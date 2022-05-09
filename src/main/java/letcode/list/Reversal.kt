package letcode.list

/**
 * 反转
 */
class Reversal {

    /**
     *  简单的反转
     */
    fun simpleReversal(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        val last = simpleReversal(head?.next)
        head.next?.next = head
        head.next = null
        return last
    }

    /**
     * 前 n个元素反转
     */
    private var endPoint: ListNode? = null
    fun reversalN(head: ListNode?, n: Int): ListNode? {
        if (n == 1) {
            endPoint = head?.next
            return head
        }
        val last = reversalN(head?.next, n - 1)
        head?.next?.next = head
        head?.next = endPoint
        return last
    }

    /**
     * 元素区间反转
     *
     *  **/
    fun reversalMN(head: ListNode?, m: Int, n: Int): ListNode? {
        return if (m == 1) {
            reversalN(head, n)
        } else {
            val node = reversalMN(head?.next, m - 1, n - 1)
            head?.next = node
            head
        }
    }

    /**
     * k 个一组，翻转链表
     * 难度又提升了一点点,我们假设是二个一组的翻转，那么翻转如下
     */
    fun reversalK2(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null) {
            return head
        }
        // 对两个一组的反转，可以这样操作
        val node1 = head?.next
        val node = reversalK2(head?.next?.next, k)
        head?.next?.next = head
        head?.next = node
        return node1
    }

    fun testReversalK2(head: ListNode?): ListNode? {
        val node = reversalK(head, 2)
        return node
    }

    /**
     * k 个一组，翻转链表
     * 难度又提升了一点点
     */

    private fun endK(head: ListNode?, k: Int): ListNode? {
        var node = head
        var i = 1
        while (i < k) {
            node = node?.next
            i++
        }
        return node
    }
    fun reversalK(head: ListNode?, k: Int): ListNode? {
        val end = endK(head,k) ?: return head
        val node = reversalK(end.next, k)
        val rv = reversalN(head, k)
        head?.next = node
        return rv
    }

    fun testReversalK(head: ListNode?): ListNode? {

        val node = reversalK(head, 2)
        return node
    }
}

fun main() {

    val node1 = ListNode(1)
    val node2 = ListNode(2)
    val node3 = ListNode(3)
    val node4 = ListNode(4)
    val node5 = ListNode(5)

    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    val reversal = Reversal()
    //  reversal.simpleReversal(node1)?.print()
    //  reversal.reversalN(node1,4)?.print()

    //  reversal.reversalMN(node1,2,4)?.print()

    reversal.testReversalK(node1)?.print()


}