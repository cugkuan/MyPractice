package thread.chapter_3

import java.util.concurrent.TimeUnit

/**
 * isInterrupted 不是很靠谱，最好需要其他的标志位
 */
@Volatile
var stop = false
fun main() {
    val thread = object : Thread() {
        override fun run() {
            println("开始启动")
            while (isInterrupted.not()) {

            }
            println("退出。。。。。")
        }
    }
    thread.start()
    TimeUnit.SECONDS.sleep(2)
    println("即将关闭")
    try {
        thread.interrupt()
    } catch (e: InternalError) {
        println("中断。。。。。")
    }


    val thread2 = Thread {
        println("开始启动...........")
        while (stop.not()) {

        }
        println("关闭了。。。。。。")
    }
    thread2.start()
    TimeUnit.SECONDS.sleep(3)
    stop = true
    println("关闭第二个线程")
}