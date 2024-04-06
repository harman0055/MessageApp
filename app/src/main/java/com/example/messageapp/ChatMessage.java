package com.example.messageapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;
    @ColumnInfo(name="message")
    private String message;
    @ColumnInfo(name="timeSent")
    private String timeSent;
    @ColumnInfo(name="isSentButton")
    private boolean isSentButton;

    public ChatMessage() {
    }

    public ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
