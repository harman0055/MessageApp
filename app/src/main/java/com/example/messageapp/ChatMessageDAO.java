package com.example.messageapp;
import androidx.room.Dao;
        import androidx.room.Database;
        import androidx.room.Delete;
        import androidx.room.Insert;
        import androidx.room.PrimaryKey;
        import androidx.room.Query;
        import androidx.room.RoomDatabase;

        import com.example.messageapp.ChatMessage;

        import java.util.List;
@Dao
public interface ChatMessageDAO {
    @Insert
    void insertMessage(ChatMessage m);

    @Query("Select * from ChatMessage")
    List<ChatMessage> getAllMessages();

    @Delete
    void deleteMessage(ChatMessage m);

    @Database(entities = {ChatMessage.class}, version = 1)
    public abstract class MessageDatabase extends RoomDatabase {
        public abstract ChatMessageDAO cmDAO();
        ChatMessageDAO mDAO;
    }

}