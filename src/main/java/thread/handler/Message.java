package thread.handler;

public class Message {
    public Handler target;
    public final String msg;

    public Message next;
    public Message(String msg){
        this.msg = msg;
    }
}
