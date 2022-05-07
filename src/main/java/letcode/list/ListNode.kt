package letcode.list

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

/**
 * 打印
 */
fun ListNode.print(){
    val print = java.lang.StringBuilder()
    var cur:ListNode? = this
    while (cur != null){
        print.append(cur.`val`)
        print.append("-")
        cur = cur.next
    }
    println(print)
}