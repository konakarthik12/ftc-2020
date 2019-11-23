const resolve = require('path').resolve;
const fs = require('fs');
const WS = require('ws');
var admin = require('firebase-admin');
const socketHandler = require('./socketHandler');
const chokidar = require('chokidar');
let adbHandler = require('./adbHandler');
const constants = require('../other/constants');
const firebaseConfig = require('./config');
let watching = false;
let client;
initServer();
//     .then(ready => {
//
//     client = ready;
//     watchFiles();
//     console.info("Connected and Watching files");
//
// });

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


async function getIp() {
    const serviceAccount = require("./ftc-config");

    admin.initializeApp({
        credential: admin.credential.cert(serviceAccount),
        databaseURL: "https://ftc-variables.firebaseio.com"
    });
    // ad.initializeApp(firebaseConfig);
    admin.database().ref('/driver-station-link/ws/').on('value', (data) => {
        try {
            let address = data.val();
            console.info("Initialing Websocket Server...");
            console.log(address);
            socketHandler.createServer(address.ip, address.port, sendOpModes).then((it) => {
                client = it;

                watchFiles();

            });
        } catch (e) {
            console.error(e);
            throw `Unable to connect to ${address.ip}:${address.port}`
        }
    });

}

async function initServer() {
    console.info("Connecting to Device...");
    // let device = (await getNetworkDevice());
    await getIp();
    // return client;
}

function watchFiles() {
    if (watching) {
        return;
    }
    watching = true;
    let paths = resolve(__dirname, constants.dexFolder);
    console.log(paths);
    chokidar.watch(paths)
        .on('add', pushCode)
        .on('change', pushCode);
}


async function pushCode(path) {
    if (!path.endsWith('classes.txt')) {
        return;
    }
    // console.log("Pushing...");
    console.log(`Sending ${path}`);
    client.send(fs.readFileSync(resolve(__dirname, constants.dexFile)), (err) => {
        if (err) throw err;
        console.log("Sent Dex")
        // client.send()
    });
    console.log(client.bufferedAmount)
}

function sendOpModes() {
    console.log("sending op modes");
    let s = fs.readFileSync(resolve(__dirname, constants.listFile), 'utf8');
    console.log(s);
    return s;
    // console.log("asked");
}
