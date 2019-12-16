package com.example.tcfapplication;

import java.util.Date;

public class Chatmessage {

    private String MessageText;
    private long messagedatetime;
    private String Username;

    public Chatmessage(String messageText, String username) {
        MessageText = messageText;
        Username = username;
        messagedatetime = new Date().getTime();
    }

    public Chatmessage(){

    }

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public long getMessagedatetime() {
        return messagedatetime;
    }

    public void setMessagedatetime(long messagedatetime) {
        this.messagedatetime = messagedatetime;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
