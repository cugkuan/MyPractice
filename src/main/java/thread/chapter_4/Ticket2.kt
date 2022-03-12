package thread.chapter_4

/**
 * 模拟多线程下共享数据不一致问题
 */
private class TicketRun2 : Runnable {
    private var index = 0
    companion object {
        const val MAX = 500
        val lock = Object()
    }
    override fun run() {
        synchronized(lock) {
            while (index <= MAX) {
                println("${Thread.currentThread().name}的号码：${index}")
                index++
            }
        }
    }
}

fun main() {
    val run2 = TicketRun2()
    Thread(run2).start()
    Thread(run2).start()
    Thread(run2).start()
    Thread(run2).start()
}