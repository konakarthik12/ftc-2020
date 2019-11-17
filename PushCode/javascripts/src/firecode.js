const resolve = require('path').resolve;
const fs = require('fs');
const WS = require('ws');

const socketHandler = require('./socketHandler');
const chokidar = require('chokidar');
let adbHandler = require('./adbHandler');
const constants = require('../other/constants');
let client;
initServer().then(ready => {

    client = ready;
    watchFiles();
    console.info("Connected and Watching files");

});

async function getNetworkDevice() {
    let allDevices = await adbHandler.getDevices();
    // console.log(allDevices.length);
    if (allDevices.length === 0) {
        throw "No android device connected"
    } else {
        let networkDevices = await adbHandler.getNetworkDevices();
        if (networkDevices.length === 0) {
            // console.log(allDevices[0].tcpip);

            await allDevices[0].connectTcpip();
        }
        let networkDevices1 = await adbHandler.getNetworkDevices();
        // console.log(networkDevices1);
        return (networkDevices1)[0];
        // console.log(networkDevices);

    }
}


async function initServer() {
    console.info("Connecting to Device...");
    let device = (await getNetworkDevice());

    try {
        console.info("Initialing Websocket Server...");
        client = await socketHandler.createServer(device.id.split(':')[0], constants.port, sendOpModes);
    } catch (e) {
        throw `Unable to connect to ${device.id.split(':')[0]}:${constants.port}`
    }
    return client;
}

function watchFiles() {
    chokidar.watch(resolve(__dirname, constants.dexFolder))
        .on('add', pushCode)
        .on('change', pushCode);
}


async function pushCode(path) {
    // console.log(path);

    if (!path.endsWith('classes.txt')) {
        return;
    }
    console.log(`Sending ${path}`);
    client.send(fs.readFileSync(resolve(__dirname, constants.dexFile)), (err) => {
        if (err) throw err;
        console.log("Sent Dex")
        // client.send()
    });
    // console.log(client.bufferedAmount)
}

function sendOpModes() {
    let s = fs.readFileSync(resolve(__dirname, constants.listFile), 'utf8');
    console.log(s);
    return s;
    // console.log("asked");
}
