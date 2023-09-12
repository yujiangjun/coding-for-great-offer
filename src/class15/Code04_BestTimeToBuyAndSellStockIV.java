package class15;

//leetcode 188
public class Code04_BestTimeToBuyAndSellStockIV {

	public static int maxProfit(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		/*
		 dp[i][j] =p 表示0..i位置最多交易j次的最大收益
		 dp[6][3]表示0..6范围内最多交易3次的最大收益是多少
		 1. 6位置不参与计算 则dp[6][3]=dp[5][3]
		 2. 6位置参与计算 那么在6位置卖出 有如下几种可能
		 a. 6位置买入，6位置卖出 dp[6][3]=dp[6][2]+prices[6]-prices[6]
		 b. 5位置买入，6位置卖出 dp[6][3]=dp[5][2]+prices[6]-prices[5]
		 c. 4位置买入，6位置卖出 dp[6][3]=dp[4][2]+prices[6]-prices[4]
		 d. 3位置买入，6位置卖出 dp[6][3]=dp[3][2]+prices[6]-prices[3]
		 e. 2位置买入，6位置卖出 dp[6][3]=dp[2][2]+prices[6]-prices[2]
		 f. 1位置买入，6位置卖出 dp[6][3]=dp[1][2]+prices[6]-prices[1]
		 g. 0位置买入，6位置卖出 dp[6][3]=dp[0][2]+prices[6]-prices[0]
		 dp[5][3]
		 1. 5位置不参与计算 则dp[5][3]=dp[4][3]
		 2. 5位置参与计算 那么在5位置卖出 有如下几种可能
		 a. 5位置买入，5位置卖出 dp[5][3]=dp[5][2]+prices[5]-prices[5]
		 b. 4位置买入，6位置卖出 dp[5][3]=dp[4][2]+prices[5]-prices[4]
		 c. 3位置买入，6位置卖出 dp[5][3]=dp[3][2]+prices[5]-prices[3]
		 d. 2位置买入，6位置卖出 dp[5][3]=dp[2][2]+prices[5]-prices[2]
		 e. 1位置买入，6位置卖出 dp[5][3]=dp[1][2]+prices[5]-prices[1]
		 f. 0位置买入，6位置卖出 dp[5][3]=dp[0][2]+prices[5]-prices[0]

		 best[6][3]=Max{
			 (dp[6][2]-prices[6]),
			 (dp[5][2]-prices[5]),
			 (dp[4][2]-prices[4]),
			 (dp[3][2]-prices[3]),
			 (dp[2][2]-prices[2]),
			 (dp[1][2]-prices[1]),
			 (dp[0][2]-prices[0]),
		 }=Max{
			 (dp[6][2]-prices[6]),
			 (dp[5][2]-prices[5]),
			 (dp[4][2]-prices[4]),
			 (dp[3][2]-prices[3]),
			 (dp[2][2]-prices[2]),
			 (dp[1][2]-prices[1])
			 (dp[0][2]-prices[0])
		 }
		 dp[6][3]=Max{best+prices[6],dp[5][3]}

		 best[5][3]=Max{
			 (dp[5][2]-prices[5]),
			 (dp[4][2]-prices[4]),
			 (dp[3][2]-prices[3]),
			 (dp[2][2]-prices[2]),
			 (dp[1][2]-prices[1]),
			 (dp[0][2]-prices[0])
		 }=Max{
		 	 (dp[5][2]-prices[5]),
		 	 (dp[4][2]-prices[4]),
			 (dp[3][2]-prices[3]),
			 (dp[2][2]-prices[2]),
			 (dp[1][2]-prices[1]),
			 (dp[0][2]-prices[0])
		 }
		 所以best[6][3]=Max{best[5][3],dp[5][2]-prices[6]}

		 baseCase: dp[0..k][0]=0
		 dp[0][0..n-1]=0
		 */
		int[][] dp = new int[K + 1][N];
		int ans = 0;
		for (int tran = 1; tran <= K; tran++) {
			/*
			 dp[tran][0]
			 在0..0范围上进行最多tran次交易
			 1 0位置不参与即dp[tran][0]
			 2.0位置参与 只有一种情况，即0位置买入，0位置卖出
			 所以best=dp[tran][0]-prices[0]
			 */
			int pre = dp[tran][0];
			int best = pre - prices[0];
			for (int index = 1; index < N; index++) {
				pre = dp[tran - 1][index];
				dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
				best = Math.max(best, pre - prices[index]);
				ans = Math.max(dp[tran][index], ans);
			}
		}
		return ans;
	}

	public static int allTrans(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(prices[i] - prices[i - 1], 0);
		}
		return ans;
	}

	// 课上写的版本，对了
	public static int maxProfit2(int K, int[] arr) {
		if (arr == null || arr.length == 0 || K < 1) {
			return 0;
		}
		int N = arr.length;
		if (K >= N / 2) {
			return allTrans(arr);
		}
		int[][] dp = new int[N][K + 1];
		// dp[...][0] = 0
		// dp[0][...] = arr[0.0] 0
		for (int j = 1; j <= K; j++) {
			// dp[1][j]
			int p1 = dp[0][j];
			int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]);
			dp[1][j] = Math.max(p1, best + arr[1]);
			// dp[1][j] 准备好一些枚举，接下来准备好的枚举
			for (int i = 2; i < N; i++) {
				p1 = dp[i - 1][j];
				int newP = dp[i][j - 1] - arr[i];
				best = Math.max(newP, best);
				dp[i][j] = Math.max(p1, best + arr[i]);
			}
		}
		return dp[N - 1][K];
	}

}
