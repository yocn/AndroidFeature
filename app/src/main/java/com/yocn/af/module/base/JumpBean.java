package com.yocn.af.module.base;

/**
 * @Author yocn
 * @Date 2019/8/4 8:49 PM
 * @ClassName JumpBean
 */
public class JumpBean {
    private String show;
    private Class toClass;

    public JumpBean(String show, Class toClass) {
        this.show = show;
        this.toClass = toClass;
    }

    public String getShow() {
        return show;
    }

    public Class getToClass() {
        return toClass;
    }

}
