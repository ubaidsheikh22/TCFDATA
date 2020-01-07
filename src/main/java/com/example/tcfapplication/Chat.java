package com.example.tcfapplication;

public class Chat {
    public String Receiver;
    public String Sender;
    public String Message;


    public Chat(String receiver, String sender) {
        Receiver = receiver;
        Sender = sender;
    }
    public boolean isseen()
    {
        return false;
    }

    public String getReceiver() {
        return Receiver;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setReciever(String receiver) {
        Receiver = receiver;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }
}
