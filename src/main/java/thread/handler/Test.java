package thread.handler;

public class Test {

    public static void main(String[]  args) throws InterruptedException {

        Looper.prepare();
        System.out.println("主线程:"+Thread.currentThread());
        Handler handler = new Handler() {
            @Override
            protected void handleMessage(Message message) {
                System.out.println(Thread.currentThread() +":"+message.msg);
            }
        };

        new Thread(() -> {
            System.out.println(Thread.currentThread());
            Message message = new Message("ddddddd");
            handler.sendMessage(message);

            handler.sendMessage(new Message("cccccccc"));
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread());
            handler.sendMessage(new Message("eeeeeeee"));
        }).start();



        Looper.looper();
    }

}
