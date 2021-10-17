package map;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * hashMap 演示内存泄露，以及key为对象时，不建议随意修改key对象的成员值
 * JVM参数：-Xms3m -Xmx3m -XX:+HeapDumpOnOutOfMemoryError
 */
public class Demo02MemoryLeak {
    /**
     * 不覆盖equals&hashCode方法导致的内存泄露
     */
    static class Key1HaveLeak {
        private String key;

        private Key1HaveLeak(String key) {
            this.key = key;
        }
    }

    /**
     * @Override equals&hashCode方法可能导致的内存泄露
     */
    static class Key1MaybeLeak {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key1MaybeLeak that = (Key1MaybeLeak) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        private Key1MaybeLeak(String key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
//        main01();
//        main02();
        main03();
    }

    /**
     * 内存泄露
     */
    public static void main01() {
        HashMap<Key1HaveLeak, String> map = new HashMap<>();
        int count = 0;
        while (true) {
            map.put(new Key1HaveLeak("dummyKey"), "value");
            count++;
            if (count % 1000 == 0) {
                System.out.println("map size: " + map.size());
                System.out.println("Free memory after count " + count + " is " + getFreeMemory() + "MB");
                //休眠100  MICROSECONDS
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 内存没有泄露
     */
    public static void main02() {
        HashMap<Key1MaybeLeak, String> map = new HashMap<>();
        int count = 0;
        while (true) {
            map.put(new Key1MaybeLeak("dummyKey"), "value");
            count++;
            if (count % 1000 == 0) {
                System.out.println("map size: " + map.size());
                System.out.println("Free memory after count " + count + " is " + getFreeMemory() + "MB");
                //休眠100  MICROSECONDS
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 内存有泄露，导致原因修改了成员变量的值，而成员变量的值有影响了Key1MaybeLeak，所以不建议map的key是自定义对象时，修改key对象的成员变量
     */
    public static void main03() {
        HashMap<Key1MaybeLeak, String> map = new HashMap<>();
        int count = 0;
        while (true) {
            Key1MaybeLeak dummyKey = new Key1MaybeLeak("dummyKey");
            map.put(dummyKey, "value");
            dummyKey.setKey("dummyKey" + count);
            count++;
            if (count % 1000 == 0) {
                System.out.println("map size: " + map.size());
                System.out.println("Free memory after count " + count + " is " + getFreeMemory() + "MB");
                //休眠100  MICROSECONDS
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }
}
