package demo02.future;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 比价系统
 * 案例：现在有6个商店，tb、pdd、js、dangdang、youdao、yamaxun。每个商店的java书籍销售价格不一样。
 * 1. 爬取每个商品的数据，并返回数据集合：tb,192.0;pdd,120.0
 * 2.
 * auth: seven
 * date: 2021/8/10 21:31
 */
public class BookPrice {
    public static void main(String[] args) {
        List<String> storeName = Arrays.asList("tb", "pdd", "js", "dangdang", "youdao", "yamaxun");
        final String bookName = "java";
        long startTime = System.currentTimeMillis();
        ontStepByStep(storeName, bookName).forEach(System.out::println);
        System.out.printf("耗时：%s毫秒\n", System.currentTimeMillis() - startTime);
        System.out.println("-------------------------------\n");
        long startTime2 = System.currentTimeMillis();
        stepAsync(storeName, bookName).forEach(System.out::println);
        System.out.printf("耗时：%s毫秒\n", System.currentTimeMillis() - startTime2);
    }

    public static List<String> ontStepByStep(List<String> storeName, String bookName) {
        //一个个获取
        return storeName.stream().map(s -> Thread.currentThread().getName() + "\t" + s + "买" + getPrice(s, bookName)).collect(Collectors.toList());
    }

    /**
     * 同时进行
     *
     * @param storeName
     * @param bookName
     * @return
     */
    public static List<String> stepAsync(List<String> storeName, String bookName) {
        return storeName.stream().map(s -> CompletableFuture.supplyAsync(() -> Thread.currentThread().getName() + "\t" + s + "买" + getPrice(s, bookName)))
                .collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 模拟爬虫方法
     */
    public static int getPrice(String storeName, String bookName) {
        //休眠1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RandomUtil.randomString(storeName, 1).charAt(0) + RandomUtil.randomString(bookName, 1).charAt(0) / 10;
    }
}

