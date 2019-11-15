import adb from "adbkit";
declare type AdbDevice ={
    id: string;

}
const device = require('./device');
const isIp = require('is-ip');
const client = adb.createClient();


export async function getDevices(): Promise<Array<AdbDevice>> {

    let newVar = await client.listDevices();
    return newVar.map(it => device(client, it));
}

export async function getNetworkDevices(): Promise<Array<AdbDevice>> {

    let devices = await getDevices();
    // console.log(devices);
    devices = devices.filter(device => {
        let strings = device.id.split(':');
        if (strings.length !== 2) return false;
        return isIp(strings[0]) && (!isNaN(strings[1] as unknown as number))
    });
    // console.log(devices);
    return devices
}

