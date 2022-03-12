package thread.chapter_4

/**
 * 模拟多线程下共享数据不一致问题
 */
internal  class TicketRun : Runnable {
    private var index = 0
    companion object {
        const val MAX = 500
    }
    override fun run() {

        while (index <= MAX) {
            Thread.yield()
            println("${Thread.currentThread().name}的号码：${index}")
            index ++
        }
    }
}

fun main() {
    val run = TicketRun()
    val t1 = Thread(run).apply {
        name = "当前窗口：1"
    }
    val t2 = Thread(run).apply {
        name = "当前窗口：2"
    }
    val t3 = Thread(run).apply {
        name = "当前窗口：3"
    }
    val t4 = Thread(run).apply {
        name = "当前窗口：4"
    }
    t1.start()
    t2.start()
    t3.start()
    t4.start()
}