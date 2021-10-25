package simulation.newcomers.d20211025;

/**
 * 在直角坐标系中，首先给你一个锚点，坐标为(posX, posY)，同时给你一个坐标点的集合，记于数组 arr 中，arr[i] = [xi, yi] 表示坐标点 (xi, yi) 。
 * 当arr中的坐标点与锚点的 x 坐标相等或者 y 坐标相等时，我们称这个点是 匹配点 。
 * <p>
 * 请找出与锚点的曼哈顿距离最近的匹配点，并返回其在arr中的下标；如果有多个最近的匹配点，请返回下标最小的；如果没有匹配点，请返回 -1 。
 * <p>
 * 注：两个点 (x1, y1) 和 (x2, y2) 之间的 曼哈顿距离 为 |x1 - x2| + |y1 - y2| 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：posX = 15, posY = 20, arr = [[35,20],[30,10],[20,23],[15,30],[20,15],[25,20]]
 * 输出：3
 * 解释：
 * 匹配点为：
 * [35,20] y 坐标相等
 * [15,30] x 坐标相等
 * [25,20] y 坐标相等
 * 其中 [15,30] 和 [25,20] 距离锚点(15,20)的曼哈顿距离最小，都为 10 ，[15,30] 的下标最小，所以返回 3。
 * 示例 2：
 * <p>
 * 输入：posX = 10, posY = 50, arr = [[110,78]]
 * 输出：-1
 * 解释：没有匹配点，因此返回 -1。
 * 提示：
 * <p>
 * 1 <= arr.length <= 104
 * arr[i].length == 2
 * 1 <= posX, posY, xi, yi <= 104
 */
public class T1 {
    public static void main(String[] args) {
        System.out.println(
            getMatchPoint(15, 20, new int[][] {{35, 20}, {30, 10}, {20, 23}, {15, 30}, {20, 15}, {25, 20}}));
    System.out.println(
            getMatchPoint(10, 50, new int[][] {{110,78}}));
    }

    public static int getMatchPoint(int posX, int posY, int[][] arr) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (posX == arr[i][0] || posY == arr[i][1]) {
                int temp = Math.abs(arr[i][0] - posX) + Math.abs(arr[i][1] - posY);
                if (temp < min) {
                    index = i;
                    min = temp;
                }
            }
        }
        return index;
    }
}
