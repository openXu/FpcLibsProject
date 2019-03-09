package com.fzy.libs.net;

/**
 * Author: openXu
 * Time: 2019/2/26 14:22
 * class: OneSentence
 * Description:
 */
public class OneSentence {

    private String  sid;             //		'sid':'' #每日一句ID
    private String  tts;             //		'tts': '' #音频地址
    private String  content;         //		'content':'' #英文内容
    private String  note;            //		'note': '' #中文内容
    private String  love;             //		'love': '' #每日一句喜欢个数
    private String  translation;     //		'translation':'' #词霸小编
    private String  picture2;         //		'picture2': '' #大图片地址
    private String  dateline;         //		'dateline':'' #时间
    private String  s_pv;           //		's_pv':'' #浏览数

    @Override
    public String toString() {
        return "OneSentence{" +
                "每日一句ID='" + sid + '\'' +
                ", 音频地址='" + tts + '\'' +
                ", 英文内容='" + content + '\'' +
                ", 中文内容='" + note + '\'' +
                ", 大图片地址='" + picture2 + '\'' +
                '}';
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getS_pv() {
        return s_pv;
    }

    public void setS_pv(String s_pv) {
        this.s_pv = s_pv;
    }
}
