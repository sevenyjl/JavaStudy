package simulation.work.d20211019;

import java.util.HashMap;

/**
 * 在一个简易日志系统中，每条日志有唯一的id，以及它的生成时间timestamp(生成时间不一定唯一，ID和timestamp均为正整数)。请设计它的函数接口，以实现以下功能:
 * (1) void Add(int id, int timestamp)：添加一条日志
 * (2) int Delete(int id)：删除id对应的那条日志，如果日志不存在返回-1，删除成功返回0
 * (3) int Query(int startTime, int endTime)：查询一段时间内（包含两个时间点）生成的日志数量，如果没有则返回0。
 * 输入：["LogSystem","Add","Add","Add","Query","Delete","Delete","Query"] [[],[1,5],[2,5],[3,6],[5,6],[2],[4],[5,6]]
 * 输出：[null,null,null,null,3,0,-1,2]
 * 解释：第一个操作是初始化，没有返回值。前三个Add操作加入了ID为1、2、3的日志，timestamp分别为5、5、6。
 * 第一次Query操作查询timestamp范围为[5, 6]的日志数量，返回3；第一次Delete操作删除了ID为2的日志，删除成功，返回0；
 * 第二次Delete操作试图删除ID为4的日志，没有该日志，操作失败，返回-1；
 * 最后一次Query操作查询timestamp范围为[5, 6]的日志数量，由于该范围中已经有一篇日志被删除了，故返回2。
 * 限制：
 * <p>
 * 1 <= id <= 10^9
 * 1 <= timestamp <= 10^9
 * Add最多调用1000次，Delete最多调用1000次，Query最多调用50次
 */
public class T2 {
    public static void main(String[] args) {
        int[][] ints = {{}, {1, 5}, {2, 5}, {3, 6}, {5, 6}, {2}, {4}, {5, 6}};
        // ["RLogSystem","Add","Add","Add","Query","Delete","Delete","Query"] [[],[1,5],[2,5],[3,6],[5,6],[2],[4],[5,6]]
        // "RLogSystem"-->[] 初始化
        // "Add"       -->[1,5] id=1 time=5
        // "Add"       -->[2,5] id=2 time=5
        // "Add"       -->[3,6] id=3 time=6
        // "Query"     -->[5,6] startTime=5 endTime=6 -->返回日志数量3
        run(new String[] {"RLogSystem", "Add", "Add", "Add", "Query", "Delete", "Delete", "Query"}, ints);
    }

    static HashMap<Integer, Integer> idAndTime = new HashMap<>();

    public static void run(String[] strings, int[][] ints) {
        for (int i = 0; i < strings.length; i++) {
            switch (strings[i]) {
                case "RLogSystem":
                    idAndTime.clear();
                    break;
                case "Add":
                    add(ints[i][0], ints[i][1]);
                    break;
                case "Query":
                    System.out.println(query(ints[i][0], ints[i][1]));
                    break;
                case "Delete":
                    System.out.println(delete(ints[i][0]));
                    break;
                default:
                    throw new RuntimeException("无效命令");
            }
        }
    }

    public static void add(int id, int timestamp) {
        idAndTime.put(id, timestamp);
    }

    public static int delete(int id) {
        Integer remove = idAndTime.remove(id);
        return remove == null ? -1 : 0;
    }

    public static int query(int startTime, int endTime) {
        return (int) idAndTime.values().stream().filter(s -> s >= startTime && s <= endTime).count();
    }
}
