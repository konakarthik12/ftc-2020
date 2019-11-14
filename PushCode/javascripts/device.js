const adb = require('adbkit');
module.exports = function (client, internal) {
    function tcpip() {
        // console.log(adb);
        client.tcpip(internal.id)
    }

    internal.tcpip = tcpip;
    return internal;
};