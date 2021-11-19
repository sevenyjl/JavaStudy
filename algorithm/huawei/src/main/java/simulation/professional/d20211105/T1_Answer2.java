package simulation.professional.d20211105;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y30016814
 * @since 2021/11/10 17:35
 * http://3ms.huawei.com/km/blogs/details/11249843
 */
public class T1_Answer2 {
    public static int getMaxRopeLength(int target, int[] ropeList) {
        int left = 0;
        int right = getMinLength(ropeList);
        List<Integer> res = new ArrayList<>();
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid == 0) {
                return 0;
            }
            int num = getNum(ropeList, mid);
            if (num == target) {
                res.add(mid);
                left = mid+1;
            } else if (num > target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (res.isEmpty()) {
            return 0;
        }
        return getMaxNum(res);
    }

    private static int getMinLength(int[] ropeList) {
        int min = Integer.MAX_VALUE;
        for (int rope : ropeList) {
            min = Math.min(rope, min);
        }
        return min;
    }

    private static int getMaxNum(List<Integer> res) {
        int maxNum = 0;
        for (Integer re : res) {
            maxNum = Math.max(maxNum, re);
        }
        return maxNum;
    }

    private static int getNum(int[] ropeList, int mid) {
        int num = 0;
        for (int rope : ropeList) {
            num += rope / mid;
        }
        return num;
    }
}
