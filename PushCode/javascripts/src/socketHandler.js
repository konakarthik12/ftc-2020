// import * as fs from "node/fs";
const fs = require('fs');
const ReconnectingWebSocket = require('reconnecting-websocket');

const WS = require("ws");
const options = {
    WebSocket: WS, // custom WebSocket constructor
    connectionTimeout: 1000,
    // maxRetries: 10,
};
let defaultPath = 'C:/Users/konak/Documents/ftc2020/MOETime/PushCode/build/dex/classes.txt';

function createServer(address, port, getOpModes, pushCode) {
    console.info(address);
    return new Promise((resolve, reject) => {
        let full = `ws://${address}:${port}`;
        console.log(full);
        const client = new ReconnectingWebSocket(full, null, options);
        client.addEventListener('open', () => {
            // console.log("yes");
            if (fs.existsSync(defaultPath))
                pushCode(defaultPath,client);
            resolve(client);
        });
        client.addEventListener('error', (err) => {
            // reject(err)
        });
        client.addEventListener('message', (message) => {
            if (message.data === 'ops') {
                client.send('ops/' + getOpModes());
                console.log("sent opmodes")
            }
        });
        // client["sendFile"] = sendFile;
    })
}


module.exports = {createServer};