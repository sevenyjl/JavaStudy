package simulation.professional.d20210305;

/**
 * @author y30016814
 * @since 2021/11/5 11:04
 * http://3ms.huawei.com/km/blogs/details/9959105
 * http://3ms.huawei.com/km/blogs/details/10437493
 * http://3ms.huawei.com/km/blogs/details/9997675
 * 类似题目 https://leetcode-cn.com/problems/ways-to-split-array-into-three-subarrays/
 * <p>
 * 某仓库墙边有一排货箱，按从左至右的顺序把每个货箱的重量记录在数组 boxes 中。
 * <p>
 * 管理员需要整理所有货箱，要求如下：
 * <p>
 * 将若干 位置连续 的货箱堆起，从左至右整理成三堆，每一堆至少有一个货箱；
 * 三堆货箱重量满足条件：左边一堆货箱重量 <= 中间一堆货箱重量 <= 右边一堆货箱重量。
 * 请返回管理员共有多少种满足要求的整理方案；如果无可行方案，返回 0。
 * <p>
 * 注意：答案需以 1e9 + 7 (1000000007) 为底取余，如：计算初始结果为：1000000008，则取余后返回 1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：boxes = [1, 1, 2, 1, 4]
 * <p>
 * 输出：5
 * <p>
 * 解释：有五种整理方法：
 * <p>
 * 整理成 [1, 1, 7], 7 = boxes[2] + boxes[3] + boxes[4] = 2 + 1 + 4，由右侧 3 个货箱堆成一堆；
 * 整理成 [1, 3, 5];
 * 整理成 [1, 4, 4];
 * 整理成 [2, 2, 5];
 * 整理成 [2, 3, 4]。
 * 示例 2：
 * <p>
 * 输入：boxes = [3, 2, 3]
 * <p>
 * 输出：0
 * <p>
 * 解释：无满足要求的整理方案。
 * <p>
 * 提示：
 * <p>
 * 3 <= boxes.length <= 10^6
 * 1 <= boxes[i] <= 10^6
 * <p>
 * 题都看不懂。。。。。。。~~~~~~！！！！！！
 */
public class T3_NO {
    public static void main(String[] args) {
        System.out.println(countBoxes(new int[] {1, 1, 2, 1, 4}));
    }


    public static int countBoxes2(int[] boxes) {
        int[] sum = new int[boxes.length];
        sum[0] = boxes[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] += sum[i - 1] + boxes[i];
        }
        int total = sum[boxes.length - 1];
        int secondMin = 0;
        int secondMax = 0;
        int count = 0;
        for (int i = 0; i < boxes.length; i++) {
            while (secondMin < boxes.length && sum[secondMin] < 2 * sum[i]) {
                secondMin++;
            }
            while (secondMax < boxes.length && 2 * sum[secondMax] <= sum[i] + total) {
                secondMax++;
            }
            if (secondMax > secondMin) {
                count = (secondMax - secondMin + count) % 1000000007;
            }
        }
        return count;
    }
    /**
     * 暴力
     *
     * @param boxes
     * @return
     */
    public static int countBoxes(int[] boxes) {
        int count = 0;
        for (int firstEnd = 0; firstEnd < boxes.length - 2; firstEnd++) {
            for (int secondEnd = firstEnd + 1; secondEnd < boxes.length - 1; secondEnd++) {
                long firstWeight = sum(boxes, 0, firstEnd);
                long secondWeight = sum(boxes, firstEnd + 1, secondEnd);
                long thirdWeight = sum(boxes, secondEnd + 1, boxes.length - 1);
                if (firstWeight <= secondWeight && secondWeight <= thirdWeight) {
                    count++;
                }
            }
        }
        return count;
    }

    private static long sum(int[] arr, int left, int right) {
        long total = 0;
        for (int i = left; i <= right; i++) {
            total += arr[i];
        }
        return total;
    }

}
