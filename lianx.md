现给定一个正整数数组 nums，其按照时间顺序记录了数个同类任务的完成时长（单位为周），为了更准确评估该类任务的工作量，现做如下分析，找出每个任务的前面、比当前任务完成时长短的邻近时长，如果无法找到则记为 -1。将这些邻近时长记录成一个新数组并返回。

 

示例 1：

输入： nums = [1,6,4,10,2,5]

输出：[-1,1,1,4,1,2]

解释：第一个任务时长记录（1）左边没有记录，返回-1。第二个任务时长记录（6）左边只有一个比该时长更短的任务时长（1），所以返回1。

第四个任务时长记录（10）左边有三个比该时长更短的时长记录（1，6，4），所以返回最邻近的4。



示例 2：

输入：nums = [ 2, 4, 1, 3, 6]

输出：[ -1, 2, -1, 1, 3 ]

解释：第三个任务时长记录（1）左边没有比该时长更短的时长记录，（左边时长记录2，4均比1长），故返回-1。

 

限制：

1 <= nums 的长度 <= 100000

1 <= nums[i] <= 100000

*/

 

//感觉是三题里面最简单的



```java
public static int[] leftNum(int[] nums) {
    if (nums.length == 0) {
        return nums;
    }
    int[] res = new int[nums.length];
    res[0] = -1;
    for(int i = 1; i < nums.length; i++) {
        for (int j = i - 1; j >= 0; j--) {
            if (nums[j] < nums[i]) {
                res[i] = nums[j];
                break;
            }
            res[i] = -1;
        }
    }

    return res;
}
```

Leetcode：https://leetcode-cn.com/problems/meeting-rooms-ii/





某设备有一个线程池调度特性，现给一个任务安排的时间数组，每个任务时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免线程冲突，并充分利用线程池的资源，请你给调度特性计算至少需要使用池中的多少个线程，才能满足这些任务执行。

 

示例 1：

输入：[[0, 30],[5, 10],[15, 20]]

输出：2

解释：任务 0 的时间周期为[0, 30]，独占一个线程；任务 1 使用另一个线程，等任务结束后，可以继续执行任务 2。所以，合计使用 2 个线程即可。



示例 2：

输入：[[7,10],[2,4]]

输出：1



示例 3：

输入：[[7,10],[2,7]]

输出：1

```java
public static int minMeetingRooms(int[][] intervals) {
    if (intervals.length < 1) {
        return 0;
    }

    int res = 1;
    for (int[] interval : intervals) {
        int start = interval[0];
        int count = 0;
        for (int[] temp : intervals) {
            if (start >= temp[0] && start < temp[1]) {
                count++;
            }
        }
        res = Math.max(count, res);
    }
    return res;
}
```

**leetcode：https://leetcode-cn.com/problems/the-maze-iii/submissions/**



由空地和围墙组成的专用场地（网格形状）中，有一个自动驾驶汽车要做专项模拟测试。假设：车可以向上（u）下（d）左（l）右（r）四个方向行驶，但在撞到围墙之前不会停止行驶。当车停下时，则可以选择下一个方向。场地中还有一个目的地，当车行驶经过目的地时，就会停下来。

给定车的起始位置，目的地和专用场地，找出让车以最短距离行驶到目的地的路径。 距离的定义是车从起始位置（不包括）到目的地（包括）经过的空地个数。通过'u', 'd', 'l' 和 'r'输出车的移动方向。 由于可能有多条最短路径， 请输出字典序最小的路径。如果车无法到达目的地，则输出"impossible"。

专用场地由一个0和1组成的二维数组表示。 1表示围墙，0表示空地。你可以假定专用场地的边缘都是围墙。起始位置和目的地的坐标通过行号和列号给出。



示例 1：

输入 1: 专用场地由以下二维数组表示

0 0 0 0 0

1 1 0 0 1

0 0 0 0 0

0 1 0 0 1

