package thread.chapter_22;

import java.io.IOException;
import java.util.Scanner;

public class InputServer extends Thread{

    private Document document;

    private Scanner scanner;
    public InputServer(Document document){
        this.document = document;
        scanner = new Scanner(System.in);
    }


    @Override
    public void run() {
        int times = 0;
        while (true){
            String text = scanner.next();
            if ("quit".equals(text)){
                try {
                    document.close();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            document.edit(text);

            if (times == 5){
                try {
                    document.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                times = 0;
            }
            times ++;



        }
    }
}
