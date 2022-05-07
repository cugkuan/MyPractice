package letcode.list

class MergeTwoLists {
    /**
     * 合并链表
     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val node = ListNode(Int.MIN_VALUE)
        var p1 = list1
        var p2 = list2
        var p = node
        while (p1 != null && p2 != null) {
            if (p1.`val` <= p2.`val`) {
                p.next = p1
                p1 = p1.next
            } else {
                p.next = p2
                p2 = p2.next
            }
            p = p.next!!
        }
        if (p1 != null) {
            p.next = p1
        }
        if (p2 != null) {
            p.next = p2
        }
        return node.next
    }

    // 这里比较耗时，可以优化
    private fun getMinNode(choice: Array<ListNode?>): ListNode? {
        var index = -1
        var temp: ListNode? = null
        choice.forEachIndexed { i, listNode ->
            if (temp == null) {
                temp = listNode
                index = i
            } else if (listNode != null) {
                if (listNode.`val` < temp!!.`val`) {
                    temp = listNode
                    index = i
                }
            }
        }
        if (index != -1) {
            choice[index] = choice[index]?.next
        }
        return temp
    }

    /**
     * 合并很多链表
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        // 下面的代码，取最值的时候，效率比较低，可以使用优先队列保证有序性
        val choice = Array<ListNode?>(lists.size) { index ->
            lists[index]
        }
        val node = ListNode(Int.MIN_VALUE)
        var p = node
        while (true) {
            var min = getMinNode(choice)
            if (min == null) {
                break
            } else {
                p.next = min
                p = p.next!!
            }
        }
        return node.next
    }

    /**
     * 判断链表中是否有循环
     * https://leetcode-cn.com/problems/linked-list-cycle/
     *
     * 这道题真的很简单，就是跑圈圈,
     */
    fun hasCycle(head: ListNode?): Boolean {
        if (head?.next == null) {
            return false
        } else {
            var slow = head
            var fast = head
            while (true) {
                slow = slow?.next
                fast = fast?.next?.next
                if (fast == null) {
                    return false
                } else {
                    if (slow == fast) {
                        return true
                    }
                }
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/
     *
     * 判断链表入环的位置
     */
    fun detectCycle(head: ListNode?): ListNode? {
        if (hasCycle(head).not()) {
            return null
        } else {
            var first = head
            var slow = head
            var fast = head
            var count = 0
            while (true) {
                count = 0
                while (true) {
                    slow = slow?.next
                    fast = fast?.next?.next
                    if (slow == first || fast == first) {
                        count++
                    }
                    if (slow == fast) {
                        break
                    }
                }
                if (count >= 1) {
                    return first
                } else {
                    first = head?.next
                }
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/middle-of-the-linked-list/
     * 链表中间节点
     * 链表非空
     * 快慢指针
     */
    fun middleNode(head: ListNode): ListNode? {
//        // 下面的代码效率好低啊，属于暴力解法
//        var size = 0
//        var cur :ListNode? = head
//        while (cur?.next != null){
//            size++
//            cur = cur.next
//        }
//        val  target = if(size %2 == 0) size /2 else size/2 +1
//        var i = 0
//        cur = head
//        while (i < target){
//            cur = cur?.next
//            i++
//        }
//        return cur
        // 快慢指针，和环形异曲同工
        var slow: ListNode? = head
        var fast: ListNode? = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast?.next?.next
        }
        return slow
    }

    /**
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     *
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // 使用一个虚拟的路径，注意这里的技巧性
        val virtual = ListNode(Int.MIN_VALUE).apply {
            next = head
        }
        var index = n
        var fast: ListNode? = virtual
        while (index > 0) {
            fast = fast?.next
            index--
        }
        var slow: ListNode? = virtual
        while (fast?.next != null) {
            fast = fast?.next
            slow = slow?.next
        }
        slow?.next = slow?.next?.next
        return virtual.next
    }

    /**
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null
     *
     * 这个解法比较巧妙，二个链表，分别连接起来
     */
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        var first = headA
        var second = headB
        var flagA = 0
        var flagB = 0
        while (true) {
            if (first == second) {
                return first
            } else {
                first = first?.next
                second = second?.next
                if (first == null && flagA == 0) {
                    first = headB
                    flagA = 1
                }
                if (second == null && flagB == 0) {
                    second = headA
                    flagB = 1
                }
            }
        }
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 这个解法是比较低阶的算法
     */
    fun reverseList(head: ListNode?): ListNode? {
        // 下面的代码是非常传统的做法，
        var pre: ListNode? = null
        var cur = head
        while (cur?.next != null) {
            val next = cur?.next
            cur?.next = pre
            pre = cur
            cur = next
        }
        cur?.next = pre
        return cur
    }

    /**
     * 这个递归有点巧妙
     */
    fun reverseList2(head: ListNode?): ListNode? {
        return if (head?.next == null) {
            head
        } else {
            val last = reverseList2(head)
            head?.next?.next = head
            head?.next = null
            last
        }
    }

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/
     *
     */
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        var index = 2
        var begin: ListNode? = head
        while (index < left) {
            index++
            begin = head?.next
        }
         val last = reverseN(begin?.next,right-left +1)
        begin?.next = last
        return head
    }


    /**
     * 后驱节点，前n个元素的反转
     */
    var successor: ListNode? = null // 后驱节点
    fun reverseN(head: ListNode?, n: Int): ListNode? {
        if (n == 1) {
            successor = head?.next
            return head
        }
        val last = reverseN(head?.next, n-1)
        head?.next?.next = head
        head?.next = successor
        return last
    }

    fun testReverseN(head: ListNode?, n: Int){
        val node = reverseN(head,n)

        System.out.println("xxxx${node?.`val`}")
    }
}

fun main() {
    // [[1,4,5],[1,3,4],[2,6]]

//    val node1 = ListNode(1)
//    val node11  = ListNode(4)
//    val node12 = ListNode(5)
//
//    node1.next = node11
//    node11.next = node12
//
//    val node2 = ListNode(1)
//    val node21  = ListNode(3)
//    val node22 = ListNode(4)
//
//    node2.next = node21
//    node21.next = node22
//
//    val node3 = ListNode(2)
//    val node31  = ListNode(6)
//    node3.next = node31
//
//    MergeTwoLists().mergeKLists(Array(3) {
//        when (it) {
//            0 -> node1
//            1 -> node2
//            else -> node3
//        }
//    })

//    val node1 = ListNode(3)
//    val node11 = ListNode(2)
//    val node12 = ListNode(0)
//    val node13 = ListNode(-4)
//
////    node1.next = node11
////    node11.next = node12
////    node12.next = node13
////    node13.next = node11
//    node1.next = node1
//
//    println(MergeTwoLists().detectCycle(node1)?.`val`)


    val node1 = ListNode(1)
    val node2 = ListNode(2)
    val node3 = ListNode(3)
    val node4 = ListNode(4)
    val node5 = ListNode(5)

    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    MergeTwoLists().reverseBetween(node1,2,4)


 //   MergeTwoLists().reverseBetween(node1,2,4)




}