0 1 0 0 0



输入 2: 车的初始位置 (rowCar, colCar) = (4, 3)

输入 3: 目的地的位置 (rowDes, colDes) = (0, 1) 

输出: "lul"

解析: 有两条让车到达目的地的最短路径。

第一条路径是 左 -> 上 -> 左, 记为 "lul". （第一步向左，碰到墙后停下来，再转向上，一直向上碰到墙后停下来，然后再向左转即达到目的地）

第二条路径是 上 -> 左, 记为 'ul'. （第一步向上，碰到墙后停下来，再转左即可到达目的）

两条路径都具有最短距离6, 但'l' < 'u'，故第一条路径字典序更小。因此输出"lul"。

 



示例 2：

输入 1: 专用场地由以下二维数组表示

0 0 0 0 0

1 1 0 0 1

0 0 0 0 0

0 1 0 0 1

0 1 0 0 0

输入 2: 车的初始位置 (rowCar, colCar) = (4, 3)

输入 3: 目的地的位置 (rowDes, colDes) = (3, 0)

输出: "impossible"

示例: 车无法到达目的地。

 

注意:

\- 场地中只有一个车和一个目的地。

\- 车和目的地都在空地上，且初始时它们不在同一位置。

\- 给定的专用场地不包括边界 (如图中的红色矩形), 但你可以假设专用场地的边缘都是墙壁。

\- 专用场地至少包括2块空地，行数和列数均不超过30。

\- 字典序：是指按照单词出现在字典的顺序进行排序的方法。先按照第一个字母、以 a、b、 c......z 的顺序排列;如果第一个字母一样，那么比较第二个、第三个乃至后面的字母。如果比 到最后两个单词不一样长(比如，sigh 和 sight)，那么把短者排在前。

 



//即为计算两点间最短路径，DFS+回溯，注意只有到达墙和边界才能转弯，除了到达终点不能控制强行控制每个方向在某个空地停下来，下面为BFS解法，推荐使用BFS

