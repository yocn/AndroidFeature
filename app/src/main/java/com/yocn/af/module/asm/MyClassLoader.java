package com.yocn.af.module.asm;

/**
 * @Author yocn
 * @Date 2019/9/23 4:21 PM
 * @ClassName MyClassLoader
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
        super(Thread.currentThread().getContextClassLoader());
    }

    public Class<?> defineClassForName(String name, byte[] data) {
        return this.defineClass(name, data, 0, data.length);
    }

}
