package letcode.backtrack

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
**/
class PhoneNumber {

    fun letterCombinations(digits: String): List<String> {
        if (digits.isNullOrEmpty()) {
            return Collections.emptyList()
        }
        val map = HashMap<Char, String>()
        map['2'] = "abc"
        map['3'] = "def"
        map['4'] = "ghi"
        map['5'] = "jkl"
        map['6'] = "mno"
        map['7'] = "pqrs"
        map['8'] = "tuv"
        map['9'] = "wxyz"
        val list = ArrayList<String>();
        for (c in digits.toCharArray()) {
            map[c]?.let {
                list.add(it)
            }
        }
        val prints = ArrayList<String>()
        numberBacktrack(list, 0, ArrayList(), prints)
        return prints
    }

    /**
     * 注意选择列表的条件判定
     */
    private fun numberBacktrack(number: List<String>, index: Int, tracks: ArrayList<Char>, prints: ArrayList<String>) {
        if (number.size == tracks.size) {
            val c = StringBuilder()
            tracks.forEach {
                print(it)
                c.append(it)
            }
            print("\n")
            prints.add(c.toString())
            return
        }
        for (i in index until number.size) {
            val num = number[i]
            num.toCharArray().forEach { c ->
                tracks.add(c)
                numberBacktrack(number, i + 1, tracks, prints)
                tracks.removeLast()
            }
        }
    }
}

fun main(){
    PhoneNumber().letterCombinations("23")
}