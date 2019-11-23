const ReconnectingWebSocket = require('reconnecting-websocket');

const WS = require("ws");
const options = {
    WebSocket: WS, // custom WebSocket constructor
    connectionTimeout: 1000,
    // maxRetries: 10,
};

function createServer(address, port, getOpModes) {
    console.info(address);
    return new Promise((resolve, reject) => {
        let full = `ws://${address}:${port}`;
        console.log(full);
        const client = new ReconnectingWebSocket(full, null, options);
        client.addEventListener('open', () => {
            // console.log("yes");
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