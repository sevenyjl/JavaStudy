http://3ms.huawei.com/km/blogs/details/7489043

第一题：

leetcode链接：https://leetcode-cn.com/problems/prison-cells-after-n-days/

一组8个单bit寄存器，状态分别只能是1和0。每个时钟周期，各寄存器状态都会按以下规则更改：

    - 如果一个寄存器左右相邻寄存器全是0或全是1，则该寄存器在一个时钟周期后，其状态为1

    - 否则，其状态为0

（注意，最两边的寄存器，无法有左右都相邻寄存器。）

给定8个寄存器初始状态，及时钟周期数量cycleNum，求 cycleNum 个时钟周期后的所有寄存器状态。

示例 1：

输入：regs = [0,1,0,1,1,0,0,1], cycleNum = 7

输出：[0,0,1,1,0,0,0,0]

解释：

下表概述了寄存器每个时钟周期状态，,其中右边界的 1 按规则在第一个周期后变为 0:

Tick 0: [0, 1, 0, 1, 1, 0, 0, 1]

Tick 1: [0, 1, 1, 0, 0, 0, 0, 0]

Tick 2: [0, 0, 0, 0, 1, 1, 1, 0]

Tick 3: [0, 1, 1, 0, 0, 1, 0, 0]

Tick 4: [0, 0, 0, 0, 0, 1, 0, 0]

Tick 5: [0, 1, 1, 1, 0, 1, 0, 0]

Tick 6: [0, 0, 1, 0, 1, 1, 0, 0]

Tick 7: [0, 0, 1, 1, 0, 0, 0, 0]

所以7个时钟周期后的寄存器状态为：[0, 0, 1, 1, 0, 0, 0, 0]

示例 2：

输入：regs = [1,0,0,1,0,0,1,0], cycleNum = 1000000000

输出：[0,0,1,1,1,1,1,0]

解释：必须考虑大的 cycleNum 情况下的处理，全遍历(O(n)时间复杂度)方法通过的用例会很少。

提示：

- 寄存器数量固定为8个

- 给定初始状态只会为0或1

- 1 <= cycleNum <= 10^9

//根据题意，一次变化后左右两端的寄存器状态必然会变为0，中间6个寄存器总共只有有限种状态，所以必然会重复出现 public static int[] prisonAfterNDays(int[] cells, int N) {
//记录转换后的状态 int[] temp = new int[cells.length]; //记录所有变换的过程 List<String> strs = new ArrayList<>(); //记录从哪个点进入循环 int begin
= 0;

    for (int i = 1; i <= N; i++) {
        StringBuilder sb = new StringBuilder();
        //首末两个的点的变化不用计算
        for (int j = 1; j < cells.length - 1; j++) {
            if (cells[j - 1] == cells[j + 1]) {
                sb.append(1);
                temp[j] = 1;
            } else {
                sb.append(0);
                temp[j] = 0;
            }
        }
        cells = temp.clone();
        String tempStr = sb.toString();
        if (!strs.contains(tempStr)) {
            strs.add(tempStr);
        } else {
           for (int k = 0; k < strs.size(); k++) {
               if (strs.get(k).equals(tempStr)) {
                   begin = k;
                   break;
               }
           }
           break;
        }
    }

    int index = (N - begin) % (strs.size() - begin) + begin - 1;
    if (index == -1) {
        index = strs.size() - 1;
    }
    String ans = strs.get(index);
    for (int i  = 0; i < ans.length(); i++) {
        cells[i + 1] = Integer.parseInt(ans.charAt(i) + "");
    }
    return cells;

}

第二题：

同工作级第三题[做过了]

http://3ms.huawei.com/km/blogs/details/7488887

第三题：

有 N 个由小写字母组成的非空字符串列表 strings。你需要在这些字符串中选出 M 组，每组三个，记为S[0], S[1], ... S[3M-1]，满足：每组字符串存在公共前缀，且该前缀非空。每组前缀互不相同。请找出 M 的最大值。

例 1:

输入: strings = ["leetcode", "leet", "happycode", "lee"]

输出: 1

解释: 我们可以取["leetcode", "leet","lee"]，并令公共前缀为"l"或"le"或"lee"。

例 2:

输入: strings = ["l", "la", "lb", "lx", "lxa", "lxb"]

输出: 2

解释: 我们可以取["l", "la", "lb"]，并令它们公共前缀为"l";然后取["lx", "lxa", "lxb"]，并令它们公共前缀为"lx"。故最多是两组。

例 3:

输入: strings = ["l", "la", "lb", "lc", "ld", "le"]

输出: 1

解释: 这些字符串两两都只有一个公共前缀"l"。因为公共前缀需要保证唯一，我们只能取出一组。

限制:

1 <= N <= 1000

1 <= strings 中每个字符串的长度 <= 50

//没想到什么更加高效的解法，按最长的前缀直接开始匹配，先匹配前缀长的 public static int findMostPrefix(String[] strings) { //首先拿到所有字符串的最大长度 int mostLength
= 0; for (String s: strings) { if (s.length() > mostLength) { mostLength = s.length(); } }

    int count = 0;
    Set<String> used = new HashSet<>();
    for (int i = mostLength; i > 0; i--) {
        //记录字符串前缀相同的字符串
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strings) {
            if (s.length() >= i && !used.contains(s)) {
                List<String> list = map.getOrDefault(s.substring(0, i), new ArrayList<>());
                list.add(s);
                if (list.size() == 3) {
                    count++;
                    used.addAll(list);
                    break;
                }
                map.put(s.substring(0, i), list);
            }
        }
    }

    return count;

}
