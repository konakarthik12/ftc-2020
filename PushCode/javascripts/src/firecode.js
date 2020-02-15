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
            createServer(address)
        } catch (e) {
            console.error(e);
            throw `Unable to connect to ${address.ip}:${address.port}`
        }
    });
    handleCommandLine()
}

function handleCommandLine() {
//TODO:temp
//     createServer({ip:'192.168.49.1',port:'43876'})
}

function createServer(address) {
    socketHandler.createServer(address.ip, address.port, sendOpModes, checkPushCode).then((it) => {
        client = it;

        watchFiles();

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
    chokidar.watch(paths, {ignoreInitial: true})
        .on('add', fileChange)
    // .on('change', pushCode);
}

function fileChange(path) {
    return checkPushCode(path, client)
}

async function checkPushCode(path, client) {
    if (!path.endsWith('classes.txt')) {
        return;
    }

    pushCode(client);
}

function pushCode(client) {
    // console.log("pushing code");
    // console.trace();
    client.send(fs.readFileSync(resolve(__dirname, constants.dexFile)), (err) => {
        if (err) throw err;
        console.log("Sent Dex")
        // client.send()
    });
}

function sendOpModes() {
    console.log("sending op modes");
    // console.log(s);
    try {
        return fs.readFileSync(resolve(__dirname, constants.listFile), 'utf8')
    } catch (e) {
        console.error(e)
    }
    // console.log("asked");
}
