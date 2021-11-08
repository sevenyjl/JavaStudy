package simulation.professional.d20211030;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author y30016814
 * @since 2021/10/30 15:27
 */
public class T3 {
    public static void main(String[] args) {
        Run3 groupBuying = new Run3(2);
        groupBuying.join(1);
        groupBuying.join(2);
        groupBuying.join(3);
        groupBuying.join(4);
        groupBuying.removeLast();
        groupBuying.removeFrom(4);
        groupBuying.join(12);
        groupBuying.removeLast();
        groupBuying.join(2);
        groupBuying.join(3);
        groupBuying.join(4);
        groupBuying.removeLast();
        groupBuying.removeFrom(4);
        groupBuying.join(12);
        groupBuying.removeLast();
        groupBuying.join(2);
        groupBuying.join(3);
        groupBuying.join(4);
        groupBuying.removeLast();
        groupBuying.removeFrom(4);
        groupBuying.join(12);
        groupBuying.removeLast();
    }

}

class Run3 {
    GroupBuying groupBuying;
    GroupBuying2 groupBuying2;

    public Run3(int eachSize) {
        groupBuying = new GroupBuying(eachSize);
        groupBuying2 = new GroupBuying2(eachSize);
    }

    public void join(int id) {
        groupBuying.join(id);
        groupBuying2.join(id);
    }

    public void removeFrom(int id) {
        System.out.println("groupBuying=" + groupBuying.removeFrom(id));
        System.out.println("groupBuying2=" + groupBuying2.removeFrom(id));
    }

    public void removeLast() {
        System.out.println("groupBuying=" + groupBuying.removeLast());
        System.out.println("groupBuying2=" + groupBuying2.removeLast());
    }
}

class GroupBuying {
    int max;
    int row = 0;
    ArrayList<int[]> ints = new ArrayList<>();

    public GroupBuying(int max) {
        this.max = max;
    }

    public int removeLast() {
        if (ints.size() > 0) {
            int i = removeFrom(row);
            if (i == -1) {
                ints.remove(ints.size() - 1);
                row--;
                return removeLast();
            } else {
                return i;
            }
        }
        return -1;
    }

    public int removeFrom(int groupIndex) {
        if (groupIndex < ints.size()) {
            int[] temp = this.ints.get(groupIndex);
            for (int i = temp.length - 1; i >= 0; i--) {
                int num = temp[i];
                if (num != 0) {
                    temp[i] = 0;
                    row = groupIndex;
                    return num;
                }
            }
            return -1;
        }
        return -1;
    }

    public void join(int num) {
        if (ints.size() - 1 < row) {
            int[] temp = new int[max];
            addIn(temp, num);
            ints.add(temp);
        } else {
            int[] temp = this.ints.get(row);
            if (!addIn(temp, num)) {
                row++;
                join(num);
            }
        }
    }

    private boolean addIn(int[] temp, int v) {
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                temp[i] = v;
                return true;
            }
        }
        return false;
    }
}

class GroupBuying2 {
    private List<List<Integer>> list;
    private int eachSize;

    public GroupBuying2(int eachSize) {
        this.list = new ArrayList<>();
        this.eachSize = eachSize;
    }

    public void join(int id) {
        boolean isInsert = false;
        for (List<Integer> item : list) {
            if (item.size() < eachSize) {
                item.add(id);
                isInsert = true;
                break;
            }
        }

        if (!isInsert) {
            List<Integer> addList = new LinkedList<>();
            addList.add(id);
            list.add(addList);
        }
    }

    public int removeFrom(int groupIndex) {
        if (groupIndex >= list.size()) {
            return -1;
        }
        List<Integer> group = list.get(groupIndex);
        int num;
        if (group.size() == 1) {
            num = group.get(0);
            list.remove(groupIndex);
        } else {
            num = group.remove(group.size() - 1);
        }
        return num;
    }

    public int removeLast() {
        if (list.size() == 0) {
            return -1;
        }
        return removeFrom(list.size() - 1);
    }

}
