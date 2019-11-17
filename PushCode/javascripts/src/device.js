module.exports = function (client, internal) {
    function tcpip() {
        // console.log(adb);

    }

    async function getIp() {
        return await client.getDHCPIpAddress(internal.id)
    }

    internal.tcpip = tcpip;
    internal.getIp = getIp;
    internal.connectTcpip = async function () {
        const port = await client.tcpip(internal.id);
        console.log(port);
        await client.waitforDevice(internal.id);
        await client.getDHCPIpAddress(internal.id);
        await client.connect()
        // let ip = await allDevices[0].getIp();

    };
    return internal;
};