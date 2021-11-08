package simulation.professional.d20211105;

import cn.hutool.core.util.RandomUtil;

/**
 * @author y30016814
 * @since 2021/11/5 9:09
 * http://3ms.huawei.com/km/blogs/details/10437493
 */
public class T1 {
    public static void main(String[] args) {
        String string = RandomUtil.randomString("x.", RandomUtil.randomInt((int) Math.pow(10, 5)));
        // String string = RandomUtil.randomString("x.", RandomUtil.randomInt(40));
        int cnt = RandomUtil.randomInt(string.length());
        System.out.println("string=" + string + ",cnt=" + cnt);
        System.out.println(solution(string, cnt));
        System.out.println(getMaxFreeMemoryLen(string, cnt));
        // System.out.println(solution("x.xxxxx....xx", 6));
        // System.out.println(getMaxFreeMemoryLen("x.xxxxx....xx", 6));
    }

    /**
     * 暴力
     * 可能超时~
     *
     * @param str
     * @param cnt
     * @return
     */
    private static int solution(String str, int cnt) {
        char[] chars = str.toCharArray();
        int max = 0;
        if (cnt == 0) {
            int temp = 0;
            for (char aChar : chars) {
                if (aChar == '.') {
                    temp++;
                } else {
                    max = Math.max(temp, max);
                    temp = 0;
                }
            }
            return Math.max(temp, max);
        }
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            max = Math.max(i + 1 - start, max);
            if (aChar == 'x') {
                int tempCnt = cnt - 1;
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[j] == 'x') {
                        if (tempCnt == 0) {
                            max = Math.max(j - start, max);
                            break;
                        }
                        tempCnt--;
                    }
                }
                if (tempCnt > 0) {
                    max = Math.max(chars.length - start, max);
                }
                start = i + 1;
            }
        }
        return max;
    }

    public static int getMaxFreeMemoryLen(String memory, int cnt) {
        //左指针
        int left = 0;
        //右指针
        int right = 0;
        //窗口内被占用内存的个数
        int window = 0;
        //释放窗口内的被占用内存的个口内被占用内存的个数数，所能形成的最大值
        int maxMemory = 0;
        while (right < memory.length()) {
            //当窗口内的被占用的个数小于阈值，且右指针小于memory的个数
            while (window <= cnt && right < memory.length()) {
                if (memory.charAt(right) == 'x') {
                    window++;
                }
                if (window <= cnt) {
                    maxMemory = Math.max(maxMemory, right - left + 1);
                }
                right++;
            }
            //左指针若遇到x，则把窗口内被占用内存的个数减少
            if (memory.charAt(left) == 'x') {
                window--;
            }
            left++;
        }
        return maxMemory;
    }
}
