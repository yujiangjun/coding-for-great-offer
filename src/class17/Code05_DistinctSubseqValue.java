package class17;

import java.util.HashMap;

// 本题测试链接 : https://leetcode.cn/problems/distinct-subsequences-ii/
public class Code05_DistinctSubseqValue {

	public static int distinctSubseqII(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		long m = 1000000007;
		char[] str = s.toCharArray();
		long[] count = new long[26];
		long all = 1; // 算空集
		for (char x : str) {
			long add = (all - count[x - 'a'] + m) % m;
			all = (all + add) % m;
			count[x - 'a'] = (count[x - 'a'] + add) % m;
		}
		all = (all - 1 + m) % m;
		return (int) all;
	}

	/*
	这个方法包含了空集
	 */
	public static int zuo(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int m = 1000000007;
		char[] str = s.toCharArray();
		// key,value表示当前来到key,上一次来到key时的以key结尾的集合数量
		HashMap<Character, Integer> map = new HashMap<>();
		int all = 1; // 一个字符也没遍历的时候，有空集
		/*
		新增的集合=前面的所有集合里都加入x,
		all=前面的所有集合+新增的集合
		ex:121
		来到1：all=[],[1]
		来到2 add=[2],[1,2] all=[],[1],[2],[1,2]
		来到1 add=[1],[1,1],[2,1],[1,2,1] all=[],[1],[2],[1,2],[1,1],[2,1],[1,2,1]
		来到第二个1时all=add+前面一个all-来到第一个1时的非空集合的数量。
		 */
		for (char x : str) {
			int newAdd = all;
//			int curAll = all + newAdd - (map.containsKey(x) ? map.get(x) : 0);
			int curAll = all;
			curAll = (curAll + newAdd) % m;
			curAll = (curAll - (map.containsKey(x) ? map.get(x) : 0) + m) % m;
			all = curAll;
			map.put(x, newAdd);
		}
		/*
		原问题的答案要求是不要空集的数据
		所以(all-1+m)%m
		 */
		return all;
	}

	public static void main(String[] args) {
		String s = "bccaccbaabbc";
		System.out.println(distinctSubseqII(s) + 1);
		System.out.println(zuo(s));
	}

}
