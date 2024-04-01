package com.example.messageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.messageapp.R;
import com.example.messageapp.ChatMessage;
import com.example.messageapp.ChatRoomViewModel;
import com.example.messageapp.databinding.ReceiveMessageBinding;
import com.example.messageapp.databinding.ChatRoomBinding;
import com.example.messageapp.databinding.SentMessageBinding;
import com.google.android.material.snackbar.Snackbar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoom extends AppCompatActivity {
    ChatRoomBinding binding;
    ChatRoomViewModel chatModel;
    ArrayList<ChatMessage> messages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        ChatMessageDAO.MessageDatabase db = Room.databaseBuilder(getApplicationContext(),
                ChatMessageDAO.MessageDatabase.class, "database-name").build();

        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView timeText;

            public MyRowHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(clk -> {
                    int position = getAbsoluteAdapterPosition();


                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);
                    builder.setMessage("Do you want to delete this message:" + messageText.getText())
                            .setTitle("Question:")
                            .setNegativeButton("No", (dialog, cl) -> {
                            })
                            .setPositiveButton("Yes", (dialog, cl) -> {

                                messages.remove(position);


                                Snackbar.make(messageText, "You deleted message#" + position, Snackbar.LENGTH_LONG);


                            })

                            .show();
                });
            }
        }
    }
}