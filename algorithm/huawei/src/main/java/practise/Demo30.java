package practise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一个只包含大写英文字母的字符串S，要求你给出对S重新排列的所有不相同的排列数。
 * 如：S为ABA，则不同的排列有ABA、AAB、BAA三种。
 * <p>
 * 解答要求
 * 时间限制：5000ms, 内存限制：100MB
 * 输入
 * 输入一个长度不超过10的字符串S，我们确保都是大写的。
 * <p>
 * 输出
 * 输出S重新排列的所有不相同的排列数（包含自己本身）。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * ABA
 * 输出样例 1
 * <p>
 * 3
 * 提示样例 1
 * <p>
 * <p>
 * 输入样例 2 复制
 * <p>
 * ABCDEFGHHA
 * 输出样例 2
 * <p>
 * 907200
 * 提示样例 2
 * <p>
 * <p>
 * 输入样例 3 复制
 * <p>
 * AABBCC
 * 输出样例 3
 * <p>
 * 90
 * 提示样例 3
 */
public class Demo30 {
    public static void main(String[] args) {
        System.out.println("aabc");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            int count = calc(str);
            System.out.println(count);
        }
    }

    private static Map<Character, Integer> putRepeatCount(String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        return map;
    }

    /**
     * 这个公式怎么得来的？？？？？若n由多个集合n1, n2, n3 ….组成，则n的全排列 = n! / (n1! * n2! * n3! …)
     * 推导过程：
     * 1. 假设全部是非重复的n个，输出结果是n的阶乘
     * 2. 假设有n个A 其余都是一个字符有一下结果（len=输入字符串长度，r=总共的个数）
     * 2个A时：    len r
     * A A          2 1
     * A A B        3 3
     * A A B C      4 12
     * 3个A时：
     * A A A        3 1
     * A A A B      4 4
     * 自己推导发现有个规律 就是 len!/ a的个数的!
     * 3. 假设只有AB是多个且个数一致，
     * -             len r
     * AABB时：        4 6
     * AAABBB时：      6 20
     * 利用阶乘的思想推导一下
     * 假设：4!/2!-->12*x=6--->x=2 又等于2!
     * -    6!/3!-->120*x=-->x=6 又等于3!
     * -    故：可能公式就是=len!/(na!*nb!)
     * 验证AABBCC-->len=6,na=2,nb=2,nc=2
     * 90=6!/(2!*2!*2!)=720/8=90,故公式推导正确
     */
    private static int calc(String str) {
        Map<Character, Integer> map = putRepeatCount(str);
        int sum = 1;
        for (Character character : map.keySet()) {
            sum *= factorial(map.get(character));
        }
        return factorial(str.length()) / sum;
    }

    private static int factorial(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }
}
