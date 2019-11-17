package org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading;

import android.util.Log;

public class TeamLessClassLoader extends ClassLoader {
    public TeamLessClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//        Log.e("name", name);
        if(name.startsWith("org.firstinspires.ftc.teamcode")){
            throw new ClassNotFoundException();
        }
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(name.startsWith("org.firstinspires.ftc.teamcode")){
            throw new ClassNotFoundException();
        }
        return super.findClass(name);
    }

}
