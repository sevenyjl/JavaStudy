http://3ms.huawei.com/km/blogs/details/7725341?l=zh-cn

# 12.27可信编程专业级题解（java）

第一题：

堆栈是一种经典的后进先出的线性结构，相关的操作主要有“入栈”（在堆栈顶插入一个元素）和“出栈”（将栈顶元素返回并从堆栈中删除）。现在请你实现一种特殊的功能，赋予它“查询中值”的操作，规则如下：对于含N个元素堆栈结构，若N是偶数，则中值定义为该序列中第N/2个小的值；若N是奇数，则中值定义为该序列中第(N+1)/2个小的值。1 <= value <= 10^6，最多会对 Pop、Push、PeekMedian进行 10^5 次调用。性能用例较少。



（1）Push(value) - 入栈，堆栈添加新的元素value

（2）Pop() - 出栈，若堆栈非空，返回栈顶元素；否则返回 -1

（3）PeekMedian() - 计算中值，若堆栈非空，返回当前堆栈的中值；否则返回-1（注意：仅仅是查值，不需要弹出）。



例1：

输入：["StackSystem","Push","Push","Push","Push","Pop","PeekMedian"]

[ [], [4], [3], [5], [6], [], [] ]

输出：[null, null, null, null, null, 6, 4]

解释：StackSystem s = StackSystem()

s.Push(4)       // 堆栈增加元素4，当前堆栈[4]

s.Push(3)       // 堆栈增加元素3，当前堆栈[4,3]

s.Push(5)       // 堆栈增加元素5，当前堆栈[4,3,5]

s.Push(6)       // 堆栈增加元素6，当前堆栈[4,3,5,6]

s.Pop()          // 返回栈顶元素6, 剩余堆栈[4,3,5]

s.PeekMedian() // 当前堆栈[4,3,5], (3+1)/2=2，中值是第2小的元素，故返回4



例2：

输入：["StackSystem","Pop","PeekMedian","Push","Push","Push","Push","Push","PeekMedian"]

[ [], [], [], [3], [4], [4], [5], [5], [] ]

输出：[null, null, null, null, null, 6, 4]

解释：StackSystem s = StackSystem()

s.Pop()          // 当前堆栈为空，返回-1

s.PeekMedian() // 当前堆栈为空，返回-1

s.Push(3)       // 堆栈增加元素3，当前堆栈[3]

s.Push(4)       // 堆栈增加元素4，当前堆栈[3,4]

s.Push(4)       // 堆栈增加元素4，当前堆栈[3,4,4]

s.Push(5)       // 堆栈增加元素5，当前堆栈[3,4,4,5]

s.Push(5)       // 堆栈增加元素5，当前堆栈[3,4,4,5,5]

```java
class StackSystem2 {

    public Deque<Integer> list;

    public StackSystem2() {
        list = new LinkedList<>();
    }

    public void push(int val) {
        list.offer(val);
    }

    public int pop() {
        if (list.size() < 1) {
            return -1;
        }
        return list.removeLast();
    }

    public int peekMedian() {
        if (list.size() < 1) {
            return -1;
        }
        int k = (list.size() + 1) / 2;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int x : list) {
            pq.offer(x);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }
}
```