package commonly.sort;

import java.util.Arrays;

import cn.hutool.core.util.ObjectUtil;
import random.ArraysRandom;

/**
 * 希尔排序，也称递减增量排序算法，1959年Shell发明。是插入排序的一种高速而稳定的改进版本。
 * <p>
 * 希尔排序是先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
 * 希尔排序的基本思想是：希尔排序在数组中采用跳跃式分组的策略，通过某个增量将数组元素划分为若干组，然后分组进行插入排序，随后逐步缩小增量，继续按组进行插入排序操作，直至增量为1。
 * 希尔排序通过这种策略使得整个数组在初始阶段达到从宏观上看基本有序，小的基本在前，大的基本在后。然后缩小增量，到增量为1时，其实多数情况下只需微调即可，不会涉及过多的数据移动。
 * <p>
 * <p>
 * 平均时间复杂度	最好情况	    最坏情况	    空间复杂度
 * O(nlog2^n)	O(nlog2^n)	O(nlog2^n)	O(1)
 */
public class ShellSort implements ISort {


    /**
     * ①. 定义gap，也叫做增量。增量初始值的定义可能会影响shell排序的效率，一般gap=len/2
     * ②. 对数组进行增量分组。eg：arr=[1,4,3,5];gap=2;-->>[1,3];[4,5]
     * ③. 对分好的数组进行插入排序
     * ④. 增量=增量/2 每次缩小2倍重复①~③，直到gap=1
     *
     * @param ints
     */
    public static void shellSort(int[] ints) {
        int gap = ints.length / 2;
        int index = 0;
        while (gap > 0) {
            for (int i = 0; i < ints.length; i++) {
                index = i + gap;
                while (index < ints.length) {
                    if (ints[i] > ints[index]) {
                        int temp = ints[index];
                        ints[index] = ints[i];
                        ints[i] = temp;
                    }
                    index = index + gap;
                }
            }
            gap = gap / 2;
        }
        // System.out.println(Arrays.toString(ints));
    }

    /**
     * 算法优化版(数组交换)
     *
     * @param ints
     */
    public static void shellSort2(int[] ints) {
        //增量gap，并逐步缩小增量
        for (int gap = ints.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < ints.length; i++) {
                int j = i;
                while (j - gap >= 0 && ints[j] < ints[j - gap]) {
                    //插入排序采用交换法
                    int temp = ints[j];
                    ints[j] = ints[j - gap];
                    ints[j - gap] = temp;
                    j -= gap;
                }
            }
        }
        // System.out.println(Arrays.toString(ints));
    }

    /**
     * 算法优化版(数组移动)
     *
     * @param arr
     */
    public static void shellSort3(int[] arr) {
        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动法
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
        // System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序（Wiki官方版）
     *
     * @param arr 待排序数组
     */
    public static void shellSort4(int[] arr) {
        int gap = 1, i, j, len = arr.length;
        int temp;
        while (gap < len / 3) {
            gap = gap * 3 + 1;      // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
        }
        for (; gap > 0; gap /= 3) {
            for (i = gap; i < len; i++) {
                temp = arr[i];
                for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = temp;
            }
        }
    }

    @Override
    public void sort(int[] arr) {
        shellSort2(arr);
    }
}
