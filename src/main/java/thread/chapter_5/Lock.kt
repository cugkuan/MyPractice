package thread.chapter_5

interface Lock {
    fun lock()
    fun lock(mills:Long)
    fun unLock()
}


class BooleanLock:Lock{
    private var isLock = false
    private val lock = Object()
    private var currentThread:Thread? = null
    override fun lock() {
        synchronized(lock){
            while (isLock){
                lock.wait()
            }
            currentThread = Thread.currentThread()
            isLock = true
        }
    }

    override fun lock(mills: Long) {
        if (mills <=0){
            lock()
        }else{
            var remainingMills = System.currentTimeMillis()- mills
            while (isLock){
                lock.wait(remainingMills)
                remainingMills = System.currentTimeMillis() - remainingMills
            }
            isLock = true
            currentThread = Thread.currentThread()
        }
    }

    override fun unLock() {
        synchronized(lock){
            if (Thread.currentThread() == currentThread){
                isLock = false
                lock.notifyAll()
            }
        }

    }
}

fun main(){

    val lock  = BooleanLock()
    var index = 0

    fun test(){
        lock.lock()
        while (index < 600){
            index ++
                print("$index , ")
        }
        lock.unLock()
    }

    repeat(4){
        Thread{
            test()
        }.start()
    }

}