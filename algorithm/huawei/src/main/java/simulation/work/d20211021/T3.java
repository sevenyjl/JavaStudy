package simulation.work.d20211021;

/**
 *
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
 * 输出样例1 复制
 * [1 4 5]
 * 提示样例1
 * 1 的左边没有元素，右边的元素都比它大，所以它可能是主元。
 * 尽管 3 的左边元素都比它小，但其右边的 2 比它小，所以它不能是主元； 尽管 2 的右边元素都比它大，但其左边的 3 比它大，所以它不能是主元。
 * 同理，4 和 5 都可能是主元。
 * 因此，1、4、5 是可能的主元，按升序输出为 [1 4 5] 。
 * @see     simulation.professional.simulations.T1
 */


public class T3 {


}
