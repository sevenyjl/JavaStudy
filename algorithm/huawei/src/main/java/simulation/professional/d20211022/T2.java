package simulation.professional.d20211022;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author y30016814
 * @since 2021/11/10 15:42
 * 20211022-Java科目一专业级
 * http://3ms.huawei.com/km/blogs/details/11229973
 * http://3ms.huawei.com/km/groups/2869253/blogs/details/11242025
 */
public class T2 {
    public static void main(String[] args) {
        // BookingSystem obj = new BookingSystem(); // 返回 null 表示无输出
        // System.out.println(obj.addLab(3, 4, 19));
        // System.out.println(obj.addLab(5, 2, 3));
        // System.out.println(obj.bookTime(2, 12, 15));
        // System.out.println(obj.bookTime(3, 12, 13));
        // System.out.println(obj.cancelBooking(2));
        // 返回 null 表示无输出
        BookingSystem obj = new BookingSystem();
        // 返回 true
        System.out.println(obj.addLab(2, 8, 17));
        // 与已有开放时段重叠，返回 false
        System.out.println(obj.addLab(2, 2, 10));
        // 添加后开放时段为[8,30)，返回 true
        System.out.println(obj.addLab(2, 17, 30));
        // 返回 2
        System.out.println(obj.bookTime(1, 9, 28));
        // 返回 true（时段释放可再被预约）
        System.out.println(obj.cancelBooking(1));
        // 返回 true
        System.out.println(obj.addLab(6, 1, 30));
        // 此时段可被预约的实验室编号为 2、6，但由于其中最早添加到系统的实验室为 2 号实验室，故返回 2
        System.out.println(obj.bookTime(13, 8, 27));
        // 由于实验室 2 的未预约时段为[27,30)，所以预约不上，只能预约到实验室 6，故返回 6
        System.out.println(obj.bookTime(9, 26, 30));
        // 不存在编号为 10 的预约记录，取消失败，返回 false
        System.out.println(obj.cancelBooking(10));
    }

}

class BookingSystem {

    LinkedHashMap<Integer, Room> idRoomMap;
    HashMap<Integer, Integer> recordIdLabIdMap;

    public BookingSystem() {
        idRoomMap = new LinkedHashMap<>();
        recordIdLabIdMap = new HashMap<>();
    }

    public boolean addLab(int labId, int startTime, int endTime) {
        Room room = idRoomMap.getOrDefault(labId, new Room(startTime, endTime));
        if (room.startTime >= startTime && endTime < room.endTime) {
            return false;
        } else {
            room.startTime = Math.min(startTime, room.startTime);
            room.endTime = Math.max(endTime, room.endTime);
        }
        idRoomMap.put(labId, room);
        return true;
    }

    public int bookTime(int recordId, int fromTime, int toTime) {
        Map.Entry<Integer, Room> integerRoomEntry = idRoomMap.entrySet()
            .stream()
            .filter(k -> k.getValue().bookRoom(recordId, fromTime, toTime))
            .findFirst().orElse(null);
        if (integerRoomEntry == null) {
            return -1;
        }
        recordIdLabIdMap.put(recordId, integerRoomEntry.getKey());
        return integerRoomEntry.getKey();
    }

    public boolean cancelBooking(int recordId) {
        Integer remove = recordIdLabIdMap.remove(recordId);
        if (remove == null) {
            return false;
        }
        return idRoomMap.get(remove).cancelBooking(recordId);
    }

    private static class Room {
        public int startTime;
        public int endTime;
        private final HashMap<Integer, int[]> bookMap = new HashMap<>();

        public Room(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        private boolean bookRoom(int recordId, int startTime, int endTime) {
            // 判断预定时间是否超过room 开放时间
            if (startTime >= this.startTime && endTime <= this.endTime) {
                for (int[] value : bookMap.values()) {
                    // 判断当前预定时间是否在 已经预定的范围交界（注意 预定时间满足[fromTime,toTime)）
                    if (startTime >= value[0] && value[1] < endTime) {
                        return false;
                    }
                }
                // 进行预定
                bookMap.put(recordId, new int[] {startTime, endTime});
                return true;
            }
            return false;
        }

        private boolean cancelBooking(int recordId) {
            return bookMap.remove(recordId) != null;
        }

    }
}
