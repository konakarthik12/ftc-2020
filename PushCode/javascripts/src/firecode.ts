import {resolve} from "path";
import * as fs from "fs";
import WS from "ws";

const socketHandler = require('./socketHandler');
const chokidar = require('chokidar');
let adb = require('./adbHandler');
const constants = require('../other/constants');
let client: WS;
initServer().then(ready => {

    client = ready;
    watchFiles();
    console.info("Connected and Watching files");

});

async function getNetworkDevice() {
    let allDevices = await adb.getDevices();
    // console.log(allDevices.length);
    if (allDevices.length === 0) {
        throw "No android device connected"
    } else {
        let networkDevices = await adb.getNetworkDevices();
        if (networkDevices.length === 0) {
            // console.log(allDevices[0].tcpip);
            allDevices[0].tcpip();
            await adb.connect(allDevices[0].getIp())
        }
        let networkDevices1 = await adb.getNetworkDevices();
        // console.log(networkDevices1);
        return (networkDevices1)[0];
        // console.log(networkDevices);

    }
}


async function initServer(): Promise<WS> {
    console.info("Connecting to Device...");
    let device = (await getNetworkDevice());

    try {
        console.info("Initialing Websocket Server...");
        client = await socketHandler.createServer(device.id.split(':')[0], constants.port);
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

async function pushCode(path: string) {
    // console.log(path);

    if (!path.endsWith('classes.dex')) {
        return
    }
    console.log(`Sending ${path}`);

    client.send(fs.readFileSync(path), (err) => {
        if (err) throw err;
        console.log("Sent")
    });
    console.log(client.bufferedAmount)
}


