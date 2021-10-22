package question.Demo20有效的括号;

import java.util.HashMap;
import java.util.Stack;

public class Solution20 {
    public static void main(String[] args) {

//        System.out.println(isValid("()"));//true
//        System.out.println(isValid("()[]{}"));//true
//        System.out.println(isValid("(]"));//false
//        System.out.println(isValid("([)]"));//false
        System.out.println(isValid("({[]})"));//true
    }

    static HashMap<Character, Character> map = new HashMap<>();

    static {
        map.put(']', '[');
        map.put(')', '(');
        map.put('}', '{');
    }


    /**
     * 解答成功:
     * 执行耗时:1 ms,击败了99.08% 的Java用户
     * 内存消耗:36.3 MB,击败了83.16% 的Java用户
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == ']' || aChar == ')' || aChar == '}') {
                if (!stack.isEmpty()) {
                    if (!stack.pop().equals(map.get(aChar))) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                stack.add(aChar);
            }
        }
        return stack.isEmpty();
    }
}
