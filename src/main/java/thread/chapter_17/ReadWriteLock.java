package thread.chapter_17;

public class ReadWriteLock {


    public final Object MUTEX = new Object();

    /**
     * 是否偏向写锁
     */
    private boolean preferWriter;


    public ReadWriteLock(){
        this(true);
    }

    public ReadWriteLock(boolean preferWriter){
        this.preferWriter = preferWriter;
    }

    private int writingWriters = 0;

    private int waitingWriters = 0;

    private int readingReaders = 0;


    public Lock readLock() {
        return null;
    }

    public Lock writeLock() {
        return null;
    }

    public int getWritingWriters() {
        return writingWriters;
    }

    public int getWaitingWriters(){
        return waitingWriters;
    }

    public int getReadingReaders() {
        return readingReaders;
    }

    public boolean getPreferWriter(){
        return preferWriter;
    }

    public void changePreferWriter(boolean preferWriter){
        this.preferWriter = preferWriter;
    }


    public void incrementReadingReader(){
        readingReaders ++;
    }

    public void decrementReadingReaders(){
        readingReaders --;
    }

    public void incrementWaitingWriters(){
        waitingWriters++;
    }

    public void decrementWaitingWriters(){
        waitingWriters --;
    }

    public void incrementWritingWriters(){
        writingWriters ++;
    }

    public void decrementWritingWriters(){
        writingWriters --;
    }




}
