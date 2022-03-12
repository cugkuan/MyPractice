package thread.chapter_3

import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
    val t1 = Thread {
        println("dddddd")
        TimeUnit.SECONDS.sleep(5)
        println("线程t1结束")
    }
    val t2 = Thread {
        println("aaaaaa")
        t1.join()
        println("线程t2结束")
    }
    t1.start()
    t2.start()
}

