package class15;

//leetcode 122
public class Code02_BestTimeToBuyAndSellStockII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		/*
		 在一个时刻手中最多有一支股票
		 在prices股票中，可以看成一个波峰波谷交替的折线图
		 所以找到每一个波峰和波谷，用波峰减去波谷的和就是答案
		 在这里，我们可以转变一下思路，在i+1-i>=0 表示上升趋势 <0表示下降趋势
		 在这里我们只需求解上升趋势的总和
		 我们从1开始，i-(i-1)=p,max(p,0)累加和就是答案
		 如果是上升趋势 p>=0,下降趋势是p<0
		 所以这里求解上升趋势的总和=sum(p)
		 */


		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
		}
		return ans;
	}
	
}