```java
private static String res = "Impossible";
private static int minDis = Integer.MAX_VALUE;

public static String searchShortestWay(int[][] matrix, int[] car, int[] des) {

    boolean[][] visit = new boolean[matrix.length][matrix[0].length];
    visit[car[0]][car[1]] = true;
    dfs(matrix, car, des, new ArrayList<>(), 0, visit);
    return res;
}

private static void dfs(int[][] matrix, int[] car, int[] des, List<Character> path, int dis, boolean[][] visit) {
    if (car[0] == des[0] && car[1] == des[1]) {
        if (dis < minDis) {
            minDis = dis;
            StringBuilder sb = new StringBuilder();
            path.forEach(sb::append);
            res = sb.toString();
        } else if (dis == minDis) {
            StringBuilder sb = new StringBuilder();
            path.forEach(sb::append);
            String s = sb.toString();
            res = res.compareTo(s) < 0 ? res : s;
        }
    } else {
        if (minDis > dis) {
            //四个方向进行寻找
            //向上寻找,当向上不是边界且不为墙时
            if (car[0] - 1 >= 0 && matrix[car[0] - 1][car[1]] != 1) {
                //向上一直找到顶点
                int upPoint = car[0];
                while (upPoint > 0 && matrix[upPoint - 1][car[1]] == 0) {
                    upPoint--;
                }

                //判断是否经过终点，经过就取终点为下一个点，没到则取转弯点为下一点
                if (car[1] == des[1] && des[0] < car[0] && des[0] >= upPoint) {
                    upPoint = des[0];
                }

                int newDis = dis + (car[0] - upPoint);
                if (newDis <= minDis && !visit[upPoint][car[1]]) {
                    car[0] = upPoint;
                    path.add('u');
                    visit[car[0]][car[1]] = true;
                    dfs(matrix, car, des, path, newDis, visit);
                    visit[car[0]][car[1]] = false;
                    path.remove(path.size() - 1);
                    car[0] = car[0] + (newDis - dis);
                }
            }

            if (car[0] + 1 < matrix.length && matrix[car[0] + 1][car[1]] != 1) {
                int downPoint = car[0];

                while (downPoint < matrix.length - 1 && matrix[downPoint + 1][car[1]] == 0) {
                    downPoint++;
                }

                //判断是否经过终点
                if (car[1] == des[1] && des[0] > car[0] && des[0] <= downPoint) {
                    downPoint = des[0];
                }

                int newDis = dis + (downPoint - car[0]);
                if (newDis <= minDis && !visit[downPoint][car[1]]) {
                    car[0] = downPoint;
                    path.add('d');
                    visit[car[0]][car[1]] = true;
                    dfs(matrix, car, des, path, newDis, visit);
                    visit[car[0]][car[1]] = false;
                    path.remove(path.size() - 1);
                    car[0] = car[0] - (newDis - dis);
                }
            }

            if (car[1] - 1 >= 0 && matrix[car[0]][car[1] - 1] != 1) {
                int leftPoint = car[1];
                while (leftPoint > 0 && matrix[car[0]][leftPoint - 1] == 0) {
                    leftPoint--;
                }

                //判断是否经过终点
                if (car[0] == des[0] && des[1] < car[1] && des[1] >= leftPoint) {
                    leftPoint = des[1];
                }

                int newDis = dis + (car[1] - leftPoint);
                if (newDis <= minDis && !visit[car[0]][leftPoint]) {
                    car[1] = leftPoint;
                    path.add('l');
                    visit[car[0]][car[1]] = true;
                    dfs(matrix, car, des, path, newDis, visit);
                    visit[car[0]][car[1]] = false;
                    path.remove(path.size() - 1);
                    car[1] = car[1] + (newDis - dis);
                }
            }

            if (car[1] + 1 < matrix[0].length && matrix[car[0]][car[1] + 1] != 1) {
                int rightPoint = car[1];
                while (rightPoint < matrix[0].length - 1 && matrix[car[0]][rightPoint + 1] == 0) {
                    rightPoint++;
                }

                //判断是否经过终点
                if (car[0] == des[0] && des[1] > car[1] && des[1] <= rightPoint) {
                    rightPoint = des[1];
                }

                int newDis = dis + (rightPoint - car[1]);
                if (newDis <= minDis && !visit[car[0]][rightPoint]) {
                    car[1] = rightPoint;
                    path.add('r');
                    visit[car[0]][car[1]] = true;
                    dfs(matrix, car, des, path, newDis, visit);
                    visit[car[0]][car[1]] = false;
                    path.remove(path.size() - 1);
                    car[1] = car[1] - (newDis - dis);
                }
            }
        }
    }
}
```

//BFS解法，强烈推荐

