package simulation.work.d20211021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 某核心网设备向计费网关发送话单（一个话单指一条通话记录的信息），发送规则如下：
 * <p>
 * 每个话单具有长度和优先级两个属性，优先级值越小表示优先级越高，高优先级的发送完，才能发送次优先级的。
 * 设备有一个承载规格，表示发送话单总容量的阈值，发送话单的总长度不能超过承载规格。
 * 现给定设备的承载规格和待发送话单（长度和优先级）列表，请计算最多可以发送多少个话单。
 * <p>
 * 输入
 * 第一行是正整数 cap ，表示设备的承载规格，取值范围：[1,10000]
 * 第二行是正整数 num ，表示待发送话单的数量，取值范围：[0,100]
 * 第三行 num 个整数，依次表示每个待发送话单的长度，每个值的范围：[0, 1000]
 * 第四行 num 个整数，依次表示每个待发送话单的优先级，每个值的范围：[0,30]
 * <p>
 * 第三行和第四行的数据一一对应，表示同一个话单的长度和优先级。
 * <p>
 * 输出
 * 输出一个整数，表示最多能发送话单的个数。
 * <p>
 * 样例
 * 输入样例1 复制
 * 110
 * 5
 * 50 20 30 10 50
 * 2 2 1 3 1
 * 输出样例1 复制
 * 3
 * 提示样例1
 * 首先尝试发送优先级为 1 的话单，长度分别是30和50，长度之和在承载规格范围内，优先级 1 的两个话单全部完成发送，剩余容量为30。
 * 接着尝试发送优先级为 2 的话单，长度20的被发送，剩余容量为10，长度50的无法发送。
 * 因优先级 2 的话单未发送完（仍剩余一条），优先级3的所有话单都无法发送。
 * 所以，最多能发送的话单数为 3 。
 */
public class T2 {
    private static int getMaxSendNumber(int cap, int[] billLen, int[] billPrior) {
        int[][] ints = new int[billLen.length][2];
        for (int i = 0; i < billPrior.length; i++) {
            ints[i][0] = billPrior[i];
            ints[i][1] = billLen[i];
        }
        int count = 0;
        for (int[] ints1 : Arrays.stream(ints)
            .sorted((o1, o2) -> o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0])
            .collect(Collectors.toList())) {
            if (cap >= ints1[1]) {
                cap = cap - ints1[1];
                count++;
            } else {
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getMaxSendNumber(110, new int[] {50, 20, 30, 10, 50}, new int[] {2, 2, 1, 3, 1}));
    }

}
