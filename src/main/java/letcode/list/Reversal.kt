package letcode.list

/**
 * 反转
 */
class Reversal {

    /**
     *  简单的反转
     */
    fun simpleReversal(head: ListNode?): ListNode? {
        if (head?.next == null){
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
    private var endPoint:ListNode? = null
    fun  reversalN(head: ListNode?,n:Int):ListNode?{
        if (n == 1){
            endPoint = head?.next
            return head
        }
        val last = reversalN(head?.next,n-1)
        head?.next?.next = head
        head?.next = endPoint
        return last
    }
}

fun main(){

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
    reversal.reversalN(node1,4)?.print()


}