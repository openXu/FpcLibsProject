package com.fzy.mbase.bean;

import java.util.Map;

/**
 * Author: openXu
 * Time: 2019/3/5 18:15
 * class: Qg
 * Description:
 */
public class Qg {

    private String name;

    private Map<String, String> map;

    public Qg(String name, Map<String, String> map) {
        this.name = name;
        this.map = map;
    }

    @Override
    public String toString() {
        return "Qg{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
