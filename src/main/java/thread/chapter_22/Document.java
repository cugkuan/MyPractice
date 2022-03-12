package thread.chapter_22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Document {


    private volatile  boolean change = false;

    private List<String> conteng = new ArrayList<>();

    private File doc;

    private FileWriter fileWriter;

    private InputServer inputServer;

    private AutoSaveServer autoSaveServer;


    public Document() throws IOException {
        doc = new File("src/thread.chapter_22/Test.txt");
        if (!doc.exists()){
            doc.createNewFile();
        }
        fileWriter = new FileWriter(doc,true);
        inputServer = new InputServer(this);
        autoSaveServer = new AutoSaveServer(this);
    }

    public void start(){
        inputServer.start();
        autoSaveServer.start();
    }




    public void close() throws IOException {

        synchronized (this){
            fileWriter.close();
            autoSaveServer.interrupt();
        }
    }

    public void edit(String content){
        synchronized (this){
            this.conteng.add(content);
            this.change = true;
        }
    }



    public void save() throws IOException {
        synchronized (this){
            if (!change){
                return;
            }
            System.out.println(Thread.currentThread() + " execute the save action");
            for (String cache:conteng){
                fileWriter.write(cache);
                fileWriter.write("\r\n");
            }
            fileWriter.flush();
            change = false;
            conteng.clear();
        }

    }
}
