package thread.chapter_17.use;

import thread.chapter_17.Lock;
import thread.chapter_17.ReadLock;
import thread.chapter_17.ReadWriteLock;
import thread.chapter_17.WriteLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShareData {

    private final List<Character> container = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReadWriteLock();
    private final Lock readLock = new ReadLock(readWriteLock);
    private final Lock writeLock = new WriteLock(readWriteLock);
    private final int length;
    public ShareData(int length){
        this.length  = length;
        for (int i = 0;i < length;i++){
            container.add(i,'c');
        }
    }

    private void slowly() throws InterruptedException {

        TimeUnit.SECONDS.sleep(1);
    }

    public char[] read() throws InterruptedException{
        readLock.lock();
        char[] newBuffer = new char[length];
        for (int i = 0;i < length ;i++){
            newBuffer[i] = container.get(i);
        }
        slowly();
        readLock.unlock();
        return newBuffer;

    }

    public void write(char c) throws InterruptedException{
        writeLock.lock();
        for (int i = 0; i < length;i++){
            this.container.add(i,c);
        }
        slowly();
        writeLock.unlock();
    }


}
