package question.Demo68文本左右对齐;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author y30016814
 * @since 2021/11/19 16:53
 */
public class Solution68 {
    public static void main(String[] args) {
        fullJustify20211119165356(new String[] {"This", "is", "an", "example", "of", "text", "justification."},
            16).forEach(System.out::println);
        // fullJustify20211119165356(new String[] {"What", "must", "be", "acknowledgment", "shall", "be"},
        //     16).forEach(System.out::println);
        String t = "Science  is  what we";
        List<String> collect = Arrays.stream(t.split(" ")).filter(s -> !s.equals("")).collect(Collectors.toList());
        System.out.println(collect);
        String x = formatList(collect, 20);
        System.out.println(x);
        System.out.println(t);
        System.out.println(x.length());
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        return null;
    }

    public static List<String> fullJustify20211119165356(String[] words, int maxWidth) {
        ArrayList<String> strings = new ArrayList<>();
        int temp = maxWidth;
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() <= temp) {
                tempList.add(word);
                temp -= word.length() + 1;
            } else {
                // 格式化list
                temp = maxWidth;
                strings.add(formatList(tempList, maxWidth));
                i--;
            }
        }
        if (!tempList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            tempList.forEach(s -> sb.append(s).append(" "));
            appendKong(sb, maxWidth - sb.length());
            strings.add(sb.toString());
        }
        return strings;
    }

    public static String formatList(List<String> tempList, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        if (tempList.size() == 1) {
            String str = tempList.get(0);
            sb.append(str);
            appendKong(sb, maxWidth - str.length());
        } else {
            // 1. 统计总list长度
            int sum = tempList.stream().mapToInt(String::length).sum();
            int kongGe = (maxWidth - sum) / (tempList.size() - 1);
            for (int i = 0; i < tempList.size() - 1; i++) {
                sb.append(tempList.get(i));
                appendKong(sb, kongGe);
            }
            sb.append(tempList.get(tempList.size() - 1));
            if (sb.length() < maxWidth) {
                for (int i = 0; i < maxWidth - sb.length(); i++) {
                    sb.insert(tempList.get(0).length(), " ");
                }
            }
        }
        tempList.clear();
        return sb.toString();
    }

    public static void appendKong(StringBuilder stringBuilder, int kong) {
        for (int i = 0; i < kong; i++) {
            stringBuilder.append(" ");
        }
    }

}
