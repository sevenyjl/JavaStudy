package commonly.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 排序算法之快速排序
 * http://3ms.huawei.com/km/blogs/details/5973811
 * <p>
 * <p>
 * 平均时间复杂度	最好情况	    最坏情况	空间复杂度
 * O(nlog₂n)	O(nlog₂n)	O(n²)	O(1)（原地分区递归版）
 */
public class QuickSort implements ISort {

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

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
