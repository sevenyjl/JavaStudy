package practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 题目描述
 * 在美丽的尧山，有一个大广场，50周年校庆的时候Solo就在大广场上见证了史上最壮观的焰火。
 * 在广场上有一排方砖是有颜色的，被涂上红色或者绿色，从左到右排列。现在校方要求重新喷涂颜色，但不一定要每一块方砖都重新喷涂，因为校方的目的是：每一块红色的方砖都至少在绿色方砖的左边（也就是每一个红的左边不能有绿的），并且尽量喷涂最少的次数。
 * <p>
 * 解答要求
 * 时间限制：1000ms, 内存限制：100MB
 * 输入
 * 输入只有一行，包含一个字符串S，且只包含'R'(代表红色)或者'G'(代表绿色)。
 * 我们保证字符串S的长度L的范围是(0 < L < 50 )。
 * <p>
 * 输出
 * 输出需要重新喷涂的方砖的最少数量。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * RGRGR
 * 输出样例 1
 * <p>
 * 2
 * 提示样例 1
 * <p>
 * <p>
 * 提示
 * 样例中字符串S为RGRGR，则我们可以这么喷涂，即RGRGR喷成RRRGG（即将第二个字符喷成R，最后一个字符喷成G）或者RRRRR（即将两个G都喷成R），都是只需喷涂两个方砖，所以答案为2。
 * <p>
 * 我们再举个例子:若S为RRRGGGGG，则我们不需要在重新喷涂就已经满足“每一块红色的方砖都在绿色方砖的左边”的要求，所以答案将是0。
 * <p>
 * http://oj.rnd.huawei.com/problems/25/details
 */
public class Demo25_NO {
    public static void main(String[] args) {
        String sb = "GRRGRGRRRRRGRRRRRRGRRGRGRRRGRRRRRGRRRRGRGRGRRRRG";//12
        System.out.println(pent(sb));
        // System.out.println(pent(sb));
        // System.out.println(pent(sb));
        // Scanner scanner = new Scanner(System.in);
        // String s = scanner.nextLine();
        // System.out.println();
    }

    /**
     * 思路：
     *
     * @param str
     * @return
     */
    public static int pent(String str) {
        int green = 0;
        int red = 0;
        /**
         * 判断上一个砖块什么颜色
         */
        char last = ' ';
        int len = str.length();
        for (int i = 0; i < len; i++) {
            //如果当前是红色的，上一个是绿色的
            if (str.charAt(i) == 'R' && last == 'G') {
                //如果染成绿色的次数不少于染成红色的次数，那么染成红色的
                if (green + 1 >= red) {
                    last = 'R';
                    green = red;
                } else {
                    green++;
                    last = 'G';
                }
            } else if (str.charAt(i) == 'G') {
                /**
                 * 记录如果染成红色，需要染的次数
                 */
                red++;
                /**
                 * 实际先不染，保留绿色
                 */
                last = 'G';
            } else {
                last = str.charAt(i);
            }
        }
        return Integer.min(red, green);
    }

}
