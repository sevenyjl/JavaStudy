package Demo06Z字形变换;

import java.util.ArrayList;

/*将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：


P   A   H   N
A P L S I I G
Y   I   R

 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

 请你实现这个将字符串进行指定行数变换的函数：


string convert(string s, int numRows);



 示例 1：


输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"

示例 2：


输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I


 示例 3：


输入：s = "A", numRows = 1
输出："A"




 提示：


 1 <= s.length <= 1000
 s 由英文字母（小写和大写）、',' 和 '.' 组成
 1 <= numRows <= 1000

 Related Topics 字符串
 👍 1235 👎 0*/
public class Z字形变换 {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        //PAHNAPLSIIGYIR
        //PAHNAPLSIIGYIR
//        System.out.println(convert(s, numRows));
        s = "0123456789";
        numRows = 4;
//        System.out.println(convert(s, numRows));
        s = "PAYPALISHIRING";
        //PINALSIGYAHRPI
        //PINALSIGYAHRPI
        numRows = 4;
//        System.out.println(convert(s, numRows));
        s = "PAYPALISHIRING";
        /**
         * P Y P L
         * A P A I
         */
        //
        //PYAIHRNAPLSIIG
        numRows = 2;
        System.out.println(convert(s, numRows));
        s = "A";
        numRows = 1;
        System.out.println(convert(s, numRows));
    }

    /**
     * 执行耗时:14 ms,击败了24.75% 的Java用户
     * 内存消耗:39.2 MB,击败了20.91% 的Java用户
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        int cycle = numRows + numRows - 2;
        if (cycle == 0) {
            return s;
        }
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            temp.add("");
        }
        char[] chars = s.toCharArray();
        int index = 0;
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (i % cycle == 0) {
                flag = false;
                index = 0;
            } else if ((i + numRows - 2) % cycle == 0) {
                flag = true;
                index = numRows - 2;
            }
            if (flag) {
                String sb = temp.get(index);
                temp.set(index, sb + aChar);
                index--;
            } else {
                String sb = temp.get(index);
                temp.set(index, sb + aChar);
                index++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s1 : temp) {
            stringBuilder.append(s1);
        }
        return stringBuilder.toString();
    }
}
