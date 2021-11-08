package com.seven.project;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.word.Word07Writer;
import cn.hutool.poi.word.WordUtil;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class WordDemo {
    public static void main(String[] args) throws IOException {
//        String pngPath = "C:\\Users\\y30016814\\Desktop\\temp\\Views";
//        String outPath = pngPath + "/png-orc-" + DateUtil.format(new Date(), "yyyyHHmm hhMMss") + ".docx";
//
//        Word07Writer writer = WordUtil.getWriter(new File(outPath));
//        Font font = new Font("宋体", Font.PLAIN, 15);
//
//        String file = Objects.requireNonNull(WordDemo.class.getClassLoader().getResource("Windows.Media.Ocr.Cli.exe"))
//            .getFile()
//            .substring(1);
//        List<File> files = FileUtil.loopFiles(pngPath, (f) -> f.getName().endsWith(".png"));
//        for (File f : files) {
//            Process exec = Runtime.getRuntime().exec(file + " \"" + f.getAbsolutePath() + "\"");
//            String read = IoUtil.read(new BufferedReader(new InputStreamReader(exec.getInputStream(), "GBK")));
//            // System.out.println(read);
//            writer.addText(font, read);
//            writer.addPicture(f,500,350);
//        }
//        writer.close();
    }
}
