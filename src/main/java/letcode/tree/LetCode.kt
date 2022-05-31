package letcode.tree

import kotlin.math.max



private var maxLevel = 0

/**
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */
fun diameterOfBinaryTree(node: TreeNode?): Int {
    myDepth(node)
    return maxLevel
}
private fun myDepth(node: TreeNode?):Int{
    if (node == null){
        return 0
    }
    val left = myDepth(node.left)
    val right = myDepth(node.right)
    val max =  max(left,right)
    maxLevel = max(left + right, maxLevel)
    return max +1
}

fun main(){

    val root  = TreeNode(1).apply {
        left = TreeNode(2)
    }

    println(diameterOfBinaryTree(root))
}
