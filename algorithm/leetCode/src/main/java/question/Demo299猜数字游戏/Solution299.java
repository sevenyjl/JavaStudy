package question.Demo299猜数字游戏;

import java.util.ArrayList;

/**
 * @author y30016814
 * @since 2021/11/8 10:54
 */
public class Solution299 {
    public static void main(String[] args) {
        // System.out.println(getHint("1123", "0111"));
        System.out.println(getHint("1122", "1222"));//3A0B
        // System.out.println(getHint("1807", "7810"));
        // System.out.println(getHint("1", "0"));
        // System.out.println(getHint("1", "1"));
    }

    /**
     * 对于公牛，需要满足数字和确切位置都猜对。我们可以遍历secret 和 guess，统计满足secret[i]=guess[i] 的下标个数，即为公牛的个数。
     * 对于奶牛，需要满足数字猜对但是位置不对。根据题目所述的「这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字」，由于多余的数字无法匹配，
     * 对于 0 到 9 的每位数字，应取其在 secret 和 guess 中的出现次数的最小值。将每位数字出现次数的最小值累加，即为奶牛的个数。
     */
    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int[] cntS = new int[10];
        int[] cntG = new int[10];
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++bulls;
            } else {
                ++cntS[secret.charAt(i) - '0'];
                ++cntG[guess.charAt(i) - '0'];
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; ++i) {
            cows += Math.min(cntS[i], cntG[i]);
        }
        return bulls + "A" + cows + "B";
    }

    /**
     * 不成功：因为题都理解不到。。。。
     *
     * @param secret
     * @param guess
     * @return
     */
    public static String getHint1(String secret, String guess) {
        int a = 0;
        int b = 0;
        ArrayList<Character> temp = new ArrayList<>();
        for (int i = 0; i < secret.length(); i++) {
            temp.add(secret.charAt(i));

        }
        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);
            char charAt = guess.charAt(i);
            if (c == charAt) {
                a++;
                temp.remove((Character) c);
            } else if (temp.contains(charAt)) {
                b++;
                temp.remove((Character) charAt);
            }
        }
        return a + "A" + b + "B";
    }
}
