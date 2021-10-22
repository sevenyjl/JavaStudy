package com.seven.project;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.List;

public class FileUtils {
    public static void main(String[] args) {
        //文件后缀重命名工具
        String dir = "C:\\Users\\y30016814\\Desktop\\temp\\Views";
        String prefix = ".aspx";
        String replacePrefix = ".png";
        List<File> files = FileUtil.loopFiles(dir,
            pathname -> pathname.getName().endsWith(StrUtil.isEmpty(prefix) ? "" : prefix));
        files.forEach(s-> FileUtil.rename(s,s.getName().replace(prefix,replacePrefix),true));
    }
}
