package practise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Demo11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        ArrayList<T> ts = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            ts.add(new T(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
            if (num == ts.size()) {
                break;
            }
        }
        ts.stream().sorted((c1, c2) -> {
            if (c1.gold != c2.gold) {
                return c2.gold - c1.gold;
            }
            if (c1.silver != c2.silver) {
                return c2.silver - c1.silver;
            }
            if (c1.bronze != c2.bronze) {
                return c2.bronze - c1.bronze;
            }
            return c1.name.compareTo(c2.name);
        }).forEach(System.out::println);
    }

    static class T {

        @Override
        public String toString() {
            return name;
        }

        private String name;
        private int gold;
        private int silver;
        private int bronze;

        public T(String name, int gold, int silver, int bronze) {
            this.name = name;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
    }
}
