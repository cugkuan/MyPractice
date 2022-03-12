package thread.chapter_3

import java.util.concurrent.TimeUnit

/**
 * join 的复杂使用
 */
fun main() {

    val f1 = Thread {
        TimeUnit.SECONDS.sleep(4)
        println("南方航空公司")
    }
    val f2 = Thread {
        TimeUnit.SECONDS.sleep(3)
        println("东方航空公司")
    }
    val f3 = Thread {
        TimeUnit.SECONDS.sleep(2)
        println("海南航空公司")
    }

    val t = Thread {
        println("等待查询")
        f1.start()
        f2.start()
        f3.start()

        f1.join()
        f2.join()
        f3.join()

        println("全部查询完成")
    }
    t.start()
    t.join()
}