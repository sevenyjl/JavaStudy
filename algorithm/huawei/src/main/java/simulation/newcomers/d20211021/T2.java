package simulation.newcomers.d20211021;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 假设有搅乱顺序的一群儿童成一个队列。 每个儿童由一个整数对(w, k)表示，其中 w 是这个儿童 的体重，k 是排在这个儿童前面且体重大于或等于 w 的儿童数量。 编写一个算法来重建这个队 列。
 * <p>
 * 注意:
 * <p>
 * 总儿童数量少于 1100 人。
 * 输入的数据是合理的，只是顺序给打乱了。
 * <p>
 * 示例
 * <p>
 * 输入:
 * [[8,0], [4,4], [8,1], [5,0], [6,1], [5,2]]
 * <p>
 * 输出:
 * [[5,0], [8,0], [5,2], [6,1], [4,4], [8,1]]
 * 解释:[5,2]前面两个儿童的体重分别是 5 和 8，且只有两个儿童;[6,1]前面只有[8,0]儿童的体重 大于他/她，并且不能和[5,2]换位置，否则会导致[5,2]的 2 不对。
 * <p>
 * 答题要求：您编写的代码需要符合 CleanCode 的要求（包括通用编码规范、安全编码规范和圈复杂度）。
 */
public class T2 {
    public static void main(String[] args) {
        for (int[] ints : re2(new int[][] {{8, 0}, {4, 4}, {8, 1}, {5, 0}, {6, 1}, {5, 2}})) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 超时
     * @param people
     * @return
     */
    public static int[][] re(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        int[][] ans = new int[people.length][2];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < people.length; ++i) {
                if (ans[i][0] == 0) {
                    spaces--;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static int[][] re2(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }
        return list.toArray(new int[list.size()][2]);
    }

}
