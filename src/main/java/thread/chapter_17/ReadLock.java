package thread.chapter_17;

public class ReadLock implements Lock{


    private ReadWriteLock readWriteLock;

    public ReadLock(ReadWriteLock readWriteLock){
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {

        synchronized (readWriteLock.MUTEX){

            while (readWriteLock.getWritingWriters() > 0 || (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0)){
                readWriteLock.MUTEX.wait();
            }

            readWriteLock.incrementReadingReader();
        }

    }

    @Override
    public void unlock() {

        synchronized (readWriteLock.MUTEX){

            readWriteLock.decrementReadingReaders();
            readWriteLock.changePreferWriter(true);
            readWriteLock.MUTEX.notifyAll();
        }

    }
}
