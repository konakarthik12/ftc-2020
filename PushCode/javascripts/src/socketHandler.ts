import WS from "ws";

let server;

export async function createServer(address: string, port: number): Promise<WS> {
    return new Promise((resolve, reject) => {
        const client = new WS(`ws://${address}:${port}`, null, null);
        client.on('open', () => {
            // console.log("yes");
            resolve(client);
        });
        client.on('error', (err) => {
            reject(err)
        });
        client["sendFile"] = sendFile;
    })
}

export function sendFile(path: any) {

}

// module.exports = {createServer};