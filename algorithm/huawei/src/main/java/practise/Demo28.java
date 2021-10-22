package practise;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * Solo和koko是两兄弟，妈妈给了他们一大袋糖，每块糖上都有自己的重量。现在他们想要将这些糖分成两堆。分糖的任务当然落到了大哥Solo的身上，然而koko要求必须两个人获得的糖的总重量“相等”（根据Koko的逻辑），要不然就会哭的。
 * 非常不幸的是，koko还非常小，并且他只会先将两个数转成二进制再进行加法，而且总会忘记进位。如当12（1100）加5（101）时：
 * <p>
 * 1100
 * +0101
 * ——-
 * 1001
 * 于是koko得到的计算结果是9（1001）。
 * <p>
 * 此外还有一些例子：
 * <p>
 * 5 + 4 = 1
 * 7 + 9 = 14
 * 50 + 10 = 56
 * （事实上，这正是异或运算:12^5=9,5^4=1…）
 * 现在Solo非常贪婪，他想要尽可能使自己得到的糖的总重量最大，且不让koko哭。
 * <p>
 * 解答要求
 * 时间限制：1000ms, 内存限制：100MB
 * 输入
 * 输入的第一行是一个整数N(2 ≤ N ≤ 15)，表示有袋中多少块糖。第二行包含N个用空格分开的整数Ci (1 ≤ Ci ≤ 106)，表示第i块糖的重量。
 * <p>
 * 输出
 * 如果能让koko不哭，输出Solo所能获得的糖的总重量，否则输出“NO”。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 3
 * 3 5 6
 * 输出样例 1
 * <p>
 * 11
 * 提示样例 1
 * <p>
 * <p>
 * 输入样例 2 复制
 * <p>
 * 5
 * 1 2 3 4 5
 * 输出样例 2
 * <p>
 * NO
 * 提示样例 2
 * <p>
 * <p>
 * 输入样例 3 复制
 * <p>
 * 8
 * 7258 6579 2602 6716 3050 3564 5396 1773
 * 输出样例 3
 * <p>
 * 35165
 * 提示样例 3
 * <p>
 * <p>
 * 提示
 * Sample1中，三块糖重量为3、5、6，因为5(101)+6(110)=3(11)，所以Solo拿走了重为5和6的糖，koko则得到了重为3的糖。
 * <p>
 * Sample2中五块糖，无论如何分，都无法满足koko的要求，所以NO。
 * <p>
 * Sample3中Solo拿走了前面7块糖，一共重35165。
 */
public class Demo28 {
    public static void main(String[] args) {
        System.out.println(fen(new int[] {3, 5, 6}));
        System.out.println(fen(new int[] {1, 2, 3, 4, 5}));
        System.out.println(fen(new int[] {7258, 6579, 2602, 6716, 3050, 3564, 5396, 1773}));

        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] candyArray = new int[count];
        for (int i = 0; i < count; i++) {
            candyArray[i] = scanner.nextInt();
        }
        int fen = fen(candyArray);
        System.out.println(fen == -1 ? "NO" : fen);

    }

    /**
     * 从math意义上，如果可以分成2份，2份的异或结果相等，则必然所有的异或结果为0。因此如果能拿走，则全部拿走。 因此算法：全体异或，如果结果不为0，则显示NO，否则，哥哥拿走全部
     *
     * @param ints
     * @return
     */
    public static int fen(int[] ints) {
        Arrays.sort(ints);
        int result = ints[0];
        int count = 0;
        for (int i = 1; i < ints.length; i++) {
            count += ints[i];
            result = result ^ ints[i];
        }
        return result == 0 ? count : -1;
    }

}
