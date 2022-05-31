package letcode.strings


/**
 * 输出最长回文字符串
 */

fun longestPalindrome(s: String): String {
    var maxString = ""
    for (i in s.indices) {
        var s1 = checkPalindrome(s, i, i + 1)
        var s2 = checkPalindrome(s, i - 1, i + 1).ifEmpty { s[i].toString() }
        val max = if (s1.length > s2.length) {
            s1
        } else {
            s2
        }
        if (max.length > maxString.length) {
            maxString = max
        }
    }
    return maxString
}

private fun checkPalindrome(s: String, left: Int, right: Int): String {
    if (left < 0 || right >= s.length) {
        return ""
    }
    var i = left
    var j = right
    while (i >= 0 && j < s.length) {
        if (s[i] == s[j]) {
            i--
            j++
        } else {
            break
        }
    }
    i++
    if (i < 0) {
        i = 0
    }
    if (j > s.length) {
        j = s.length
    }
    // substring 左闭右开
    return s.substring(i, j)
}

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 滑动窗口 解决方案
 */

//private fun isSatisfy(s: String,left: Int,right: Int){
//
//    var
//}
//fun lengthOfLongestSubstring(s: String): Int {
//
//    var i;
//    var offset
//    while (true){
//
//    }
//
//}

fun main() {

    println(longestPalindrome("abb"))
}