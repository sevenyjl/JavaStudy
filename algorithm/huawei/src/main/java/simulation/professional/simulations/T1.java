package simulation.professional.simulations;

import java.util.Arrays;

/**
 * OJ考题代码：快速排序
 * 著名的快速排序算法里有一个经典的划分过程：通常采用某种方法取一个元素作为主元，通过交换，把比主元小的元素放到它的左边，比主元大的元素放到它的右边。
 * <p>
 * 给定一个划分后的正整数序列，请问有多少个元素可能是划分过程中选取的主元？
 * <p>
 * 输入
 * 第 1 行一个正整数 N，表示正整数序列的长度，取值范围 [1, 10^5]
 * 第 2 行 N 个互不不同的正整数，每个数的取值范围[1, 10^9]
 * <p>
 * 输出
 * 按升序排列的可能主元的列表，以单个空格分隔；或者空列表。
 * <p>
 * 样例
 * 输入样例1 复制
 * 5
 * 1 3 2 4 5
 * 1 2 3 4 5
 * 输出样例1 复制
 * [1 4 5]
 * 提示样例1
 * 1 的左边没有元素，右边的元素都比它大，所以它可能是主元。
 * 尽管 3 的左边元素都比它小，但其右边的 2 比它小，所以它不能是主元； 尽管 2 的右边元素都比它大，但其左边的 3 比它大，所以它不能是主元。
 * 同理，4 和 5 都可能是主元。
 * 因此，1、4、5 是可能的主元，按升序输出为 [1 4 5] 。
 * <p>
 * 主元有一个特点：主元的位置和排列之后数组中的位置相同，而且是主元之前的最大数。
 *
 * @author 命题组
 * @since 2021-01-29
 */

public class T1 {
    /**
     * 他人
     * @param array
     * @return
     */
    private static int[] quickSort(int[] array) {
        int index = 0;
        int[] re = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            boolean b = true;
            // 1 3 2 4 5
            int nowNum = array[i];
            // 使该元素比较左边的
            for (int j = 0; j < i; j++) {
                if (nowNum < array[j]) {
                    b = false;
                    break;
                }
            }
            //使该元素比较右边的
            for (int k = i + 1; k < array.length; k++) {
                if (nowNum > array[k]) {
                    b = false;
                    break;
                }
            }
            if (b) {
                re[index] = array[i];
                index++;
            }
        }
        return Arrays.stream(re).limit(index).sorted().toArray();
    }

    /**
     * 自己写的
     * @param array
     * @return
     */
    private static int[] quickSort2(int[] array) {
        int index = 0;
        int[] re = new int[array.length];
        // 保留原来数组
        int[] temp = array.clone();
        // 利用java自己排序（目的：找到索引相同值相同的 元素-->可能是主元素）
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            //找到相同索引&值的元素 主元素判断条件1
            if (array[i] == temp[i]) {
                boolean flag = true;
                for (int j = 0; j < i; j++) {
                    // 判断 主元之前的最大数。主元素判断条件2
                    if (temp[j] > temp[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    // 加入结果数组中
                    re[index] = temp[i];
                    index++;
                }
            }
        }
        return Arrays.stream(re).limit(index).sorted().toArray();
    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        // Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        // int row = cin.nextInt();
        // int[] numbers = new int[row];
        // for (int i = 0; i < row; i++) {
        //     numbers[i] = cin.nextInt();
        // }
        // cin.close();
        //{1, 0, 2, 2, 2, 2, 1, 0, 0, 2}
        //{0, 0, 0, 1, 1, 2, 2, 2, 2, 2}
        int[] numbers = new int[] {1, 0, 2, 2, 2, 2, 1, 0, 0, 2};
        showResult(numbers);
        // int[] randomInts = ArraysRandom.createRandomInts(10, 3);
        // showResult(randomInts);

    }

    /**
     * just show result
     *
     * @param numbers
     */
    private static void showResult(int[] numbers) {
        // random test
        long start = System.currentTimeMillis();
        // do something
        int[] results = quickSort(numbers);
        System.out.printf("执行耗时%s毫秒\n", System.currentTimeMillis() - start);

        String[] strResult = Arrays.stream(results).mapToObj(String::valueOf).toArray(String[]::new);
        System.out.println("[" + String.join(" ", strResult) + "]");

        start = System.currentTimeMillis();
        // do something
        results = quickSort2(numbers);
        System.out.printf("执行耗时%s毫秒\n", System.currentTimeMillis() - start);
        strResult = Arrays.stream(results).mapToObj(String::valueOf).toArray(String[]::new);
        System.out.println("[" + String.join(" ", strResult) + "]");
    }
}
