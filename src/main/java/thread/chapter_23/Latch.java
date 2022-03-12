package thread.chapter_23;

public abstract  class Latch {

    protected int limit;

    public Latch(int limit){
        this.limit = limit;
    }

    public abstract void await() throws InterruptedException;

    public abstract void countDown();

    /**
     * 未完成的数量
     * @return
     */
    public abstract int getUnarrived();
}
