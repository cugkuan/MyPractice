package thread.chapter_12


@Volatile var index = 0
fun main(){

    repeat(4){
        Thread{
            while (index < 50){
                index ++
                println(index)

            }

        }.start()
    }



}