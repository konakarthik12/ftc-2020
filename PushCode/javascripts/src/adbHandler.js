const adb = require('adbkit');

const device = require('./device');
const isIp = require('is-ip');
const client = adb.createClient();


async function getDevices() {

    let newVar = await client.listDevices();
    return newVar.map(it => device(client, it));
}

async function getNetworkDevices() {

    let devices = await getDevices();
    // console.log(devices);
    devices = devices.filter(device => {
        let strings = device.id.split(':');
        if (strings.length !== 2) return false;
        return isIp(strings[0]) && (!isNaN(strings[1]))
    });
    // console.log(devices);
    return devices
}

function connect(ip) {
    console.log(ip);
    client.connect(ip)
}

module.exports = {getDevices, getNetworkDevices, connect};

