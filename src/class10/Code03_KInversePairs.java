package class10;

// 测试链接 : https://leetcode.cn/problems/k-inverse-pairs-array/
public class Code03_KInversePairs {

	public static int kInversePairs(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		/*
		dp[i][j]=p表示1-i个数组自由组合，中共有j个逆序对的组合共有p个
		dp[5][3]表示1-5，5个数自由组合，有3个逆序对的组合
		那么我们不妨设前面1-4的组合是abcd.
		当5这个数加入到abcd中，共有下面这几种方法
		1. abcd5,那么满足dp[5][3]的话，abcd的逆序对需要是3，即dp[4][3]
		2.abc5d,那么满足dp[5][3]的话，由于5大于d,所以abcd需要满足的逆序对是2，即dp[4][2]
		3.ab5cd,那么满足dp[5][3]的话，由于5大于d,大于c,所以abcd需要满足的逆序对是1，即dp[4][1]
		4.a5bcd,那么满足dp[5][3]的话，由于5大于d,大于c,大于b,所以abcd需要满足的逆序对是0，即dp[4][0]
		5.如果是5abcd,那么就无法满足dp[5][3],因为abcd都小于5，超过了3.
		所以dp[5][3]=dp[4][3]+dp[4][2]+dp[4][1]+dp[4][0]
		那么dp[5][4]=dp[4][4]+dp[4][3]+dp[4][2]+dp[4][1]+dp[4][0]
		由此我们可以推广出dp[5][4]=dp[5][3]+dp[4][4]
		dp[i][j]=dp[i][j-1]+dp[i-1][j]

		此时我们考虑一种边界情况
		dp[5][5]
		1.abcd5 那么满足dp[5][5]的话，abcd的逆序对需要是5，即dp[4][5]
		2.abc5d,那么满足dp[5][5]的话，由于5大于d,所以abcd需要满足的逆序对是4，即dp[4][4]
		3.ab5cd,那么满足dp[5][5]的话，由于5大于d,大于c,所以abcd需要满足的逆序对是3，即dp[4][3]
		4.a5bcd,那么满足dp[5][5]的话，由于5大于d,大于c,大于b,所以abcd需要满足的逆序对是2，即dp[4][2]
		5.5abcd,那么满足dp[5][5]的话，由于5大于d,大于c,大于b,大于a,所以abcd需要满足的逆序对是1，即dp[4][1]
		只有这上面5中情况，dp[5][5]=dp[4][5]+dp[4][4]+dp[4][3]+dp[4][2]+dp[4][1]
		dp[5][6]
		1.abcd5 那么满足dp[5][6]的话，abcd的逆序对需要是6，即dp[4][6]
		2.abc5d,那么满足dp[5][6]的话，由于5大于d,所以abcd需要满足的逆序对是5，即dp[4][5]
		3.ab5cd,那么满足dp[5][6]的话，由于5大于d,大于c,所以abcd需要满足的逆序对是4，即dp[4][4]
		4.a5bcd,那么满足dp[5][6]的话，由于5大于d,大于c,大于b,所以abcd需要满足的逆序对是3，即dp[4][3]
		5.5abcd,那么满足dp[5][6]的话，由于5大于d,大于c,大于b,大于a,所以abcd需要满足的逆序对是2，即dp[4][2]
		只有这上面5中情况，dp[5][6]=dp[4][6]+dp[4][5]+dp[4][4]+dp[4][3]+dp[4][2]
		所以对于j>=i 的情况dp[i][j]=dp[i-1][j]+...+dp[i-1][j-i+1]
		dp[i][j]=dp[i][j-1]+dp[i-1][j]-dp[i-1][j-i+1]
		 */
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
				if (j >= i) {
					dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
				}
			}
		}
		return dp[n][k];
	}

	public static int kInversePairs2(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				if (j >= i) {
					dp[i][j] -= dp[i - 1][j - i];
				}
			}
		}
		return dp[n][k];
	}

}
