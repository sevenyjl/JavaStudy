package simulation.work.d20211019;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串S与一个索引列表A。
 * 例如，如果这个列表是[“time”, “me”, “bell”]，我们就可以将其表示为S = "time#bell#“和indexes = [0, 2, 5]。
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到”#"结束，来恢复我们之前的单词列表。
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * 输入：words = ["time", "me", "bell"]
 * 输出：10
 * 说明：S = “time#bell#” ， indexes = [0, 2, 5]。
 * 提示：
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母。
 */
public class T3 {
    public static void main(String[] args) {
        System.out.println(read(new String[] {"time", "me", "bell"}));
        System.out.println(read(new String[] {"t"}));
        System.out.println(read(new String[] {"me", "time"}));//5

    }

    public static int read(String[] strings) {
        HashSet<String> good = new HashSet<>(Arrays.asList(strings));
        for (String string : strings) {
            for (int i = 1; i < string.length(); i++) {
                good.remove(string.substring(i));
            }
        }
        return good.stream().mapToInt(s -> s.length() + 1).sum();
    }
}
