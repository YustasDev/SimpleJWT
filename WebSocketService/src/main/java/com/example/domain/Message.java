package com.example.domain;

public class Message {
    private String from;
    private String text;

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getMessage() {
        return text;
    }
    public void setMessage(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "Message [from=" + from + ", message=" + text + "]";
    }


}
