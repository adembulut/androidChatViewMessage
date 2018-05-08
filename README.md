# Chat View Message
Chat Layout and Classes for Android

## Getting Started
Öncelikle projenizin /res/drawable klasörüne imgs içerisindeki image dosyalarını kopyalayın. Sonra res/Layout klasöründe chat_layout.xml dosyası oluşturun. Dosya içeriği aşağıdaki gibi

//Kendinize göre düzenleyebilirsiniz.
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        android:id="@+id/chatListView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_alignParentStart="true"
        android:divider="@null"
        android:dividerHeight="0dp"
        android:layout_marginBottom="40dp"/>

    <RelativeLayout
        android:id="@+id/lnrLay"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:orientation="horizontal"
        android:layout_alignParentBottom="true"
        android:weightSum="2">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/editMessage"
            android:layout_alignParentTop="true"
            android:layout_toStartOf="@+id/imgSend"
            android:hint="Write Message"
            android:padding="10dp" />
        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/imgSend"
            android:src="@drawable/send"
            android:layout_alignParentEnd="true"/>
    </RelativeLayout>
</RelativeLayout>

## chat_bubble.xml 
/res/layout dizinine chat_bubble.xml dosyasını oluşturun içeriği aşağıdaki gibi
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/bubble_layout_parent"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <LinearLayout
        android:id="@+id/bubble_layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/bubble1"
        android:layout_margin="5dp"
        android:padding="10dp"
        android:orientation="vertical">

            <TextView
                android:id="@+id/message_text"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:maxEms="12"
                android:layout_gravity="center"
                android:text="Hi! new message"
                android:layout_marginStart="10dp"
                android:layout_marginEnd="5dp"
                android:textColor="#000" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:maxEms="10"
            android:text="14:10"
            android:layout_gravity="end|bottom"
            android:id="@+id/txtTime"/>
    </LinearLayout>

</LinearLayout>

## MessageItem class file 
//Projenize Message classı ekleyin. Bu class örnek projedeki IMessage interfacesinden implement olmalıdır.
```javascript
public class Message implements IMessage { ...

...}
```

## CustomAdapter
Projenize CustomAdapter ekleyin BaseAdapter sınıfından extend edilecek.
```javascript
public class ChatAdapter extends BaseAdapter {
....
 @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.chat_bouble,null);
        Message message = listItem.get(i);
        TextView txtMessage = v.findViewById(R.id.message_text);
        TextView txtTime = v.findViewById(R.id.txtTime);
        LinearLayout layout = v.findViewById(R.id.bubble_layout);
        LinearLayout parentLayout = v.findViewById(R.id.bubble_layout_parent);
        if(message.isMine()){ //Mesajın bize ait olup olmadığını belirleyip yerleşimi ona göre ayarlamak için. Olmazsa olmaz
            layout.setBackgroundResource(R.drawable.bubble2);
            parentLayout.setGravity(Gravity.RIGHT);
        }
        txtMessage.setText(message.getMessage());
        txtTime.setText(message.getTime());

        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // Listedeki mesajları silmek için. Opsiyoneldir.
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog alertDialog = new AlertDialog.Builder(ctx).setTitle("Check").setMessage("Are you delete item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Message m = listItem.get(position);
                                listItem.remove(m);
                                notifyDataSetChanged();
                            }
                        }).create();
                        alertDialog.show();
                return false;
            }
        });

        return v;
    }
    ....
    }
```

## Son olarak MainActivity
```javascript
public class MainActivity extends AppCompatActivity {
...

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


...
}

```

Projenin son hali yaklaşık olarak aşağıdaki gibi olmalı

[![INSERT YOUR GRAPHIC HERE](https://raw.githubusercontent.com/adembulut/androidChatViewMessage/master/screenshot.png)]()
