package simulation.professional.d20211105;

/**
 * http://3ms.huawei.com/km/blogs/details/11249867
 */
public class T1_Answer3 {
    public static int getMaxRopeLength(int target, int[] ropeList) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < ropeList.length; i++) {
            sum += ropeList[i];
            max = Math.max(ropeList[i], max);
        }
        if (sum < target) {
            return 0;
        }
        if (sum == target) {
            return 1;
        }
        int res = binarySearch(0, max, ropeList, target);
        return res;
    }

    static int binarySearch(int start, int end, int[] ropeList, int target) {
        while (start <= end) {
            int count = 0;
            int mid = start + (end - start) / 2;
            for (int i = 0; i < ropeList.length; i++) {
                int len = ropeList[i];
                while (len >= mid) {
                    count++;
                    len -= mid;
                }
            }
            if (count == target) {
                start = mid + 1;
            } else if (count < target) {
                end = mid - 1;
            } else if (count > target) {
                start = mid + 1;
            }
        }
        return start - 1;
    }

}