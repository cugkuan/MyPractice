package thread.chapter_20;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class GuardedSusension {


    private final LinkedList<String> quenu = new LinkedList<>();
    private final int LIMIT = 10;
    public void offer(String value) throws InterruptedException {
        synchronized (this) {
            while (quenu.size() > LIMIT) {
                wait();
            }
            quenu.addLast(value);
            notifyAll();
        }
    }

    public String take() throws InterruptedException{
        synchronized (this){
            while (quenu.isEmpty()){
                wait();
            }
            notifyAll();
            return quenu.removeFirst();
        }
    }

    public static void main(String[] args){


        GuardedSusension guardedSusension = new GuardedSusension();

            new Thread(() -> {
                for (int  i =0 ;i < 100;i++){
                    try {
                        guardedSusension.offer(i +"");

                        System.out.println("输入 ："+i);

                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();

            new Thread(() -> {

                while (true){
                    try {
                        String value =  guardedSusension.take();
                        System.out.println(value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();



    }
}
