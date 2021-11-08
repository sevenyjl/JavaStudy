package simulation.professional.d20211104;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author y30016814
 * @since 2021/11/4 16:25
 */
public class T2 {
    public static void main(String[] args) {
        DataMachineSystem dataMachineSystem = new DataMachineSystem(3);
        System.out.println(dataMachineSystem.transferData(1, 2, 17));
        // dataMachineSystem.show();
        dataMachineSystem.transferDataToAll(2, 29);
        // dataMachineSystem.show();
        System.out.println(dataMachineSystem.queryContribution(2));
        System.out.println("-----------------------------------------");
        DataMachineSystem2 dataMachineSystem2 = new DataMachineSystem2(3);
        System.out.println(dataMachineSystem2.transferData(1, 2, 17));
        // dataMachineSystem2.show();
        dataMachineSystem2.transferDataToAll(2, 29);
        // dataMachineSystem2.show();
        System.out.println(dataMachineSystem2.queryContribution(2));
    }

}

class DataMachineSystem {
    private int[] gong;
    private List<List<Integer>> haveData = new ArrayList<>();

    public DataMachineSystem(int num) {
        gong = new int[num];
        for (int i = 0; i < num; i++) {
            haveData.add(new ArrayList<>());
        }
    }

    public int transferData(int machineA, int machineB, int dataId) {
        productData(machineA - 1, dataId);
        List<Integer> bList = haveData.get(machineB - 1);
        if (!bList.contains(dataId)) {
            // 接收数据
            bList.add(dataId);
            gong[machineA - 1] += 10;
            return 1;
        }
        return 0;
    }

    public void show() {
        System.out.println("贡献值：");
        for (int i = 0; i < gong.length; i++) {
            System.out.println(i + 1 + "=" + gong[i]);
        }
        System.out.println("拥有值");
        for (int i = 0; i < haveData.size(); i++) {
            System.out.println(i + 1 + "=" + haveData.get(i));
        }
    }

    private void productData(int machine, int dataId) {
        List<Integer> list = haveData.get(machine);
        if (!list.contains(dataId)) {
            // 生产数据
            list.add(dataId);
        }
    }

    public void transferDataToAll(int machine, int dataId) {
        productData(machine - 1, dataId);
        for (List<Integer> haveDatum : haveData) {
            if (!haveDatum.contains(dataId)) {
                gong[machine - 1] += 10;
                haveDatum.add(dataId);
            }
        }
    }

    public int queryContribution(int machine) {
        return gong[machine - 1];
    }
}

class DataMachineSystem2 {
    int[][] machines;

    int[] contribute;

    int[][] dataLast;

    public DataMachineSystem2(int num) {
        machines = new int[num + 1][1000 + 1];
        contribute = new int[num + 1];
        dataLast = new int[num + 1][1000 + 1];
    }

    public int transferData(int machineA, int machineB, int dataId) {
        if (machines[machineB][dataId] == 1) {
            return 0;
        }
        if (machines[machineA][dataId] == 0) {
            machines[machineA][dataId] = 1;
        }
        machines[machineB][dataId] = 1;
        dataLast[machineB][dataId] = machineA;
        int contributor = machineA;
        while (contributor != 0) {
            contribute[contributor] += 10;
            contributor = dataLast[contributor][dataId];
        }
        return 1;
    }

    public int transferDataToAll(int machine, int dataId) {
        int count = 0;
        for (int i = 1; i < machines.length; i++) {
            if (i != machine) {
                count += transferData(machine, i, dataId);
            }
        }
        return count;
    }

    public int queryContribution(int machine) {
        return contribute[machine];
    }

    public void show() {
        System.out.println("贡献值：");
        for (int i = 0; i < contribute.length; i++) {
            System.out.println(i + 1 + "=" + contribute[i]);
        }
        System.out.println("拥有值");
        for (int i = 0; i < dataLast.length; i++) {
            System.out.println(i + 1 + "=" + Arrays.toString(dataLast[i]));
        }
    }
}