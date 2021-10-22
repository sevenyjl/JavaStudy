package simulation.newcomers.d20211021;

import java.util.HashMap;

/**
 * 在一个简易日志系统中，每条日志有唯一的 ID，以及它的生成时间 timeStamp（ID 和 timeStamp 均为正整数）。根据给出的函数框架，请实现以下功能：
 * <p>
 * void Add(int id,int timeStamp)：增加一条新的日志记录，将这条日志记录存到系统中。
 * int Delete(int id)：在日志系统中尝试删除这个 ID 对应的日志记录。如果该日志 ID 在系统中不存在，返回 -1，否则删除这条日志，并返回 0。
 * int Query(int startTime,int endTime)：返回日志系统中生成时间大于等于 startTime 且小于等于 endTime 的日志数量。如果没有，返回 0。
 * 注：同一时间可能有多条日志。函数定义以对应语言的右侧实际框架为准。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["LogSystem","add","add","add","query","delete","delete","query"]
 * [[],[1,5],[2,5],[3,6],[5,6],[2],[4],[5,6]]
 * 输出：
 * [null,null,null,null,3,0,-1,2]
 * 解释：
 * 第一个操作是初始化，没有返回值；
 * 前三个 Add 操作加入了 ID 为 1、2、3 的日志，timeStamp 分别为 5、5、6；
 * 第一次 Query 操作查询 timeStamp 范围为[5,6]的日志数量，返回 3；
 * 第一次 Delete 操作删除了 ID 为 2 的日志，删除成功，返回 0；
 * 第二次 Delete 操作试图删除 ID 为 4 的日志，没有该日志，操作失败，返回 -1；
 * 最后一次 Query 操作查询 timeStamp 范围为[5,6]的日志数量，由于该范围中已经有一条日志被删除了，故返回 2。
 * 注：输出中的 null 表示此对应函数无输出（等同于 C 语言的 void 类型）。
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= id <= 10^9
 * 1 <= timeStamp <= 10^9
 * 每次 Add 调用中的 id 是唯一的
 * Add 最多调用 1000 次，Delete 最多调用 1000 次，Query 最多调用 50 次
 * <p>
 * <p>
 * 初始化及调用参考（以 C 为例，其他语言参考）：
 * <p>
 * /*
 * * Your LogSystem struct will be instantiated and called as such:
 * * LogSystem* obj = LogSystemCreate();
 * * LogSystemAdd(obj, id, timeStamp);
 * * int param_2 = LogSystemDelete(obj, id);
 * * int param_3 = LogSystemQuery(obj, start, end);
 * * LogSystemFree(obj);
 */

public class T1 {

   private static HashMap<Integer, Integer> idAndTime = new HashMap<>();

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

class LogSystem{
    private HashMap<Integer, Integer> idAndTime;

    public LogSystem() {
        idAndTime = new HashMap<>();
    }
    public  void add(int id, int timestamp) {
        this.idAndTime.put(id, timestamp);
    }

    public  int delete(int id) {
        Integer remove = idAndTime.remove(id);
        return remove == null ? -1 : 0;
    }

    public  int query(int startTime, int endTime) {
        return (int) idAndTime.values().stream().filter(s -> s >= startTime && s <= endTime).count();
    }
}
