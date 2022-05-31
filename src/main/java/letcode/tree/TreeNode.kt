package letcode.tree

import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

data class TreeNode(var `val`: Int) {

    var left: TreeNode? = null
    var right: TreeNode? = null

}

fun traverse(node: TreeNode?) {
    if (node == null) {
        return
    }
    println(node.`val`)
    traverse(node.left)
    traverse(node.right)
}

/**
 * 层序遍历
 */
fun levelTraverse(node: TreeNode?) {
    val queue: Queue<TreeNode> = LinkedList()
    if (node != null) {
        queue.add(node)
    }
    while (queue.peek() != null) {
        val siz = queue.size
        for (i in 0 until siz) {
            val node = queue.poll()
            println(node.`val`)
            if (node.left != null) {
                queue.add(node.left)
            }
            if (node.right != null) {
                queue.add(node.right)
            }
        }

    }
}

fun depth(node: TreeNode?):Int{

    if (node == null){
        return 0
    }
    val left = depth(node.left) +1
    val right = depth(node.right)+1
    return max(left,right)
}

fun main() {
    //二叉树的最大高度
    val root = TreeNode(3)
        .apply {
            left = TreeNode(9)
            right = TreeNode(20).apply {
                left = TreeNode(15)
                right = TreeNode(7)
            }
        }
//    traverse(root)
//    println(maxDepth(root))

  //  levelTraverse(root)
    println( depth(root))


}