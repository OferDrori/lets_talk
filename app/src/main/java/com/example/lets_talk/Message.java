package com.example.lets_talk;

import android.hardware.Sensor;

public class Message {
    private long timeStamp = 0;
    private String content;
    private long sender;
    private long receiver;
    private int status = 0;
    private String senderName;
    private String photoPath;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Message(long timeStamp, String content, long sender, long receiver, int status, String senderName,String photoPath) {
        this.timeStamp = timeStamp;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.senderName = senderName;
        this.photoPath=photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Message() {
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Message setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public long getSender() {
        return sender;
    }

    public Message setSender(long sender) {
        this.sender = sender;
        return this;
    }

    public long getReceiver() {
        return receiver;
    }

    public Message setReceiver(long receiver) {
        this.receiver = receiver;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Message setStatus(int status) {
        this.status = status;
        return this;
    }

}
