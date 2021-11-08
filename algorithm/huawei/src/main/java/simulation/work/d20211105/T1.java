package simulation.work.d20211105;

import java.util.Arrays;

/**
 * @author y30016814
 * @since 2021/11/5 9:04
 * http://3ms.huawei.com/km/blogs/details/11044563
 */
public class T1 {
    public static void main(String[] args) {
        System.out.println(getMaxGiftsNum(new int[] {361, 900, 870, 1000, 719, 400, 200, 900, 200, 550, 800}, 5000));
    }

    public static int getMaxGiftsNum(int[] prices, int maxMoney) {
        Arrays.sort(prices);
        int count = 0;
        for (int price : prices) {
            if (maxMoney > 0) {
                maxMoney -= price;
                count++;
            } else {
                return count;
            }
        }
        return count;
    }
}
