package com.seven.project;

import java.io.IOException;

public class WordDemo {
    public static void main(String[] args) throws IOException {
        // String pngPath = "C:\\Users\\y30016814\\Desktop\\temp\\Views";
        // String outPath = pngPath + "/png-orc-" + DateUtil.format(new Date(), "yyyyHHmm hhMMss") + ".docx";
        //
        // Word07Writer writer = WordUtil.getWriter(new File(outPath));
        // Font font = new Font("宋体", Font.PLAIN, 15);
        //
        // String file = Objects.requireNonNull(WordDemo.class.getClassLoader().getResource("Windows.Media.Ocr.Cli.exe"))
        //     .getFile()
        //     .substring(1);
        // List<File> files = FileUtil.loopFiles(pngPath, (f) -> f.getName().endsWith(".png"));
        // for (File f : files) {
        //     Process exec = Runtime.getRuntime().exec(file + " \"" + f.getAbsolutePath() + "\"");
        //     String read = IoUtil.read(new BufferedReader(new InputStreamReader(exec.getInputStream(), "GBK")));
        //     // System.out.println(read);
        //     writer.addText(font, read);
        //     writer.addPicture(f,500,350);
        // }
        // writer.close();
    }
}
