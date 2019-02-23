package com.fzy.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: openXu
 * Time: 2019/2/23 17:20
 * class: FzyMessage
 * Description: 消息推送
 */

@Entity
public class FzyMessage {

    /**常量*/
    @Transient
    public static final int STATUS_UNREAD = 1;       //未读标记，0：已读， 1：未读
    public static final int STATUS_READED = 0;       //未读标记，0：已读， 1：未读

    @Id(autoincrement = true)
    private Long id;
    private String MessageID;       //	消息ID，必选。
    private String Title;       //	消息标题，必选。
    private String Content;       //	消息内容，可选。
    private String MessageType;       //	消息类型(0,通知栏消息;1,透传消息) ，必选。
    private String MessageTopic;       //	消息主题,可选。
    private String OperType;       //	业务类型('Login':登录；'Logout':注销；'Notify':系统通知；'ism':巡检；'exam':标准化检查；'emm':保养；'emf',运维；'emi',盘点)，可选。
    private String Extra;       //	扩展信息(用于扩展消息特殊信息，如数据接口或链接的Uri等)，可选。
    private String FromUserAccount;       //	发布消息帐号，可选。
    private String ToUserAccount;       //	目标消息帐号，可选。
    private String PublishDate;       //	发布时间，必选。
    private String AppId;       //	应用系统ID，可选。
    private int ReadStatus  = 1;   // /*未读标记，0：已读， 1：未读*/

    @Generated(hash = 671313852)
    public FzyMessage(Long id, String MessageID, String Title, String Content, String MessageType, String MessageTopic,
            String OperType, String Extra, String FromUserAccount, String ToUserAccount, String PublishDate, String AppId,
            int ReadStatus) {
        this.id = id;
        this.MessageID = MessageID;
        this.Title = Title;
        this.Content = Content;
        this.MessageType = MessageType;
        this.MessageTopic = MessageTopic;
        this.OperType = OperType;
        this.Extra = Extra;
        this.FromUserAccount = FromUserAccount;
        this.ToUserAccount = ToUserAccount;
        this.PublishDate = PublishDate;
        this.AppId = AppId;
        this.ReadStatus = ReadStatus;
    }

    @Generated(hash = 1657463902)
    public FzyMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String messageID) {
        MessageID = messageID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }

    public String getMessageTopic() {
        return MessageTopic;
    }

    public void setMessageTopic(String messageTopic) {
        MessageTopic = messageTopic;
    }

    public String getOperType() {
        return OperType;
    }

    public void setOperType(String operType) {
        OperType = operType;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public String getFromUserAccount() {
        return FromUserAccount;
    }

    public void setFromUserAccount(String fromUserAccount) {
        FromUserAccount = fromUserAccount;
    }

    public String getToUserAccount() {
        return ToUserAccount;
    }

    public void setToUserAccount(String toUserAccount) {
        ToUserAccount = toUserAccount;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public int getReadStatus() {
        return ReadStatus;
    }

    public void setReadStatus(int readStatus) {
        ReadStatus = readStatus;
    }
}
