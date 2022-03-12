package thread.handler;

public class MessageQueue {


    private Message current;

    public void enqueueMessage(Message message){

        synchronized (this) {
            if (current == null) {
                current = message;
            }
            current.next = message;
        }
    }

    public Message next() throws InterruptedException {
        synchronized (this){
            if (current == null){
                wait();
            }
            Message msg = current;
            current = msg.next;
            return msg;
        }
    }
}
