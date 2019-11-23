const ReconnectingWebSocket =require('reconnecting-websocket');

const WS = require("ws");
const options = {
    WebSocket: WS, // custom WebSocket constructor
    connectionTimeout: 1000,
    // maxRetries: 10,
};

async function createServer(address, port, getOpModes) {
    return new Promise((resolve, reject) => {
        const client = new ReconnectingWebSocket(`ws://${address}:${port}`, null, options);
        client.on('open', () => {
            // console.log("yes");
            resolve(client);
        });
        client.on('error', (err) => {
            reject(err)
        });
        client.on('message', (message) => {
            if (message === 'ops') {
                client.send('ops/' + getOpModes(), (err) => {
                    if (err) {
                        throw err;
                    }
                    console.info("Sent opmodes")
                })
            }
        });
        // client["sendFile"] = sendFile;
    })
}


module.exports = {createServer};