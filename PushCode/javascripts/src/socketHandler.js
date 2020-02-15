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
    console.log("yep");
    console.log(address);
    return new Promise((resolve, reject) => {
        let full = `ws://${address}:${port}`;
        console.log(full);
        const client = new ReconnectingWebSocket(full, null, options);
        client.addEventListener('open', () => {
            if (fs.existsSync(defaultPath)) {
                console.log("yes");
                pushCode(defaultPath, client);
            }
            resolve(client);
        });
        client.addEventListener('error', (err) => {
            // reject(err)
        });
        client.addEventListener('message', (message) => {
            if (message.data === 'ops') {
                let opModes = getOpModes();
                if (!opModes) {
                    console.log("cant find txt");

                    return;
                }
                client.send('ops/' + opModes);
                console.log("sent opmodes")
            }
        });
        // client["sendFile"] = sendFile;
    })
}


module.exports = {createServer};