package letcode.backtrack

import letcode.subarraySum
import java.util.Arrays

/**
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target,找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为target 的不同组合数少于 150 个。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class CombinationSum {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        Arrays.sort(candidates)
        backTrack(candidates,target,0,ArrayList<Int>(),result)
        return result
    }
    private fun MutableList<Int>.sum():Int{
        var sum = 0
        forEach{
            sum+= it
        }
        return sum
    }
    private fun backTrack(candidates: IntArray,target: Int,index:Int,tracks:MutableList<Int>,result:MutableList<List<Int>>){
        val sum = tracks.sum()
        if (sum > target){
            return
        }
        if (sum == target){
            result.add(ArrayList(tracks))
            return
        }
        for (i in index until candidates.size){
            tracks.add(candidates[i])
            backTrack(candidates,target,i,tracks,result)
            tracks.removeAt(tracks.size -1)
        }
    }
}
fun main(){
   val result =  CombinationSum().combinationSum(intArrayOf(2,3,6,7),7)
    result.forEach { list ->
        val  c = StringBuilder()
        c.append("[")
        list.forEach{
            c.append(it)
            c.append(",")
        }
        c.append("]")
        println(c.toString())
    }


}