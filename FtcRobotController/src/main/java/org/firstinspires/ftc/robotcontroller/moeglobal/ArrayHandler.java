package org.firstinspires.ftc.robotcontroller.moeglobal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayHandler {

    public static List<byte[]> divideArray(byte[] source, int chunksize) {

        List res = new ArrayList();
        int start = 0;

        for (int i = 0; i < (int) Math.ceil(source.length / (double) chunksize); i++) {
            res.add(Arrays.copyOfRange(source, start, start + chunksize));
            start += chunksize;
        }

        return res;
    }
}