const socketHandler = require('./socketHandler');
const chokidar = require('chokidar');
const adb = require('./adbHandler');
const constants = require('./constants');
let client;

initServer().then((ready) => {
    client = ready;
    watchFiles();

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
            allDevices[0].tcpip()
        }
        let networkDevices1 = await adb.getNetworkDevices();
        // console.log(networkDevices1);
        return (networkDevices1)[0];
        // console.log(networkDevices);

    }
}


async function initServer() {
    let device = (await getNetworkDevice());
    console.log(device);
    client = await socketHandler.createServer(device.id.split(':')[0], constants.port);
    console.info("Connected")
}

function watchFiles() {
    chokidar.watch(`dex`)
        .on('add', pushCode)
        .on('change', pushCode);
}

function pushCode(path) {
    if (!path.endsWith('classes.dex')) {
        return
    }

    console.log(path);

}


