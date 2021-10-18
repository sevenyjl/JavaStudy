package commonly.sort;

import java.util.Arrays;

/**
 * 直接插入
 * <p>
 * 插入排序的设计初衷是往有序的数组中快速插入一个新的元素。它的算法思想是：把要排序的数组分为了两个部分, 一部分是数组的全部元素(除去待插入的元素), 另一部分是待插入的元素;
 * 先将第一部分排序完成, 然后再插入这个元素. 其中第一部分的排序也是通过再次拆分为两部分来进行的.
 * <p>
 * 插入排序由于操作不尽相同, 可分为
 * * 直接插入排序
 * * 折半插入排序(又称二分插入排序),
 * * 链表插入排序
 * * 希尔排序
 * <p>
 * <p>
 * 最好情况下:
 * <p>排序前对象已经按照要求的有序。比较次数(KCN)：n−1n−1；
 * <p>移动次数(RMN)为00。则对应的时间复杂度为O(n)O(n)。
 * 最坏情况下:
 * <p>排序前对象为要求的顺序的反序。第i趟时第i个对象必须与前面i个对象都做排序码比较，并且每做1次比较就要做1次数据移动（从上面给出的代码中看出）。
 * <p>比较次数(KCN)：∑n−1i=1i=n(n−1)2≈n22∑i=1n−1i=n(n−1)2≈n22 ;
 * <p>移动次数(RMN)为：∑n−1i=1i=n(n−1)2≈n22∑i=1n−1i=n(n−1)2≈n22。则对应的时间复杂度为O(n^2)。
 * 如果排序记录是随机的，那么根据概率相同的原则，在平均情况下的排序码比较次数和对象移动次数约为n22，因此，直接插入排序的平均时间复杂度为O(n^2)。
 * 平均时间复杂度	最好情况	最坏情况	空间复杂度
 * O(n²)	    O(n)	O(n²)	O(1)
 */
public class InsertionSort implements ISort {
    public static void main(String[] args) {
        insertionSort(new int[] {4, 1, 5, 3, 2});
        insertionSort2(new int[] {4, 1, 5, 3, 2});
    }

    /**
     * 41532
     * ①. 从第一个元素开始，该元素可以认为已经被排序
     * ②. 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * ③. 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * ④. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * ⑤. 将新元素插入到该位置后
     * ⑥. 重复步骤②~⑤
     */
    public static void insertionSort(int[] ints) {
        for (int i = 1; i < ints.length; i++) {//①. 从第一个元素开始，该元素可以认为已经被排序
            int temp = ints[i];
            for (int j = i; j >= 0; j--) {//②. 取出下一个元素，在已经排序的元素序列中从后向前扫描
                if (j > 0 && ints[j - 1] > temp) {
                    //③. 如果该元素（已排序）大于新元素，将该元素移到下一位置
                    ints[j] = ints[j - 1];
                    // System.out.println("Temping:  " + Arrays.toString(ints));
                } else {
                    //⑤. 将新元素插入到该位置后
                    ints[j] = temp;
                    // System.out.println("Sorting:  " + Arrays.toString(ints));
                    break;
                }
            }
        }
    }

    // 交换次数较多的实现
    public static void insertionSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j - 1] <= arr[j]) {
                    break;
                }
                int temp = arr[j];      //交换操作
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                // System.out.println("Sorting:  " + Arrays.toString(arr));
            }
        }
    }

    @Override
    public void sort(int[] arr) {
        insertionSort(arr);
    }
}
