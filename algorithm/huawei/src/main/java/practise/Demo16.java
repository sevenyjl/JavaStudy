package practise;

import java.math.BigDecimal;
import java.util.Scanner;

public class Demo16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= count; i++) {
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            System.out.println("Case " + i + ":");
            BigDecimal b1 = new BigDecimal(split[0]);
            BigDecimal b2 = new BigDecimal(split[1]);
            System.out.printf("%s - %s = %s\n\n", b1, b2, b1.subtract(b2));
        }
        scanner.close();
    }
}
