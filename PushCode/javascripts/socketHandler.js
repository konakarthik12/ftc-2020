const ws = require('ws');
let server;

async function createServer(address, port) {
    return new Promise(resolve => {
        const client = new ws(`${address}:${port}`);
        client.on('open', () => {

        })
    })
}

module.exports = {createServer};