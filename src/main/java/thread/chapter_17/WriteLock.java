package thread.chapter_17;

public class WriteLock implements Lock{

    private ReadWriteLock readWriteLock;

    public WriteLock(ReadWriteLock readWriteLock){
        this.readWriteLock = readWriteLock;
    }


    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.MUTEX){
            readWriteLock.incrementWaitingWriters();
            while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() >0){
                readWriteLock.MUTEX.wait();
            }
            readWriteLock.decrementWaitingWriters();
            readWriteLock.incrementWritingWriters();
        }

    }

    @Override
    public void unlock() {

        synchronized (readWriteLock.MUTEX){
            readWriteLock.decrementWritingWriters();
            readWriteLock.changePreferWriter(false);
            readWriteLock.MUTEX.notifyAll();
        }

    }
}
