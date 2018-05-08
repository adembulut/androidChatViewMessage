package adem.com.messageview.Model;

/**
 * Created by xsom on 08.05.2018.
 */

public class Message implements IMessage {
    public String Name;
    public String Message;
    public Boolean Mine;
    public String Time;

    public Message(){

    }
    public Message(String name,String message,String time,boolean mine){
        this.Name = name;
        this.Message = message;
        this.Mine = mine;
        this.Time = time;
    }
    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getMessage() {
        return Message;
    }

    @Override
    public boolean isMine() {
        return Mine;
    }

    @Override
    public String getTime() {
        return Time;
    }
}
