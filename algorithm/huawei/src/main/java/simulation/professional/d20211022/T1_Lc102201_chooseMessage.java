package simulation.professional.d20211022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 功能说明
 *
 * @author x00501103
 * @version 2021-2021 v1.0
 * @since 2021/10/22 19:50
 * C版权所有 (c) 华为技术有限公司 2021-2021
 */
public class T1_Lc102201_chooseMessage {
    public static void main(String[] args) {
        String messages = "ABBCDCDAB";
        String keys = "BAC";
        long result = chooseMessage(messages, keys);
        System.out.println(result);
    }

    public static long chooseMessage(String messages, String keys) {
        if (keys.length() > messages.length()) {
            return 0L;
        }
        // 对keys进行字母次数统计
        Set<Character> keysSet = getKeySet(keys);
        // 遍历一遍messages,使用滑窗
        return getResult(messages, keysSet);
    }

    private static Set<Character> getKeySet(String keys) {
        Set<Character> keysSet = new HashSet<>();
        for (char currKey : keys.toCharArray()) {
            keysSet.add(currKey);
        }
        return keysSet;
    }

    private static long getResult(String messages, Set<Character> keysSet) {
        int left = 0;
        long result = 0L;
        char[] messagesChar = messages.toCharArray();
        Map<Character, Integer> messageCount = new HashMap<>();
        for (int i = 0; i < messagesChar.length; i++) {
            updateMessageCount(keysSet, messagesChar[i], messageCount);
            // 判定当前计数值是否已经包含所有字母
            while (messageCount.size() == keysSet.size()) {
                // 如果此时，右侧还没有到达结尾的话,此时long的值要加上还有多少个字符没统计
                result = result + messagesChar.length - i;
                // 把当前最左侧的字母扔掉继续循环
                removeMessageCount(keysSet, messagesChar[left], messageCount);
                left++;
            }
        }
        return result;
    }

    private static void removeMessageCount(Set<Character> keysSet, char leftChar1,
        Map<Character, Integer> messageCount) {
        char leftChar = leftChar1;
        if (keysSet.contains(leftChar)) {
            int leftCount = messageCount.get(leftChar) - 1;
            if (leftCount == 0) {
                // 为0时要remove掉，保证while条件里size的判定效果
                messageCount.remove(leftChar);
            } else {
                messageCount.put(leftChar, leftCount);
            }
        }
    }

    private static void updateMessageCount(Set<Character> keysSet, char currChar1,
        Map<Character, Integer> messageCount) {
        char currChar = currChar1;
        // 是keys里的字母的话加1,进行map计数
        if (keysSet.contains(currChar)) {
            messageCount.put(currChar, 1 + messageCount.getOrDefault(currChar, 0));
        }
    }
}