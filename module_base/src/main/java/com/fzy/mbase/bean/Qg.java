package com.fzy.mbase.bean;

/**
 * Author: openXu
 * Time: 2019/3/5 18:15
 * class: Qg
 * Description:
 */
public class Qg {

    private String name;

    @Override
    public String toString() {
        return "Qg{" +
                "name='" + name + '\'' +
                '}';
    }

    public Qg(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
