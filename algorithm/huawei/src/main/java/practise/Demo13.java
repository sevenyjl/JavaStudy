package practise;

import java.util.Scanner;

public class Demo13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(" ");
        StringBuilder sb1 = new StringBuilder("+");
        StringBuilder sb1_1 = new StringBuilder("|");
        for (int i = 0; i < Integer.parseInt(split[1]); i++) {
            sb1.append("---+");
            sb1_1.append("   |");
        }
        StringBuilder sp0 = new StringBuilder().append(sb1).append("\n");
        for (int i = 0; i < Integer.parseInt(split[0]); i++) {
            sp0.append(sb1_1).append("\n").append(sb1).append("\n");
        }
        System.out.println(sp0);
        scanner.close();
    }
}
