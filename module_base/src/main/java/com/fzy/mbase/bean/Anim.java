package com.fzy.mbase.bean;

public class Anim {

    private String userName;
    private String password;
    private Qg qg;

    public Anim() {
    }

    public Anim(String userName, String password, Qg qg) {
        this.userName = userName;
        this.password = password;
        this.qg = qg;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", qg=" + qg +
                '}';
    }

    public Qg getQg() {
        return qg;
    }

    public void setQg(Qg qg) {
        this.qg = qg;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
