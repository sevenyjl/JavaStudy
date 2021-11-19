package simulation.professional.simulations;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 终端公司的零售店需要定期去仓库提取货物，假设零售店和仓库在一个矩阵上：相邻点的距离为 1 ；只能通过上下左右走动。
 * 矩阵元素的值仅为三种：0，表示仓库； -1，表示障碍； 1，表示零售店。 注：障碍无法通过，其它可以通过。
 * <p>
 * 为了将取货效率最大化，需要计算每个零售店走到最近仓库的最小距离，并输出这些最小距离的和：
 * <p>
 * 无法到达仓库的零售店，不参与距离和的计算；
 * 没有零售店或者没有仓库的话，返回0;
 * 输入
 * 第一行为两个数字 m 和 n，表示数组的行数和列数，m和n的范围均为 [1,300) 。
 * 接下来的 m 行表示一个 m*n 的数组，每行的元素间以空格分割。
 * <p>
 * 输出
 * 一个整数，表示所计算的最小距离和。
 * <p>
 * 样例
 * 输入样例1 复制
 * 3 3
 * 1 -1 0
 * 0 1 1
 * 1 -1 1
 * 输出样例1 复制
 * 6
 * 提示样例1
 * 如下图所示，共有5个零售店（绿色），2个仓库（红色），2个障碍（白色），零售店上标注的数字表示到最近仓库的最小距离：
 * http://oj.rnd.huawei.com/public/upload/6dc252bc6e.png
 * <p>
 * 位置[2][2]的零售店，距离[0][2]的仓库为 2，距离[1][0]的仓库为 3，因此到最近仓库的最小距离为 2 ； 其余零售店到最近仓库的最小距离都为 1。所以，所有零售店到仓库的最小距离和为 1 + 1 + 1 + 1 + 2 = 6 。
 * <p>
 * 输入样例2 复制
 * 2 3
 * 0 -1 1
 * 1 -1 1
 * 输出样例2 复制
 * 1
 * 提示样例2
 * 位置[0][2]和[1][2]的零售店无法到达唯一的仓库[0][0]，只有[1][0]的零售店可以到达，且最近距离为 1 。
 * <p>
 * OJ考题代码：最小距离和
 *
 * @author 命题组
 * @since 2020-3-9
 */
public class T3_BAK1 {
    public static int solve(int[][] metrics) {
        int rowNumb = metrics.length;
        int columnNumb = metrics[0].length;

        int totalMinDistance = 0;
        for (int i = 0; i < rowNumb; i++) {
            for (int j = 0; j < columnNumb; j++) {
                if (metrics[i][j] == 1) {
                    totalMinDistance += getMinDistance(metrics, i, j);
                }
            }
        }
        return totalMinDistance;
    }

    /**
     * bfs
     *
     * @param metrics
     * @param row
     * @param column
     * @return
     */
    private static int getMinDistance(int[][] metrics, int row, int column) {
        int rowNumb = metrics.length;
        int columnNumb = metrics[0].length;

        int[][] moveSteps = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // u d l r
        Queue<int[]> readyQueue = new ArrayDeque<>();
        int[][] visited = new int[rowNumb][columnNumb];
        int[] startLocation = {row, column};
        readyQueue.offer(startLocation);
        visited[row][column] = 1;

        int stepCnt = 0;
        while (!readyQueue.isEmpty()) {
            int queueSize = readyQueue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] currentLocation = readyQueue.poll();
                int currentValue = metrics[currentLocation[0]][currentLocation[1]];
                if (currentValue == 0) {
                    return stepCnt;
                }
                for (int[] moveStep : moveSteps) {
                    int newRow = currentLocation[0] + moveStep[0];
                    int newColumn = currentLocation[1] + moveStep[1];
                    if (newRow < 0 || newRow >= rowNumb || newColumn < 0 || newColumn >= columnNumb) {
                        continue;
                    }
                    int[] newLocation = {newRow, newColumn};
                    if (metrics[newRow][newColumn] == -1) {
                        continue;
                    }
                    if (visited[newRow][newColumn] == 0) {
                        readyQueue.offer(newLocation);
                        visited[newRow][newColumn] = 1;
                    }
                }
            }
            stepCnt++;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[][] {{1, -1, 0}, {0, 1, 1}, {1, -1, 1}})); // 6
        System.out.println(solve(new int[][] {{}})); // 0
        System.out.println(solve(new int[][] {{1}})); // 0
        System.out.println(solve(new int[][] {{1, -1, 1}, {1, 1, 1}, {1, -1, 1}})); // 0
    }
}






