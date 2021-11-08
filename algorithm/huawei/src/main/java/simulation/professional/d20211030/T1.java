package simulation.professional.d20211030;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author y30016814
 * @since 2021/10/30 15:03
 */
public class T1 {
    public static void main(String[] args) {
        Run run = new Run();
        run.push(6);
        run.push(4);
        run.push(5);
        run.push(1);
        run.pop();
        run.pop();
        run.push(11);
        run.push(2);
        run.pop();
        run.peekMedian();
    }

}

class Run {
    StackSystem2 stackSystem2;
    StackSystem stackSystem;

    public Run() {
        stackSystem2 = new StackSystem2();
        stackSystem = new StackSystem();
    }

    public void push(int number) {
        stackSystem2.push(number);
        stackSystem.push(number);
    }

    public void pop() {
        int pop2 = stackSystem2.pop();
        int pop = stackSystem.pop();
        System.out.printf("pop2=%s,pop=%s\n", pop2, pop);
    }

    public void peekMedian() {
        int peekMedian2 = stackSystem2.peekMedian();
        int peekMedian = stackSystem.peekMedian();
        System.out.printf("peekMedian2=%s,peekMedian=%s\n", peekMedian2, peekMedian);
    }
}

class StackSystem {
    private ArrayList<Integer> integers;

    public StackSystem() {
        integers = new ArrayList<>();
    }

    public void push(int number) {
        integers.add(number);
    }

    public int pop() {
        if (integers.size() > 0) {
            return integers.remove(integers.size() - 1);
        }
        return -1;
    }

    public int peekMedian() {
        if (integers.size() > 0) {
            ArrayList<Integer> temp = new ArrayList<>(integers);
            Collections.sort(temp);
            int integer;
            if (integers.size() % 2 == 0) {
                // 若N是偶数，则中值定义为该序列中第N/2个小的值
                integer = integers.size() / 2;
            } else {
                // 若N是奇数，则中值定义为该序列中第(N+1)/2个小的值。
                integer = (integers.size() + 1) / 2;
            }
            return temp.get(integers.size() - integer);
        }
        return -1;
    }
}

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
