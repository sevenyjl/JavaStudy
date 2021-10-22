package practise;

import java.math.BigDecimal;
import java.util.Scanner;

public class Demo17 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            BigDecimal b1 = new BigDecimal(split[0]);
            BigDecimal b2 = new BigDecimal(split[1]);
            System.out.println(b1.multiply(b2));
        }
        scanner.close();
    }
}
