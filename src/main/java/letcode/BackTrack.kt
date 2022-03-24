package letcode


/**
 * 回溯算法的训练
 * 全排列
 */



private val result = ArrayList<List<Int>>()


fun List<Int>.print(){
    val value =  StringBuilder("")
    forEach(){
        value.append(it)
        value.append(",")
    }
    println(value)
}


fun permute(nums:IntArray){
    permuteBackTrack(nums.toMutableList(),ArrayList())
}

private fun permuteBackTrack(pickList:List<Int>,trackList: ArrayList<Int>) {
    if (pickList.isEmpty()){
        trackList.print()
        return
    }
    pickList.forEachIndexed { index, i ->
        trackList.add(i)
        val start  = ArrayList(pickList).apply {
            remove(i)
        }
        permuteBackTrack(start,trackList)
        trackList.remove(i)
    }
}

fun main(){

    permute(intArrayOf(1,2,3))


}