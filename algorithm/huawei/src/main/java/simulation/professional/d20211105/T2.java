package simulation.professional.d20211105;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author y30016814
 * @since 2021/11/12 17:37
 * JAVA专业级科目一1105
 * http://3ms.huawei.com/km/blogs/details/11249843
 * http://3ms.huawei.com/km/blogs/details/11248617
 */
public class T2 {
    public static void main(String[] args) {
        System.out.println(transformationTable(new String[] {
            "line1 col1 DERRR",
            "line1 col3",
            "line1 col2 A",
            "line2 col3 HH",
            "line2 col1",
            "line2 col2 FFF",
            "line3 col3 A",
            "line3 col2 AB",
            "line3 col1 A",
        }));
    }

    public static String transformationTable(String[] tableInfo) {
        Map<String, Map<String, String>> lineMap = new HashMap<>();
        for (String s : tableInfo) {
            String[] split = s.split(" ");
            Map<String, String> orDefault = lineMap.getOrDefault(split[0], new HashMap<>());
            if (split.length == 2) {
                orDefault.put(split[1], "");
            } else {
                orDefault.put(split[1], split[2].trim());
            }
            lineMap.put(split[0], orDefault);
        }
        int size = lineMap.size();
        List<List<String>> lists = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Map<String, String> colMap = lineMap.get("line" + i);
            ArrayList<String> strings = new ArrayList<>();
            for (int j = 1; j <= colMap.size(); j++) {
                strings.add(colMap.get("col" + j));
            }
            lists.add(strings);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lists.get(0).size(); i++) {
            int max = 0;
            for (List<String> list : lists) {
                max = Math.max(list.get(i).length(), max);
            }
            result.append("+-");
            for (int j = 0; j <= max; j++) {
                result.append("-");
            }
        }
        result.append("+\n");
        String temp = result.toString();
        String[] split = temp.split("\\+");
        int index = 1;
        for (List<String> list : lists) {
            while (index < split.length - 1) {
                result.append("|").append(parse(" " + list.get(index - 1), split[index].length()));
                index++;
            }
            result.append("|").append("\n");
            index = 1;
        }
        result.append(temp);
        return result.toString();
    }

    private static String parse(String s, int len) {
        StringBuilder sBuilder = new StringBuilder(s);
        for (int i = sBuilder.length(); i < len; i++) {
            sBuilder.append(" ");
        }
        return sBuilder.toString();
    }
}
