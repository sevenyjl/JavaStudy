package practise;

import java.util.Locale;
import java.util.Scanner;

public class Demo10 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.nextLine().toLowerCase(Locale.ROOT);
            char[] chars = next.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                if (aChar == 'a') {
                    aChar = 'A';
                } else if (aChar == 'e') {
                    aChar = 'E';
                } else if (aChar == 'i') {
                    aChar = 'I';
                } else if (aChar == 'o') {
                    aChar = 'O';
                } else if (aChar == 'u') {
                    aChar = 'U';
                }
                stringBuilder.append(aChar);
            }
            System.out.println(stringBuilder);
        }
    }
}
