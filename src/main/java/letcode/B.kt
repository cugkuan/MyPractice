package letcode

import java.util.Collections


/**
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？



 **/


/**
 * 传统的算法
 */
fun climbStairs(n: Int): Int {
    return when (n) {
        0 -> 0
        1 -> 1
        2 -> 2
        else -> {
            climbStairs(n-1) + climbStairs(n-2)
        }
    }
}

/**
 * 动态规划
 */
fun climbStairs2(n: Int): Int {
    val intArray = IntArray(n+1){ 0}
   return  when(n){
        0 -> 0
        1 -> 1
        2 -> 2
        else ->{
            intArray[0] = 0
            intArray[1] = 1
            intArray[2] = 2
            for (i in 3 .. n){
                intArray[i] = intArray[i-1] +intArray[i-2]
            }
            intArray[n]
        }
    }
}

/**
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。

对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 */

fun findContentChildren(g: IntArray, s: IntArray): Int {
    g.sort()
    s.sort()
    var count = 0
    var begin  = 0
    for (i in g.indices){
        val gValue = g[i]
        for (j in begin until s.size ){
            if (gValue <= s[j]){
                begin = j+1
                count ++
                break
            }
        }
    }
    return count
}

/**
 * 双指针
 */

fun findContentChildren2(g: IntArray, s: IntArray): Int {
    g.sort()
    s.sort()
    var count = 0
    var i = 0
    var j = 0
    while (i < g.size && j < s.size){
        if (s[j] >= g[i]){
            j++
            i++
            count++
        }else{
            j++
        }

    }
    return count
}



fun main(){

 //   println(climbStairs2(3))

    println(findContentChildren(intArrayOf(1,2), intArrayOf(1,2,3)))
}
