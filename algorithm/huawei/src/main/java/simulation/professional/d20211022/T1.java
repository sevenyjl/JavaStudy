package simulation.professional.d20211022;

import java.util.HashSet;

/**
 * @author y30016814
 * @since 2021/11/10 14:
 * 20211022-Java科目一专业级
 * http://3ms.huawei.com/km/blogs/details/11142343
 */
public class T1 {

    public static void main(String[] args) {
        String messages = "OIOIONOINASDFASDWQONOIOIONOINASDFASDWQNASDOIONOINASDFASDWQFASDWQOIOIONOINASDFASDWQONOINASDFASOIONOINASDFASDWQDWQOIONOINASDFASDWQ";
        String keys = "OIONOINASDFASDWQ";
        System.out.println(getMaxTarget(messages, keys));
        System.out.println(T1_Lc102201_chooseMessage.chooseMessage(messages, keys));

    }

    private static int getMaxTarget(String messages, String keys) {
        int result = 0;
        // 1.转换set
        HashSet<Character> characters = new HashSet<>();
        for (int i = 0; i < keys.length(); i++) {
            characters.add(keys.charAt(i));
        }
        // 2.遍历messages
        for (int i = 0; i < messages.length(); i++) {
            // 查询从当前开始有多少个子字符串满足
            result += find(messages, i, new HashSet<>(characters));
        }
        return result;
    }

    private static int find(String messages, int start, HashSet<Character> characters) {
        int needFind = characters.size();
        for (int i = start; i < messages.length(); i++) {
            char c = messages.charAt(i);
            if (characters.remove(c)) {
                needFind--;
            }
            if (needFind == 0) {
                return messages.length() - i;
            }
        }
        return 0;
    }
}
