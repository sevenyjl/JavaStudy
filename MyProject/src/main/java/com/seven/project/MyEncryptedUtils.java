package com.seven.project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

public class MyEncryptedUtils {
    public static void main(String[] args) throws IOException {
        // File file = new File("D:\\my\\question-master\\src\\main\\resources\\sql\\question.sql");
        // encryptedFile(file);
        // decodeFileText(new File("D:\\my\\question-master\\src\\main\\resources\\sql\\question.sql.seven"));
        decodeDirSplit(new File("C:\\Users\\Administrator\\Downloads"));
    }

    /**
     * 分割加密文件 100kb为分割单位
     *
     * @param file
     */
    public static void encryptedFileSplit(File file) {
        for (int i = 0; i < RandomUtil.randomInt(10, 30); i++) {
            FileUtil.appendUtf8String(new String(new byte[1024]), file);
        }
        if (FileUtil.size(file) > 100000) {
            BufferedInputStream inputStream = FileUtil.getInputStream(file);
            byte[] bytes = new byte[1000];
            int temp = -1;
            int index = 0;
            int count = 0;
            try {
                File outFile = new File(file.getCanonicalPath() + ".seven" + index);
                System.out.println(outFile.getAbsolutePath());
                BufferedOutputStream outputStream = FileUtil.getOutputStream(outFile);
                while ((temp = inputStream.read(bytes)) != -1) {
                    if (bytes[0] >= Byte.MAX_VALUE - 2) {
                        System.out.println("有风险");
                    }
                    bytes[0] += 1;
                    outputStream.write(bytes, 0, temp);

                    count++;
                    if (count >= 100) {
                        count = 0;
                        index++;
                        outputStream.close();
                        outFile = new File(file.getCanonicalPath() + ".seven" + index);
                        outputStream = FileUtil.getOutputStream(outFile);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IoUtil.close(inputStream);
            }
        }
    }

    /**
     * 分割解密文件 encryptedFileSeven
     *
     * @param dir
     */
    public static void decodeDirSplit(File dir) {
        List<File> files = FileUtil.loopFiles(dir, pathname -> pathname.getName().contains(".seven"));
        HashMap<String, List<File>> stringFileHashMap = new HashMap<>();
        files.forEach(f -> {
            String substring = null;
            try {
                substring = f.getCanonicalPath().substring(0, f.getCanonicalPath().lastIndexOf(".seven"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<File> orDefault = stringFileHashMap.getOrDefault(substring, new ArrayList<>());
            orDefault.add(f);
            stringFileHashMap.put(substring, orDefault);
        });
        stringFileHashMap.forEach((k, v) -> {
            File outFile = new File(k + ".decode");
            BufferedOutputStream outputStream = FileUtil.getOutputStream(outFile);
            v.stream().sorted().forEach(f -> {
                BufferedInputStream inputStream = FileUtil.getInputStream(f);
                byte[] bytes = new byte[1000];
                int temp = -1;
                try {
                    while ((temp = inputStream.read(bytes)) != -1) {
                        bytes[0] -= 1;
                        outputStream.write(bytes, 0, temp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IoUtil.close(inputStream);
                }
            });
            IoUtil.close(outputStream);
        });
    }


    /**
     * 仅仅支持word、rar、zip等非文本文件
     *
     * @param file
     */
    public static void encryptedFile(File file) throws IOException {
        String name = file.getName();
        switch (name.substring(name.lastIndexOf(".") + 1).toUpperCase(Locale.ROOT)) {
            case "PDF":
            case "DOCX":
            case "DOC":
            case "RAR":
            case "ZIP":
            case "PPT":
            case "EXE":
                extractedNoText(file);
                break;
            case "SEVEN":
                break;
            default:
                extractedText(file);
                System.out.println(name + "-->字符加密");

        }
    }

    private static void extractedNoText(File file) throws IOException {
        File outFile = new File(file.getCanonicalPath() + ".seven");
        BufferedInputStream inputStream = FileUtil.getInputStream(file);
        BufferedOutputStream outputStream = FileUtil.getOutputStream(outFile);
        byte[] bytes = new byte[2048];
        int temp = -1;
        try {
            while ((temp = inputStream.read(bytes)) != -1) {
                bytes[0] += 1;
                outputStream.write(bytes, 0, temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
        }
    }

    private static void extractedText(File file) throws IOException {
        File outFile = new File(file.getCanonicalPath() + ".seven");
        System.out.println(outFile.getAbsolutePath());
        FileUtil.writeString(encrypted(FileUtil.readUtf8String(file)), outFile, "UTF-8");
    }

    public static void decodeFile(File file) throws IOException {
        if (file.getName().endsWith(".seven")) {
            String name = file.getName().replace(".seven", "");
            switch (name.substring(name.lastIndexOf(".") + 1).toUpperCase(Locale.ROOT)) {
                case "PDF":
                case "DOCX":
                case "DOC":
                case "RAR":
                case "ZIP":
                case "PPT":
                case "EXE":
                    decodeFileNoText(file);
                    break;
                case "SEVEN":
                    break;
                default:
                    decodeFileText(file);
            }
        } else {
            System.out.println(file.getCanonicalPath() + "非加密文件");
        }
    }

    private static void decodeFileText(File file) throws IOException {
        File outFile = new File(file.getCanonicalPath().replace(".seven", ""));
        System.out.println(outFile.getAbsolutePath());
        FileUtil.writeUtf8String(MyEncryptedUtils.decode(FileUtil.readUtf8String(file)), outFile);
    }

    private static void decodeFileNoText(File file) throws IOException {
        File outFile = new File(file.getCanonicalPath().replace(".seven", ""));
        BufferedInputStream inputStream = FileUtil.getInputStream(file);
        BufferedOutputStream outputStream = FileUtil.getOutputStream(outFile);
        byte[] bytes = new byte[2048];
        int temp = -1;
        try {
            while ((temp = inputStream.read(bytes)) != -1) {
                bytes[0] -= 1;
                outputStream.write(bytes, 0, temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
        }
    }

    public static String encrypted(String s) {
        int i = Math.abs(s.hashCode() % 107);
        return (char) i + encrypted(s, i);
    }

    public static String decode(String s) {
        char c = s.charAt(0);
        return decode(s.substring(1), c);
    }

    public static String encrypted(String s, int num) {
        if (StrUtil.isEmpty(s)) {
            return s;
        }
        char c = s.charAt(0);
        num = num + c;
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            stringBuilder.append((char) (((int) aChar) + num));
        }
        return stringBuilder.toString();
    }

    public static String decode(String s, int num) {
        if (StrUtil.isEmpty(s)) {
            return s;
        }
        char c = s.charAt(0);
        num = (c - num) / 2 + num;
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            stringBuilder.append((char) (((int) aChar) - num));
        }
        return stringBuilder.toString();
    }
}
