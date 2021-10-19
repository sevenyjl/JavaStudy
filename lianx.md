一个机房里面有N个位置可以放N个服务器，每个服务器都有一个标签，标签为正整数1~N，且有可能有重复。输入一个标签列表（无序的），求最少移动多少个服务器可以让标签列表有序



输入样例：[1,1,2,3,5,4]

输出样例：2

输出样例说明：互换标签为5和4的服务器，即可使得标签列表有序。



//根据题意，要将一个数组变为有序的需要移动多少个数字，将原数组排下序，比较以下哪几个位置不一样即可得到答案

```java
public static int minTrans(int[] trans) {
    int[] res = trans.clone();
    Arrays.sort(res);

    int count = 0;
    for (int i = 0; i < trans.length; i++) {
        if (res[i] != trans[i]) {
            count++;
        }
    }

    return count;
}

```


给定一个闭区间范围[m, n]，1<=m<=n<=10^9，求[m,n]区间上位数为偶数的整数有多少个？



输入样例：

m = 1, n = 100

输出样例：

90

输出样例说明：[1,100]区间上，有10~99这些整数的位数是2位，是偶数，所以答案是10~99这些整数的数量，即90



//根据题意，需要统计给定区间里面的所有数字个数为偶数的数的多少，有点绕。在该题中直接使用循环遍历所有数字，中间对于比如10-99，100-999这些直接跳过提高效率。


```java

public static int mostDouble(int m, int n) {
    int count = 0;
    for (int i = m; i <= n; i++) {
        int length = String.valueOf(i).length();
        //当循环到10的倍数时，直接进行跳过操作
        if (i % 10 == 0) {
            //当是偶数时直接将连续的偶数全部计算在内
            int finish = (int) Math.pow(10, length) - 1;
            if (length % 2 == 0) {
                count += finish - i + 1;
            }

            //当跳过了结束值，需减掉多出来的偶数
            if (finish > n && length % 2 == 0) {
                count -= (finish - n);
            }
            i = finish;

        } else {
            if (length % 2 == 0) {
                count++;
            }
        }
    }

    return count;
}
```
某地突发一起重大交通事故，热心群众立即播打了 120 急救电话，事发时段正巧是下班高峰，路况不尽如人意。救护车司机打开地图 matrix ，matrix 是给定的一个的矩阵，给定起点S（坐标 startX, startY） 以及终点E（坐标 endX, endY）。地图上所有的 0 都显示通畅路段，1 代表拥堵路段（拥堵路段不可通行）。为了能尽快将伤员送往医院，司机立即求助于市交通部指挥中心，指挥中心使用智慧交通提供的紧急救援功能，可以将地图上的一个拥堵路段（1）开辟出一道绿色通道变为通畅路段（0）。（注意：司机仅有一次开辟绿色通道的机会）司机一次只能往上、往下、往左、往右行驶一公里，请返回他从 S 开始并走到 E 所花的最短公里数。如果一定不能到达，请返回 -1。



示例 1：

输入：matrix = [[0,0,1,0],[1,0,0,0]], startX = 0, startY = 0, endX = 0, endY = 3

输出：3

解释：司机在智慧交通提供的紧急救援功能的引导下可以将坐标为 (0, 2) 的拥堵路段开辟出一道绿色通道转变为通畅路段。这样的公里数最少，为 3。

示例 2：



输入：matrix = [[0,1,1,0],[1,0,0,0]], startX = 0, startY = 0, endX = 0, endY = 3

输出：5

解释：司机在智慧交通提供的紧急救援功能的引导下只能将坐标为(1, 0)的拥堵路段开辟出一条绿色通道变为通畅路段。这样的公里数为5。

示例 3：



输入：matrix = [[0,1,1,0],[1,1,0,0]], startX = 0, startY = 0, endX = 0, endY = 3

输出：-1

解释：司机被困在了起点。答案为-1。



限制：

matrix 大小不超过 100 * 100

0 <= 拥堵路段（1）的个数 <= 50

