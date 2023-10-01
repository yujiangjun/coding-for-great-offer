package class29;

public class Problem_0050_PowXN {

	/*
	计算10^75
	75=64+8+2+1
	二进制形式100101101
	ans=10^1

	 */
	public static int pow(int a, int n) {
		int ans = 1;
		int t = a;
		while (n != 0) {
			if ((n & 1) != 0) {
				ans *= t;
			}
			t *= t;
			n >>= 1;
		}
		return ans;
	}

	// x的n次方，n可能是负数
	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		// 求x^n,如果n是负数，则先求解ans=x^(-n),然后1/ans
		// 这里如果n是系统最小值，无法转成正数，因为溢出
		//系统最小值=-(系统最大值)-1=-(系统最大值+1)
		int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			// 这里求解pow的二进制表示的最后一位是不是1
			if ((pow & 1) != 0) {
				ans *= t;
			}
			pow >>= 1;
			t = t * t;
		}
		if (n == Integer.MIN_VALUE) {
			ans *= x;
		}
		return n < 0 ? (1D / ans) : ans;
	}

}
