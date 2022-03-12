package thread.handler;

public class Looper {

    private static ThreadLocal<Looper> threadLocal = new ThreadLocal<>();
    private MessageQueue messageQueue;
    private Looper(){
        threadLocal.set(this);
        messageQueue = new MessageQueue();
    }
    public MessageQueue getMessageQueue(){
        return messageQueue;
    }
    public static Looper getLooper(){
       return threadLocal.get();
    }

    public static void prepare(){
        if (threadLocal.get() == null){
            new Looper();
        }
    }

    public static void looper() throws InterruptedException {
        Looper looper = threadLocal.get();
        if (looper == null){
            throw  new NullPointerException("需要首先调用Looper.prepare()方法");
        }
        for (;;){
           Message message =  looper.messageQueue.next();
           if (message == null){
               break;
           }
           message.target.handleMessage(message);
        }
    }
}