```java
public static String findShortestWay(int[][] matrix, int[] car, int[] des) {

    // 用来记录需要寻找的点
    Queue<int[]> queue = new LinkedList<>();
    queue.add(car);

    // 这两个队列分别用来记录走的路和距离
    Queue<String> pathQueue = new LinkedList<>();
    pathQueue.add("");
    Queue<Integer> disQueue = new LinkedList<>();
    disQueue.add(0);

    //用来记录最后结果
    String res = "impossible";
    int minDis = Integer.MAX_VALUE;

    // 用来记录访问到该点的具体某个方向所用的步数
    int[][][] visit = new int[matrix.length][matrix[0].length][4];

    while (!queue.isEmpty()) {
        int[] node = queue.remove();
        String path = pathQueue.remove();
        int dis = disQueue.remove();

        // 判断是否到达终点
        if (node[0] == des[0] && node[1] == des[1]) {
            if (dis < minDis) {
                minDis = dis;
                res = path;
            } else if (dis == minDis) {
                res = path.compareTo(res) < 0 ? path : res;
            }
        } else {
            //对四个方向进行寻找
            //向上查找
            if (node[0] - 1 >= 0 && matrix[node[0] - 1][node[1]] != 1) {
                //向上一直找到顶点
                int upPoint = node[0];
                while (upPoint > 0 && matrix[upPoint - 1][node[1]] == 0) {
                    upPoint--;
                }

                //判断是否经过终点，经过就取终点为下一个点，没到则取转弯点为下一点
                if (node[1] == des[1] && des[0] >= upPoint && des[0] < node[0]) {
                    upPoint = des[0];
                }

                int newDis = dis + (node[0] - upPoint);
                //当经过该点且往上的方向没有走过，或者往该方向走到的步数比当前走到该点步数多时才继续寻找，这里判断不好会导致部分用例不过
                if (newDis <= minDis && (visit[node[0]][node[1]][0] == 0 || visit[node[0]][node[1]][0] >= dis)) {
                    String newPath = path + "u";
                    queue.add(new int[]{upPoint, node[1]});
                    pathQueue.add(newPath);
                    disQueue.add(newDis);

                    visit[node[0]][node[1]][0] = dis;
                }
            }

            if (node[0] + 1 < matrix.length && matrix[node[0] + 1][node[1]] != 1) {
                int downPoint = node[0];
                while (downPoint < matrix.length - 1 && matrix[downPoint + 1][node[1]] == 0) {
                    downPoint++;
                }

                //判断是否经过终点，经过就取终点为下一个点，没到则取转弯点为下一点
                if (node[1] == des[1] && des[0] <= downPoint && des[0] > node[0]) {
                    downPoint = des[0];
                }

                int newDis = dis + (downPoint - node[0]);
                if (newDis <= minDis && (visit[node[0]][node[1]][1] == 0 || visit[node[0]][node[1]][1] >= dis)) {
                    String newPath = path + "d";
                    queue.add(new int[]{downPoint, node[1]});
                    pathQueue.add(newPath);
                    disQueue.add(newDis);

                    visit[node[0]][node[1]][1] = dis;
                }
            }

            if (node[1] - 1 >= 0 && matrix[node[0]][node[1] - 1] != 1) {
                int leftPoint = node[1];
                while (leftPoint > 0 && matrix[node[0]][leftPoint - 1] == 0) {
                    leftPoint--;
                }

                //判断是否经过终点，经过就取终点为下一个点，没到则取转弯点为下一点
                if (node[0] == des[0] && des[1] >= leftPoint && des[1] < node[1]) {
                    leftPoint = des[1];
                }

                int newDis = dis + (node[1] - leftPoint);
                if (newDis <= minDis && (visit[node[0]][node[1]][2] == 0 || visit[node[0]][node[1]][2] >= dis)) {
                    String newPath = path + "l";
                    queue.add(new int[]{node[0], leftPoint});
                    pathQueue.add(newPath);
                    disQueue.add(newDis);

                    visit[node[0]][node[1]][2] = dis;
                }
            }

            if (node[1] + 1 < matrix[0].length && matrix[node[0]][node[1] + 1] != 1) {
                int rightPoint = node[1];
                while (rightPoint < matrix[0].length - 1 && matrix[node[0]][rightPoint + 1] == 0) {
                    rightPoint++;
                }

                //判断是否经过终点，经过就取终点为下一个点，没到则取转弯点为下一点
                if (node[0] == des[0] && des[1] > node[1] && des[1] <= rightPoint) {
                    rightPoint = des[1];
                }

                int newDis = dis + (rightPoint - node[1]);
                if (newDis <= minDis && (visit[node[0]][node[1]][3] == 0 || visit[node[0]][node[1]][3] >= dis)) {
                    String newPath = path + "r";
                    queue.add(new int[]{node[0], rightPoint});
                    pathQueue.add(newPath);
                    disQueue.add(newDis);

                    visit[node[0]][node[1]][3] = dis;
                }
            }
        }
    }

    return res;
}
```

