package com.fzy.mbase.bean;

/**
 * Author: openXu
 * Time: 2019/3/5 18:02
 * class: QtBean
 * Description:
 */
public class QtBean {

    private Anim anim;
    private String banji;

    public QtBean() {
    }

    public QtBean(Anim anim, String banji) {
        this.anim = anim;
        this.banji = banji;
    }

    @Override
    public String toString() {
        return "QtBean{" +
                "anim=" + anim +
                ", banji='" + banji + '\'' +
                '}';
    }

    public Anim getAnim() {
        return anim;
    }

    public void setAnim(Anim anim) {
        this.anim = anim;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }
}
