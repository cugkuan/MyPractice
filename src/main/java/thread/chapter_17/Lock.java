package thread.chapter_17;

public interface Lock {

    void lock() throws InterruptedException;

    void unlock();
}