起始点和终点都不是拥堵路段。



//即为计算两点间最短路径，然后中间能去掉一个障碍点，DFS+回溯，另附BFS解法，推荐使用BFS


```java
private static int[][] nextStep = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

public static int rescue(int[][] matrix, int startX, int startY, int endX, int endY) {

    List<Integer> res = new ArrayList<>();
    //记录该节点是否已经走过的矩阵，避免重复寻找
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    visited[startX][startY] = true;
    dfs(matrix, startX, startY, endX, endY, 0, false, res, visited);

    if (res.size() > 0) {
        return res.get(0);
    } else {
        return -1;
    }
}

private static void dfs(int[][] matrix, int startX, int startY, int endX, int endY, int stepNum,
                         boolean used, List<Integer> res, boolean[][] visited) {

    //首先判定是否到达终点, 到底终点则比较所用步数
    if (startX == endX && startY == endY) {
        if (res.size() == 0) {
            res.add(stepNum);
        } else {
            if (res.get(0) > stepNum) {
                res.set(0, stepNum);
            }
        }
    } else {
        //对于结束条件进行剪枝，当已经跑完的路径比现在没跑完的要长才继续寻找
        if (res.size() == 0 || res.get(0) > stepNum) {
            //四个方向进行寻找
            for (int[] next : nextStep) {
                int nextX = startX + next[0];
                int nextY = startY + next[1];
                if (nextX >= 0 && nextX < matrix.length && nextY >= 0 && nextY < matrix[0].length && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    //对于下一步是否为路障进行分别处理
                    if (matrix[nextX][nextY] == 1) {
                        //当该点是障碍点时，只有除障没有用过时才能继续查找
                        if (!used) {
                            dfs(matrix, nextX, nextY, endX, endY, stepNum + 1, true, res, visited);
                        }
                    } else {
                        dfs(matrix, nextX, nextY, endX, endY, stepNum + 1, used, res, visited);
                    }
                    visited[nextX][nextY] = false;
                }
            }
        }
    }

}

```

//BFS解法不用递归，使用队列存储需要走的路径，推荐解法
```java
private static int[][] nextStep = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

public static int rescue(int[][] matrix, int startX, int startY, int endX, int endY) {

    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{startX, startY, 0});

    //用两个矩阵记录走过的路径,分别表示没有使用清障和使用清障走过的位置
    boolean[][] visitNoShield = new boolean[matrix.length][matrix[0].length];
    boolean[][] visitHasShield = new boolean[matrix.length][matrix[0].length];
    visitNoShield[startX][startY] = true;
    visitHasShield[startX][startY] = true;


    //用来记录走过的步数
    int count = 0;
    while (!queue.isEmpty()) {
        count++;
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            //用来记录当前坐标与是否使用路障的状态 0：x坐标 1：Y坐标 2：是否使用清路障 0-未使用 1-已使用
            int[] node = queue.remove();

            //结束条件,返回时减1，由于算了第一个点的步数
            if (node[0] == endX && endY == node[1]) {
                return count - 1;
            }

            //四个方向进行寻找
            for (int[] next : nextStep) {
                int nextX = node[0] + next[0];
                int nextY = node[1] + next[1];

                if (nextX >= 0 && nextX < matrix.length && nextY >= 0 && nextY < matrix[0].length) {
                    //对于下一步是否为路障进行分别处理
                    if (matrix[nextX][nextY] == 1) {
                        if (node[2] == 0 && !visitHasShield[nextX][nextY]) {
                            queue.add(new int[]{nextX, nextY, 1});
                            visitHasShield[nextX][nextY] = true;
                        }
                    } else {
                        if ((node[2] == 0 && !visitNoShield[nextX][nextY]) || (node[2] == 1 && !visitHasShield[nextX][nextY])) {
                            queue.add(new int[]{nextX, nextY, node[2]});
                            if (node[2] == 0) {
                                visitNoShield[nextX][nextY] = true;
                            } else {
                                visitHasShield[nextX][nextY] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    return -1;
}
```
