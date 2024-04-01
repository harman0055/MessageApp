package com.example.messageapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @ColumnInfo(name = "message")
    protected String message;
    @ColumnInfo(name = "TimeSent")
    protected String timeSent;
    @ColumnInfo(name = "SendOrReceive")
    protected int SendOrReceive;
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    public ChatMessage(String m, String t, int sent)
    {
        message = m;
        timeSent = t;
        SendOrReceive = sent;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public int getSendOrReceive() {return SendOrReceive;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public void setSendOrReceive(int sentButton) {
        SendOrReceive = sentButton ;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
