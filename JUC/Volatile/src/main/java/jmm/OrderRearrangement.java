package jmm;

/**
 * 验证JMM指令重排【未完成】
 */
public class OrderRearrangement {

    public static void main(String[] args) {
        ResortSeqDemo resortSeqDemo = new ResortSeqDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                resortSeqDemo.method01();
            }, "thread " + i).start();
        }
    }


}

class ResortSeqDemo {
    int a= 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;
    }

    public void method02() {
        if(flag) {
            a = a + 5;
            System.out.println("reValue:" + a);
        }
    }
}