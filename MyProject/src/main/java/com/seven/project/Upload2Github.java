package com.seven.project;

import cn.hutool.core.io.FileUtil;

public class Upload2Github {
    //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

    public static void update(String s, String outFile, int count) {
        if (s.length() < 32048) {
            //update
            FileUtil.appendUtf8String(s, outFile+count);
        } else {
            String substring = s.substring(0, 32048);
            //update substring
            FileUtil.writeUtf8String(substring, outFile+count);
            s = s.substring(32048);
            update(s, outFile, ++count);
        }
    }
}
