package simulation.professional.d20211019;

import java.util.Arrays;

/**
 * 一个机房里面有N个位置可以放N个服务器，每个服务器都有一个标签，标签为正整数1~N，且有可能有重复。输入一个标签列表（无序的），
 * 求最少移动多少个服务器可以让标签列表有序
 * <p>
 * <p>
 * <p>
 * 输入样例：[1,1,2,3,5,4]
 * <p>
 * 输出样例：2
 * <p>
 * 输出样例说明：互换标签为5和4的服务器，即可使得标签列表有序。
 */
public class T1 {
    public static void main(String[] args) {
        System.out.println(minTrans(new int[]{1, 1, 2, 3, 5, 4}));//2
        System.out.println(minTrans(new int[]{1, 1, 2, 6, 5, 4}));//2
    }

    public static int minTrans(int[] trans) {
        int[] clone = trans.clone();
        Arrays.sort(trans);
        int count = 0;
        for (int i = 0; i < trans.length; i++) {
            if (trans[i] != clone[i]) {
                count++;
            }
        }
        return count;
    }
}