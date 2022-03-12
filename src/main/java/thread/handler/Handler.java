package thread.handler;

public abstract class Handler {

    private Looper looper;
    public Handler(){
        looper = Looper.getLooper();
    }

    protected abstract void handleMessage(Message message);

    public void sendMessage(Message message){
        message.target = this;
        looper.getMessageQueue().enqueueMessage(message);
    }
}
