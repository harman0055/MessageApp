package com.example.messageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.messageapp.R;
import com.example.messageapp.ChatMessage;
import com.example.messageapp.ChatRoomViewModel;
import com.example.messageapp.databinding.ReceiveMessageBinding;
import com.example.messageapp.databinding.ChatRoomBinding;
import com.example.messageapp.databinding.SentMessageBinding;
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
    ChatRoomViewModel chatModel ;
    ArrayList<ChatMessage> messages = new ArrayList<>();

    RecyclerView.Adapter<MyRowHolder> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        binding= ChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        messages = chatModel.messages.getValue();
        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }




        binding.recyclerView.setAdapter(myAdapter=new RecyclerView.Adapter<MyRowHolder>(){

            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                if (viewType==0)
                {
                    SentMessageBinding binding= SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());}
                else {
                    ReceiveMessageBinding binding= ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }

            }
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position){
                ChatMessage obj = messages.get(position);
                holder.messageText.setText(obj.getMessage());
                holder.timeText.setText(obj.getTimeSent());

            }
            @Override
            public int getItemCount(){
                return messages.size();

            }
            @Override
            public  int getItemViewType(int position){
                ChatMessage obj = messages.get(position);
                if (obj.isSentButton()==true)

                    return 0; // sender message
                else
                    return 1;// receiver message
            }



        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.sendButton.setOnClickListener(click->{
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            messages.add(new ChatMessage(binding.inputText.getText().toString(),currentDateandTime,true));
            myAdapter.notifyItemInserted(messages.size()-1);
            binding.inputText.setText("");

        });
        binding.receiveButton.setOnClickListener(click->{
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            messages.add(new ChatMessage(binding.inputText.getText().toString(),currentDateandTime,false));
            myAdapter.notifyItemInserted(messages.size()-1);
            binding.inputText.setText("");

        });

    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText=itemView.findViewById(R.id.message);
            timeText=itemView.findViewById(R.id.time);

        }
    }
}