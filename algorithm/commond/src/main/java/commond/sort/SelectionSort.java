package commond.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 选择排序的基本思想：比较 + 交换。
 * <p>
 * 在未排序序列中找到最小（大）元素，存放到未排序序列的起始位置。在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 * <p>
 * <p>
 * 平均时间复杂度	最好情况	最坏情况	空间复杂度
 * O(n²)	    O(n²)	O(n²)	O(1)
 */
public class SelectionSort implements ISort {
    public static void main(String[] args) {
        selectionSort(new int[] {9, 1, 2, 5, 7, 8, 0, 6, 4, 3});

    }

    /**
     * ①. 从待排序序列中，找到关键字最小的元素；
     * ②. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
     * ③. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        // System.out.println("Sorting:  " + Arrays.toString(arr));
    }

    @Override
    public void sort(int[] arr) {
        selectionSort(arr);
    }
}
