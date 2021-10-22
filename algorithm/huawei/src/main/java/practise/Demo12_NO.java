package practise;

import java.util.Scanner;

/**
 * http://oj.rnd.huawei.com/problems/12/details
 * 题目描述
 * Word Maze 是一个网络小游戏，你需要找到以字母标注的食物，但要求以给定单词字母的顺序吃掉。假设给定单词if，你必须先吃掉i然后才能吃掉f。
 * 但现在你的任务可没有这么简单，你现在处于一个迷宫Maze（n×m的矩阵）当中，里面到处都是以字母标注的食物，但你只能吃掉能连成给定单词W的食物。
 * <p>
 * 注意区分英文字母大小写,并且你只能上下左右行走。
 * <p>
 * 解答要求
 * 时间限制：1000ms, 内存限制：100MB
 * 输入
 * 输入第一行包含两个整数n、m(0<n,m<21)分别表示n行m列的矩阵，第二行是长度不超过100的单词W，从第3行到第n+2行是只包含大小写英文字母的长度为m的字符串。
 * <p>
 * 输出
 * 如果能在地图中连成给定的单词，则输出“YES”，否则输出“NO”。注意：每个字母只能用一次。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 5 5
 * SOLO
 * CPUCY
 * EKLQH
 * CRSOL
 * EKLQO
 * PGRBC
 * 输出样例 1
 * <p>
 * YES
 */
public class Demo12_NO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        String[] split = nextLine.split(" ");
        int hang = Integer.parseInt(split[0]);
        int lie = Integer.parseInt(split[1]);
        String word = scanner.nextLine();
        char[][] maze = new char[hang][lie];
        for (int i = 0; i < hang; i++) {
            maze[i] = scanner.nextLine().toCharArray();
        }
        //遍历查询起点
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < maze.length; i++) {
            char[] chars = maze[i];
            for (int j = 0; j < chars.length; j++) {
                char aChar = chars[j];
                // System.out.print(aChar);
                if (aChar == wordChars[0]) {
                    //开始爬寻
                    // System.out.printf("第%s,%s", i, j);
                    if (checkAround(i, j, hang, lie, maze, wordChars)) {
                        System.out.println("YES");
                        return;
                    } else {
                        //这个起点不行
                        break;
                    }
                }
            }
            // System.out.println();
        }
        System.out.println("NO");
    }

    public static boolean checkAround(int x, int y, int maxX, int maxY, char[][] maze, char[] checkWord) {
        char[][] mazeTemp = maze.clone();
        mazeTemp[x][y] = ' ';
        for (int i = 1; i < checkWord.length; i++) {
            if (x + 1 < maxX && mazeTemp[x + 1][y] == checkWord[i]) {
                mazeTemp[x + 1][y] = ' ';
                x = x + 1;
            } else if (x > 0 && mazeTemp[x - 1][y] == checkWord[i]) {
                mazeTemp[x - 1][y] = ' ';
                x = x - 1;
            } else if (y + 1 < maxY && mazeTemp[x][y + 1] == checkWord[i]) {
                mazeTemp[x][y + 1] = ' ';
                y = y + 1;
            } else if (y > 0 && mazeTemp[x][y - 1] == checkWord[i]) {
                mazeTemp[x][y - 1] = ' ';
                y = y - 1;
            } else {
                return false;
            }
        }
        return true;
    }
}
