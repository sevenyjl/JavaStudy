package simulation.professional.d20211014;

/**
 * 某简易的linux目录系统 cd 命令（change directory）功能如下：
 * <p>
 * cd ：进入home目录/home/user；
 * cd -：进入上一次停留的目录（连续两个以上 cd - 会在两个目录之间来回跳转，而不是回到更早之前的目录）；
 * cd <绝对路径>：以 / 开头的为绝对路径；
 * cd <相对路径>：不以/开头的为相对路径；
 * 现给定一批 cd 命令，命令格式及相关假设如下：
 * <p>
 * 单独的/表示根目录，且为系统默认的初始目录；
 * ////a表示目录/a，即这种格式中的多个 / 等价于一个 / ；
 * 特殊目录. ，表示当前层次目录，例如/a/. 表示目录/a；
 * 特殊目录..，表示当前层次目录的父目录（根目录的父目录仍为根），例如/a/..表示目录/；
 * 假设不会出现第一个命令为cd -的情况；
 * 请计算所有命令执行结束后的最终目录，并格式化输出其绝对路径：
 * <p>
 * 必须以/开头，不以/结尾（除根目录外）；
 * 不允许特殊目录.、..，不允许连续的/，需转化为所表示的目录；
 * 输入
 * 首行一个整数 n，表示 cd 命令的个数，取值范围：[1,100]
 * 接下来 n 行，每行一条 cd 命令，长度范围 [1, 100]，其中目录名仅由小写英文字母 [a-z] 组成
 * <p>
 * 假设：
 * <p>
 * 设目录总长度 < 10000
 * 命令中的目录都是存在的
 * 命令格式都符合规则
 * 输出
 * 一个字符串，表示最终目录的绝对路径。
 * <p>
 * 样例
 * 输入样例1 复制
 * 3
 * cd /aa/bb/cc/dd/
 * cd ./ee/ff
 * cd gg/../hh
 * 输出样例1 复制
 * /aa/bb/cc/dd/ee/ff/hh
 * 提示样例1
 * 默认目录在 / ，各条命令执行情况如下：
 * <p>
 * 第一条：路径名以 / 开头表示绝对路径，执行后当前目录为 /aa/bb/cc/dd/
 * 第二条：路径名不以 / 开头表示相对路径，进入当前目录的下层目录，执行后当前目录为 /aa/bb/cc/dd/ee/ff
 * 第三条：路径名不以 / 开头表示相对路径，gg/../hh 表示先进入下层目录 gg，接着返回 gg 的上层目录即当前目录，然后进入下层目录 hh， 因此最终目录为 /aa/bb/cc/dd/ee/ff/hh
 * 输入样例2 复制
 * 2
 * cd
 * cd ..
 * 输出样例2 复制
 * /home
 * 提示样例2
 * 第一条：进入home目录 /home/user
 * 第二条：进入当前目录的父目录即 /home
 * <p>
 * 输入样例3 复制
 * 3
 * cd /aa/bb//
 * cd ..
 * cd -
 * 输出样例3 复制
 * /aa/bb
 * 提示样例3
 * 第一条：多个 / 等价于一个，当前目录为 /aa/bb
 * 第二条：进入父目录后当前目录为 /aa，上一次停留的目录为 /aa/bb
 * 第三条：进入上一次停留的目录，最终目录为 /aa/bb
 * <p>
 * <p>
 * OJ考题代码：简易Linux路径解析
 *
 * @author 命题组
 * @since 2021-02-05
 */

public class T2 {

    // 待实现函数，在此函数中填入答题代码
    private static String getCurrentDirectory(String[] cmds) {
        String temp = "/";
        String now = "/";
        for (String cmd : cmds) {
            cmd = cmd.trim();
            if ("cd".equals(cmd)) {
                temp = now;
                now = "/home/user/";
            } else if ("cd -".equals(cmd)) {
                String s = temp;
                temp = now;
                now = s;
            } else if (cmd.startsWith("/")) {
                temp = now;
                now = cmd;
            } else {
                temp = now;
                now += cmd.substring(3) + "/";
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : now.split("/")) {
            if (s.equals(".")) {
            } else if (s.equals("..")) {
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.lastIndexOf("/")));
            } else if (!s.equals("")) {
                stringBuilder.append("/").append(s);
            }
        }
        return stringBuilder.toString().equals("") ? "/" : stringBuilder.toString();
    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        // Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        // int row = cin.nextInt();
        // cin.nextLine();
        // String[] cmds = new String[row];
        // for (int i = 0; i < row; i++) {
        //     cmds[i] = cin.nextLine();
        // }
        // cin.close();
        String[] cmds = new String[] {
            "cd ", "cd aa//bb/cc", "cd -", "cd /abcd/abcd", "cd -", "cd aa/bb/cc"
        };
        System.out.println(getCurrentDirectory(cmds));
        cmds = new String[] {
            "cd ", "cd -", "cd /abc", "cd -", "cd -", "cd -", "cd aa/bb/../cc/../bb"
        };
        System.out.println(getCurrentDirectory(cmds));
        cmds = new String[] {
            "cd /////////////////", "cd", "cd -", "cd ./aa/bb/cc/dd/", "cd ../ee/ff",
            "cd gg/../.././././hh////////////../."
        };
        System.out.println(getCurrentDirectory(cmds));
        cmds = new String[] {
            "cd /aa/bb//", "cd ..", "cd -", "cd -", "cd -"
        };
        System.out.println(getCurrentDirectory(cmds));
        cmds = new String[] {
            "cd a", "cd .."
        };
        System.out.println(getCurrentDirectory(cmds));
        cmds = new String[] {
            "cd ./a", "cd .."
        };
        System.out.println(getCurrentDirectory(cmds));
        cmds = new String[] {
            "cd ./a", "cd ..","cd -"
        };
        System.out.println(getCurrentDirectory(cmds));
    }
}




