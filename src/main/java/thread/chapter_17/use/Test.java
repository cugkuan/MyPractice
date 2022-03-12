package thread.chapter_17.use;

public class Test {


    public static void main(String[] args){

        String text = "abcdefghijklmnopqrsytuvwxyz";

        ShareData shareData = new ShareData(50);

        for (int i = 0; i < 2;i++){
            new Thread(() -> {
                for (int index = 0; index < text.length();index ++ ){
                    char c = text.charAt(index);
                    try {
                        shareData.write(c);
                        System.out.println(Thread.currentThread() + "write:" + c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }

        for (int i = 0;i < 10; i++){

            new Thread(() -> {

                while (true){
                    try {
                        System.out.println(Thread.currentThread() + "read: "+new String(shareData.read()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }
}
