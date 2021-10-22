package simulation.professional.d20211014;

import java.util.Arrays;

import random.ArraysRandom;

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
 * <p>
 * OJ考题代码：最小距离和
 *
 * 没有通过~
 * @author 命题组
 * @since 2020-3-9
 */
public class T3_1 {

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        // Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        // int rows = cin.nextInt();
        // int cows = cin.nextInt();
        // int[][] grid = new int[rows][cows];
        // for (int i = 0; i < rows; i++) {
        //     for (int j = 0; j < cows; j++) {
        //         grid[i][j] = cin.nextInt();
        //     }
        // }
        // System.out.println(nearestWareHouse(grid));
        //
        // cin.close();

        // System.out.println(nearestWareHouse(new int[][] {{1, -1, 0}, {0, 1, 1}})); //3
        // System.out.println(nearestWareHouse(new int[][] {{1, -1, 0}, {0, 1, 1}, {1, -1, 1}})); // 6
        // System.out.println(nearestWareHouse(new int[][] {{}})); // 0
        // System.out.println(nearestWareHouse(new int[][] {{1}})); // 0
        // System.out.println(nearestWareHouse(new int[][] {{1, -1, 1}, {1, 1, 1}, {1, -1, 1}})); // 0
        // System.out.println(nearestWareHouse(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}})); // 0
        // System.out.println(nearestWareHouse(new int[][] {{1, 1, 1}, {1, -1, 1}, {1, 1, -1}})); // 0
        // System.out.println(nearestWareHouse(new int[][] {
        //     {1, 1, 1, 1, 1},
        //     {1, -1, -1, -1, 1},
        //     {1, -1, 0, -1, 1},
        //     {1, -1, -1, -1, 1},
        //     {1, 1, 1, 1, 1},
        // })); // 0
        // System.out.println(T3_BAK1.solve(new int[][] {
        //     {1, 1, 1, 1, 1},
        //     {1, -1, -1, -1, 1},
        //     {1, -1, 0, -1, 1},
        //     {1, -1, -1, -1, 1},
        //     {1, 1, 1, 1, 1},
        // })); // 0
        // System.out.println(nearestWareHouse(new int[][] {
        //     {1, 1, 1, 1, 1},
        //     {1, 1, 1, 1, 1},
        //     {1, 1, 0, 1, 1},
        //     {1, 1, 1, 1, 1},
        //     {1, 1, 1, 1, 1},
        // })); //60
        // System.out.println(nearestWareHouse(new int[][] {
        //     {1, 1, 1, 1, 1},
        //     {1, -1, -1, -1, 1},
        //     {-1, 1, 0, 1, -1},
        // })); //2
        //big test
        int[][] randomInts = ArraysRandom.createRandomIntss(100, 100, Arrays.asList(1, -1, 0));
        long start = System.currentTimeMillis();
        //do something
        System.out.println(T3_BAK1.solve(randomInts.clone()));
        System.out.printf("执行耗时%s毫秒\n", System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        //do something
        System.out.println(nearestWareHouse(randomInts));
        System.out.printf("执行耗时%s毫秒\n", System.currentTimeMillis() - start);
    }

    private static int sum = 0;

    /**
     * 暴力破解（一个个遍历，一个个找），而且改变了原来的数组
     *
     * @param grid
     * @return
     */
    private static int nearestWareHouse(int[][] grid) {
        sum = 0;
        // int[][][] caches = new int[grid.length][grid[0].length][1];
        //移除特殊情况 全部都是1的情况
        int length = grid[0].length;
        int target = 0;
        while (true) {
            boolean flag = true;
            boolean has0 = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < length; j++) {
                    if (!has0 && grid[i][j] == 0) {
                        judgments0(grid, i, j);
                        has0 = true;
                    } else if (grid[i][j] == 1) {
                        flag = false;
                        judgments1(grid, i, j, target);
                    }
                }
            }
            if (!has0) {
                break;
            }
            target = target + 2;
            if (flag) {
                break;
            }
        }
        return sum;
    }

    private static void judgments1(int[][] grid, int lie, int hang, int target) {
        int lieMax = grid.length - 1;
        int hangMax = grid[0].length - 1;
        if ((lie > 0 && grid[lie - 1][hang] == target) ||
            (lie < lieMax && grid[lie + 1][hang] == target) ||
            (hang > 0 && grid[lie][hang - 1] == target) ||
            (hang < hangMax && grid[lie][hang + 1] == target)) {
            grid[lie][hang] = target + 2;
            sum += target / 2 + 1;
            return;
        }
        if ((lie > 0 && grid[lie - 1][hang] == -1) &&
            (lie < lieMax && grid[lie + 1][hang] == -1) &&
            (hang > 0 && grid[lie][hang - 1] == -1) &&
            (hang < hangMax && grid[lie][hang + 1] == -1)) {
            grid[lie][hang] = -1;
        }
    }

    /**
     * 判断0 是否失效，失效条件周围没有1
     *
     * @param grid
     * @param lie
     * @param hang
     */
    private static void judgments0(int[][] grid, int lie, int hang) {
        int lieMax = grid.length - 1;
        int hangMax = grid[0].length - 1;
        int count = 4;
        if (lie > 0) {
            if (grid[lie - 1][hang] != 1) {
                count--;
            }
        } else {
            count--;
        }
        if (lie < lieMax) {
            if (grid[lie + 1][hang] != 1) {
                count--;
            }
        } else {
            count--;
        }
        if (hang > 0) {
            if (grid[lie][hang - 1] != 1) {
                count--;
            }
        } else {
            count--;
        }
        if (hang < hangMax) {
            if (grid[lie][hang + 1] != 1) {
                count--;
            }
        } else {
            count--;
        }
        if (count == 0) {
            grid[lie][hang] = -1;
        }
        // if ((lie > 0 && grid[lie - 1][hang] != 1) &&
        //     (lie < lieMax && grid[lie + 1][hang] != 1) &&
        //     (hang > 0 && grid[lie][hang - 1] != 1) &&
        //     (hang < hangMax && grid[lie][hang + 1] != 1)) {
        //     grid[lie][hang] = -1;
        // }
    }

}






