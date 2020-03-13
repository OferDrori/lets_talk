package com.example.lets_talk;

public class ConversationTopic {

    private String topic;
    private String subject;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ConversationTopic(String topic, String subject) {
        this.topic = topic;
        this.subject = subject;
    }
}
