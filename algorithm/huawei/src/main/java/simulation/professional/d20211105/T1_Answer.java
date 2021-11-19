package simulation.professional.d20211105;

import java.util.Arrays;

/**
 * @author y30016814
 * @since 2021/11/10 17:35
 * http://3ms.huawei.com/km/blogs/details/11248617
 */
public class T1_Answer {
    public static int getMaxRopeLength(int target, int[] ropeList) {
        int left = 1;
        int right = Arrays.stream(ropeList).sum() / target;
        int ropeLength = 0;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int currentT = 0;
            for (int rope : ropeList) {
                currentT += rope / middle;
            }
            if (currentT < target) {
                right = middle - 1;
            } else {
                ropeLength = Math.max(ropeLength, middle);
                left = middle + 1;
            }
        }
        return ropeLength;
    }
}
