package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

class MOEPositionalSystemPid(val xPid: MOERawPid, val yPid: MOERawPid, val tPid: MOETurnPid) : MOEPidSystem(xPid, yPid, tPid) {
    constructor(Px: Double = 0.0, Ix: Double = 0.0, Dx: Double = 0.0, Fx: Double = 0.0,
                Py: Double = 0.0, Iy: Double = 0.0, Dy: Double = 0.0, Fy: Double = 0.0,
                Pt: Double = 0.0, It: Double = 0.0, Dt: Double = 0.0, Ft: Double = 0.0) :
            this(MOERawPid(Px, Ix, Dx, Fx), MOERawPid(Py, Iy, Dy, Fy), MOETurnPid(Pt, It, Dt, Ft))
    constructor(xOptions: MOEPidValues, yOptions: MOEPidValues, turnOptions: MOEPidValues) :
            this(MOERawPid(xOptions), MOERawPid(yOptions), MOETurnPid(turnOptions))
}