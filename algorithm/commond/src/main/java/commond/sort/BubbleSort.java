package commond.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 冒泡排序（Bubble Sort）是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * <p>
 * <p>
 * 平均时间复杂度	最好情况	最坏情况	空间复杂度
 * O(n²)	    O(n)	O(n²)	O(1)
 */
public class BubbleSort implements ISort {
    public static void main(String[] args) {
        bubbleSort(new int[] {7, 1, 3, 4, 6, 8, 9, 2, 4, 0});
    }

    /**
     * ①. 倒着遍历，因为冒泡后面的都是不动的，大的会逐渐上浮
     * ②. 正着遍历到最近的最大值即上格循环的i，比较相邻的两个数字，大于则交换
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // System.out.println(Arrays.toString(arr));
                }
            }
        }
    }

    @Override
    public void sort(int[] arr) {
        bubbleSort(arr);
    }
}
