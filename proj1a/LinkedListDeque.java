public class LinkedListDeque<T> {

    /*member variables*/
    private class DequeNode {
        public T item;
        public DequeNode previous;
        public DequeNode next;

        public DequeNode(T x, DequeNode pre, DequeNode n) {
            item = x;
            next = n;
            previous = pre;
        }
    }

    /** the head of the deque */
    private DequeNode sentinel;
    private int size;
    private DequeNode last;

    /*constructor. constructor will be only used when the whole LinkedListDeque was created. */
    public LinkedListDeque() {
        T tmp = (T) new Object();
        sentinel = new DequeNode(tmp, null, null);
        size = 0;
        last=sentinel;
    }

    public LinkedListDeque(T item) {
        sentinel = new DequeNode(item, null, null);
        sentinel.next = new DequeNode(item, null, null);
        sentinel.next.previous = sentinel;
        size = 1;
        last=sentinel.next;
    }

    /* add an item of type T to the front of the deque*/
    public void addFirst(T item) {
        size += 1;
        DequeNode newNode = new DequeNode(item, sentinel, sentinel.next);
        if(sentinel.next != null) //原来不是空的情况
            sentinel.next.previous = newNode;
        sentinel.next=newNode;
        // 如果原来的last节点是sentinel 说明是空 更新last节点
        if(last == sentinel)
            last = newNode;
    }

    /*add an item of type T to the back of the deque*/
    public void addLast(T item) {
        DequeNode newNode = new DequeNode(item, last, null);
        last.next = newNode;
        last = newNode;
        size += 1;
    }

    /*return true if deque isempty, false otherwise*/
    public boolean isEmpty() {
        return size == 0;
    }

    /*return the number of items in the deque*/
    public int size() {
        return size;
    }

    /*print the items in the deque from first to last, separated by a space*/
    public void printDeque() {
        if(this.isEmpty()) {
            return;
        }
        for(DequeNode i = sentinel.next; i != null; i = i.next) {
            System.out.print(i.item + " ");
        }
    }

    /*remove and return the item at the front of the deque, return null if no item exists*/
    public T removeFirst() {
        if(size == 0) return null;
        // 首先把要返回的节点保存下来
        DequeNode retNode = sentinel.next;
        //接下来将哨兵的next和返回节点的next连接好 注意处理只有一个节点的情况
        if (size == 1) {
            // 移除之后就只剩下哨兵了
            sentinel.next=null;
            last=sentinel;
        }
        else {
            retNode.next.previous = sentinel;
            sentinel.next = retNode.next;
        }
        // size-1
        size -=1;
        return retNode.item;
    }

    /*remove and return the item at the back of the deque, return null if no item exists*/
    public T removeLast() {
        if(sentinel.next == null)
            return null;
        size -= 1;
        DequeNode targetNode = last;
        targetNode.previous.next=null;
        last=targetNode.previous;
        return targetNode.item;
    }

    /*get the item at the given index, where 0 is the front*/
    public T get(int index) {
        if (index >= size) {
            System.out.println("Out of index.");
        }
        DequeNode p = sentinel.next;
        int cur=0;
        while(cur < index) {
            p = p.next;
            cur += 1;
        }
        return p.item;
    }


}
