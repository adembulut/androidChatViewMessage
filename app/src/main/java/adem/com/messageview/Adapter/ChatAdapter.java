package adem.com.messageview.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import adem.com.messageview.Model.Message;
import adem.com.messageview.R;

/**
 * Created by xsom on 08.05.2018.
 */

public class ChatAdapter extends BaseAdapter {
    Context ctx;
    List<Message> listItem;
    LayoutInflater inflater =null;
    ListView lst;
    public ChatAdapter(Context context,List<Message> list,ListView lst){
        this.ctx = context;
        this.listItem = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lst = lst;
    }
    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Message getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.chat_bouble,null);
        Message message = listItem.get(i);
        TextView txtMessage = v.findViewById(R.id.message_text);
        TextView txtTime = v.findViewById(R.id.txtTime);
        LinearLayout layout = v.findViewById(R.id.bubble_layout);
        LinearLayout parentLayout = v.findViewById(R.id.bubble_layout_parent);
        if(message.isMine()){
            layout.setBackgroundResource(R.drawable.bubble2);
            parentLayout.setGravity(Gravity.RIGHT);
        }
        txtMessage.setText(message.getMessage());
        txtTime.setText(message.getTime());

        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
    public void AddMessage(Message message){
        listItem.add(message);
        notifyDataSetChanged();
        lst.smoothScrollToPosition(listItem.size()-1);
    }
}
