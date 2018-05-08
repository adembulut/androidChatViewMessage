package adem.com.messageview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adem.com.messageview.Adapter.ChatAdapter;
import adem.com.messageview.Model.*;

public class MainActivity extends AppCompatActivity {

    ChatAdapter adapter ;
    EditText editMessage;
    ImageView imgSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        editMessage = findViewById(R.id.editMessage);
        imgSend = findViewById(R.id.imgSend);
        ListView lstChatMessage = findViewById(R.id.chatListView);
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("Adem","This is a test Message!","14:10",true));
        messageList.add(new Message("Adem","This is a test Answer!","14:10",false));
        messageList.add(new Message("Adem","This is a test Message and so big message.This is a test Message and so big message.This is a test Message and so big message.This is a test Message and so big message.This is a test Message and so big message.!","14:10",true));
        messageList.add(new Message("Adem","This is a test Answer2!","14:10",false));
        adapter = new ChatAdapter(this,messageList,lstChatMessage);

        lstChatMessage.setAdapter(adapter);

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editMessage.getText().toString().trim();
                if(msg.length()>0){
                    Message m = new Message("test",msg,getTime(),true);
                    adapter.AddMessage(m);
                }
            }
        });




    }
    String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }
}
