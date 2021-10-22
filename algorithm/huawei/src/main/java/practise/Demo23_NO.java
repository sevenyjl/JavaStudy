package practise;

import java.math.BigDecimal;
import java.util.Scanner;

/* 根据二项式公式： (1 + x)^n = ∑(r=0,r∝n)*C(n,r)*x^r
                                = C(n,0)*x^0 + C(n,1)*x^1 + ... + C(n,n)*x^n
    令x=1, n= 2n，可得: (1 + 1)^2n = C(2n,0) + C(2n,1) + ... + C(2n,2n) = 2^2n   式1
    令x=-1, n= 2n，可得: (1 - 1)^2n = C(2n,0) - C(2n,1) + ... + C(2n,2n) = 0     式2
    式1 - 式2可得: C(2n,1) + C(2n,3) + ... + C(2n,2n-1) = 2^(2n - 1)
    令k1=C(2n,1), k2=C(2n,3), ..., kn=C(2n,2n-1)
    假设最大公约数为x, 则 k1+k2+k3+...+kn = x * (kk1 + kk2 + kk3 + ... + kkn) = 2^（2n-1）
    显然x只能是 2的幂次方
    C(2n, i) = (2n/i) * C(2n-1, i-1) 其中 i为奇数
    由于i为奇数，且C(2n-1,i-1)为整数，所以C(2n,i)中的2因子一定大于等于C(2n,1)中的2因子
    综上：
    1、最大公约数只能为2的幂次方
    2、C(2n,1)中的2因子最少
    可得，最大公约数为C(2n,1)的2因子的乘积
    C(2n,1)=2n,即求2n中的所有2因子的乘积，由于二进制的特性，如果2n=xxx10000(后面有几个0就代表有几个2因子)
    那么-2n的补码(符号位不变，原码所有位取反后加1)为xxx01111 + 1 = xxx10000(xxx与正2n的xxx位相反)
    得出2n的2因子个数为2n & (-2n)
    */
public class Demo23_NO {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // int n = scanner.nextInt();
        int n = 11;
        long temp = 0;
        for (int i = 1; i <= n; i++) {
            if (temp == 0) {
                temp = c(2 * n, 2 * i - 1);
            } else {
                temp = getGCD(temp, c(2 * n, 2 * i - 1));
            }
        }
        System.out.println(temp);
    }

    public static long getGCD(long a, long b) {
        if (a < 0 || b < 0) {
            return 0; // 数学上不考虑负数的约数
        }
        if (b == 0) {
            return a;
        }
        while (a % b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static long c(int n, int m) {
        BigDecimal divide = factBig(n).divide(factBig((n - m)).multiply(factBig(m)));
        return divide.longValue();
    }

    public static BigDecimal factBig(int n) {
        if (n == 1 || n == 0) {
            return BigDecimal.valueOf(1);
        } else {
            return BigDecimal.valueOf(n).multiply(factBig(n - 1));
        }
    }
}
