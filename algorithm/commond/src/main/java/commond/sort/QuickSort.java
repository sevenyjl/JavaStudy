package commonly.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 排序算法之快速排序
 * http://3ms.huawei.com/km/blogs/details/5973811
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] ints = {6,5,4,3,2,1};
        sort(ints, 0, ints.length - 1);
        System.out.println(Arrays.toString(ints));
    }

    public static void sort(int[] ints, int left, int right) {
        if (left > right) {
            return;
        }
        int i, j, temp;
        i = left;
        j = right;
        temp = ints[left];
        while (i < j) {
            while (ints[j] > temp && i < j) {
                j--;
            }
            if (i < j) {
                ints[i] = ints[j];
                i++;
            }
            while (ints[i] < temp && i < j) {
                i++;
            }
            if (i < j) {
                ints[j] = ints[i];
                j--;
            }
        }
        ints[i] = temp;
        sort(ints, left, i - 1);
        sort(ints, i + 1, right);
    }
}
