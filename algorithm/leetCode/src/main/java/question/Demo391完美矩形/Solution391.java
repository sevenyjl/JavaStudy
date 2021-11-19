package question.Demo391完美矩形;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author y30016814
 * @since 2021/11/16 9:17
 */
public class Solution391 {
    public static void main(String[] args) {
        // System.out.println(
        //     isRectangleCover(new int[][] {
        //         {0, 0, 4, 1}, {7, 0, 8, 2}, {6, 2, 8, 3}, {5, 1, 6, 3}, {4, 0, 5, 1}, {6, 0, 7, 2}, {4, 2, 5, 3},
        //         {2, 1, 4, 3}, {0, 1, 2, 2}, {0, 2, 2, 3}, {4, 1, 5, 2}, {5, 0, 6, 1}
        //     }));// true
        // System.out.println(
        //     isRectangleCover(new int[][] {
        //         {0, 0, 4, 1}, {7, 0, 8, 2}, {5, 1, 6, 3}, {6, 0, 7, 2}, {4, 0, 5, 1}, {4, 2, 5, 3}, {2, 1, 4, 3},
        //         {0, 2, 2, 3}, {0, 1, 2, 2}, {6, 2, 8, 3}, {5, 0, 6, 1}, {4, 1, 5, 2}
        //     }));// true
        System.out.println(
            isRectangleCover(new int[][] {
                {0, 0, 1, 1}, {1, 1, 2, 2}, {0, 0, 2, 1}
            }));// false
        System.out.println(
            isRectangleCover(new int[][] {
                {0, 0, 2, 1}, {1, 0, 2, 2}
            }));// false
    }

    /**
     * 找四个顶点，如果四个顶点有重复，那肯定false，然后求4个顶点围成的面积
     * 如果总面积=四个顶点面积那么成功
     * 最左下 最左上 最右下 最右上 的四个点只出现一次 其他点成对出现
     *
     * @param rectangles
     * @return
     */
    public static boolean isRectangleCover(int[][] rectangles) {
        Set<String> set = new HashSet<>();
        int total = 0;
        int[] point = rectangles[0].clone();
        for (int[] rectangle : rectangles) {
            point[0] = Math.min(point[0], rectangle[0]);
            point[1] = Math.min(point[1], rectangle[1]);
            point[2] = Math.max(point[2], rectangle[2]);
            point[3] = Math.max(point[3], rectangle[3]);
            total += sumArea(rectangle);
            //分别记录小矩形的坐标
            String lt = rectangle[0] + "" + rectangle[3];
            String lb = rectangle[0] + "" + rectangle[1];
            String rt = rectangle[2] + "" + rectangle[3];
            String rb = rectangle[2] + "" + rectangle[1];
            //如果有就移除 没有就加入 todo 这里看不懂呀
            if (!set.contains(lt)) {
                set.add(lt);
            } else {
                set.remove(lt);
            }
            if (!set.contains(lb)) {
                set.add(lb);
            } else {
                set.remove(lb);
            }
            if (!set.contains(rt)) {
                set.add(rt);
            } else {
                set.remove(rt);
            }
            if (!set.contains(rb)) {
                set.add(rb);
            } else {
                set.remove(rb);
            }
        }
        int maxArea = sumArea(point);
        //最后只剩4个大矩形坐标且面积相等即为完美矩形
        return (set.size() == 4 && set.contains(point[0] + "" + point[3]) && set.contains(point[0] + "" + point[1])
            && set.contains(
            point[2] + "" + point[1]) && set.contains(point[2] + "" + point[3]) && total == maxArea);
    }

    public static int sumArea(int[] ints) {
        return (ints[3] - ints[1]) * (ints[2] - ints[0]);
    }
}
