public class ArrayDeque<T> {
    // 两个私有化成员变量 用于指示当前的数组地址和已用长度
    private T[] items;
    private int size;
    // 两个私有化变量指示头指针和尾指针
    private int nextFirst;
    private int nextLast;

    //构造函数
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    // 由于空间利用率的限制，在移除元素的时候同样需要resize，所以加上一个factor参数
    private void resize(double factor) {
        int newCol = (int) (items.length * factor);
        T[] newArray = (T[]) new Object[newCol];
        // 将原数组中的复制过来 新数组从0开始
        int startPos = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i += 1) {
            newArray[i + 1] = items[(startPos + i) % items.length];
        }
        // 然后替换掉原来的items, nextFirst, nextLast
        items = newArray;
        nextFirst = 0;
        nextLast = size + 1;
    }

    public void addFirst(T item) {
        // 首先保证有空位置，没有空位置则resize
        if(size == items.length) {
            resize(2);
        }
        // 在下一个first位置插入
        items[nextFirst] = item;
        // 长度+1
        size += 1;
        // 更新nextFirst 由于是左移，如果原来是0，长度为8的话，那么更新后到了数组尾部7
        // 计算时应该是 (0+8-1)%8=7
        nextFirst = (nextFirst + items.length - 1) % items.length;
    }

    public void addLast(T item) {
        // 判断是否已满
        if (items.length == size) {
            resize(2);
        }
        // 插入新元素
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        // 空数组直接返回
        if(size == 0) {
            return ;
        }
        int startPos = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i += 1) {
            System.out.print(items[(startPos + i) % items.length] + " ");
        }
    }

    public T removeFirst() {
        // 空数组
        if (size == 0) {
            return null;
        }
        else {
            T retNode = items[(nextFirst + 1) % items.length];
            size -= 1;
            nextFirst = (nextFirst + 1) % items.length;
            if (size * 2 < items.length)
                resize(0.5);
            return retNode;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        else {
            T retNode = items[(nextLast - 1 + items.length) % items.length];
            size -= 1;
            nextLast = (nextLast - 1 + items.length) % items.length;
            if (size * 2 < items.length)
                resize(0.5);
            return retNode;
        }
    }

    public T get(int index) {
        if (size == 0) return null;
        int startPos = (nextFirst + 1) % items.length;
        return items[(startPos + index) % items.length];
    }
}