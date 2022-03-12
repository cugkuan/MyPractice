package thread.chapter_5

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 多线程消费模式
 */
class EventQueue2 {
    private val  max = 10
    private val lock = Object()
    private val events = LinkedList<String>()
    fun addEvent(event:String){
        synchronized(lock){
            while (events.size >= max){
                println("消息队列已经满了")
                lock.wait()
            }
            events.addLast(event)
            lock.notifyAll()
        }
    }

    fun tak():String{
        synchronized(lock){
            while (events.isEmpty()){
                println("消息队列中没有消息了")
                lock.wait()
            }
            lock.notifyAll()
            return events.removeFirst()
        }
    }

}

fun main(){

    val eventQueue = EventQueue2()
    Thread(){
        var index = 0
        repeat (100){
            index++
            eventQueue.addEvent(it.toString())
        }
    }
        .start()

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
    Thread(){
        while (true){
            println(eventQueue.tak())
        }
    }
        .start()
}