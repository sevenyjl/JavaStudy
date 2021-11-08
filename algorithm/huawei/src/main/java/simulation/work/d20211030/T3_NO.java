package simulation.work.d20211030;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.HexUtil;

/**
 * http://3ms.huawei.com/km/blogs/details/7725323?l=zh-cn
 * @author y30016814
 * @since 2021/10/30 17:02
 */
public class T3_NO {
    private static Map<Character, String> map = new HashMap<Character, String>();

    static {
        map.put('0', "0000");
        map.put('1', "0001");
        map.put('2', "0010");
        map.put('3', "0011");
        map.put('4', "0100");
        map.put('5', "1000");
        map.put('6', "1001");
        map.put('7', "1010");
        map.put('8', "1011");
        map.put('9', "1100");
    }

    public static void main(String[] args) {
    }
}
