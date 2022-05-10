package letcode.matrix


/**
 * 旋转矩阵
 * https://leetcode.cn/problems/rotate-image/
 *
 * 方法很巧妙
 *
 */
fun rotate(matrix: Array<IntArray>): Unit {
    val n  = matrix.size
    // 对称变换
    for (i in matrix.indices){
        for (j in i until n){
            val temp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = temp
        }
    }
    // 每一行的旋转

    for (i in matrix.indices){
        val row = matrix[i]
        var left = 0
        var right = n-1
        while (left < right){
            val temp = row[left]
            row[left] = row[right]
            row[right] = temp
            left ++
            right--
        }
    }
}

///**
// * https://leetcode.cn/problems/spiral-matrix/
// */
//fun spiralOrder(matrix: Array<IntArray>): List<Int> {
//
//}