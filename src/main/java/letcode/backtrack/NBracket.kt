package letcode.backtrack

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
class NBracket {

    fun generateParenthesis(n: Int): List<String> {
        val input = ArrayList<String>()
        val tracks = ArrayList<Char>()
        backTrack(n, 0, 0,tracks,input)
        return input
    }

    private fun backTrack(n: Int,left: Int, right: Int, tracks: MutableList<Char>,input:MutableList<String>) {
        if (tracks.size == 2*n){
           val p =  StringBuilder()
            tracks.forEach{
                p.append(it)
            }
            println(p.toString())
            input.add(p.toString())
            return
        }

        if (left < right){
            return
        }
        if (left < n){
            tracks.add('(')
            backTrack(n,left+1,right,tracks,input)
            tracks.removeLast()
        }
        if (right < n){
            tracks.add(')')
            backTrack(n,left,right+1,tracks,input)
            tracks.removeLast()
        }
    }

}

fun main() {
    NBracket().generateParenthesis(3)
}