package algorithm.base;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode generateList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode back = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            ListNode front = new ListNode(arr[i]);
            front.next = back;
            back = front;
        }
        return back;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(val);
        ListNode nextNode = this.next;
        while (nextNode != null) {
            str.append("->").append(nextNode.val);
            nextNode = nextNode.next;
        }
        return str.toString();
    }
}
