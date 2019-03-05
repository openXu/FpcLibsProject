package com.fzy.mbase.bean;

import java.util.List;

/**
 * Author: openXu
 * Time: 2019/3/5 18:02
 * class: QtBean
 * Description:
 */
public class QtListBean {

    private List<Anim> anims;
    private String banji;

    public QtListBean() {
    }

    public QtListBean(List<Anim> anims, String banji) {
        this.anims = anims;
        this.banji = banji;
    }

    @Override
    public String toString() {
        return "QtListBean{" +
                "anims=" + anims +
                ", banji='" + banji + '\'' +
                '}';
    }

    public List<Anim> getAnims() {
        return anims;
    }

    public void setAnims(List<Anim> anims) {
        this.anims = anims;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }
}
