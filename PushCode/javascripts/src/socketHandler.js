const WS = require("ws");


async function createServer(address , port, getOpModes) {
    return new Promise((resolve, reject) => {
        const client = new WS(`ws://${address}:${port}`, null, null);
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