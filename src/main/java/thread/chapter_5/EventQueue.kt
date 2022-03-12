package thread.chapter_5

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 一个简单的消费者，生产者模式，明白 wait 是刮起线程，notify 是唤醒线程
 */
class EventQueue {
    private val  max = 10
    private val lock = Object()
    private val events = LinkedList<String>()
    fun addEvent(event:String){
        synchronized(lock){
            if (events.size >= max){
                println("消息队列已经满了")
                lock.wait()
            }
            events.addLast(event)
            lock.notify()
        }
    }

    fun tak():String{
        synchronized(lock){
            if (events.isEmpty()){
                println("消息队列中没有消息了")
                lock.wait()
            }
            lock.notify()
            return events.removeFirst()
        }
    }

}

fun main(){
    val eventQueue = EventQueue()
    Thread(){
        var index = 0
        repeat (100){
            if (index < 50) {
                TimeUnit.MICROSECONDS.sleep(30)
            }
            index++
            eventQueue.addEvent(it.toString())
        }
    }
        .start()
    Thread(){
        while (true){
            TimeUnit.MICROSECONDS.sleep(10)
            println(eventQueue.tak())
        }
    }
        .start()
}