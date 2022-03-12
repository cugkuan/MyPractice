package thread.chapter_23;

import java.util.concurrent.TimeUnit;

public class CountDownLatch extends Latch {

    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {

        synchronized (this) {
            while (limit > 0) {
                this.wait();
            }
        }

    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalArgumentException("all of task already arrived");
            }
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        return limit;
    }


    static int a, b, c, d;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 2;
            countDownLatch.countDown();

        }).start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b = 2;
            countDownLatch.countDown();

        }).start();

        new Thread(() -> {
            c = 1;
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();

        }).start();

        new Thread(() -> {
            d = 2;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();

        }).start();


        countDownLatch.await();
        System.out.println(a + b + c + d);

    }
}
