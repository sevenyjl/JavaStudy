package threadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Demo02ThreadLocal {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Date parse(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    public static Date parse2(String date) throws ParseException {
        return simpleDateFormatThreadLocal.get().parse(date);
    }

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
    }

    private static void success() {
        for (int i = 0; i < 3; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    System.out.println(Demo02ThreadLocal.parse2("2021-11-11 11:11:11"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }, "thread " + i);
            threadTemp.start();
        }
    }

    private static void error() {
        for (int i = 0; i < 3; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    System.out.println(Demo02ThreadLocal.parse("2021-11-11 11:11:11"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }, "thread " + i);
            threadTemp.start();
        }
    }


}
