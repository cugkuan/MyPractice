package thread.chapter_22;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AutoSaveServer  extends Thread{

    private Document document;
    public AutoSaveServer(Document document){
        this.document = document;
    }

    @Override
    public void run() {
        while (true){
            try {
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (IOException | InterruptedException  e) {
                e.printStackTrace();
                break;
            }

        }
    }
}
