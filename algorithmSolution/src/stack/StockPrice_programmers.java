package stack;

import java.util.Stack;

public class StockPrice_programmers {
	private static class Element{
		int num,idx;
		public Element(int num, int idx) {
			this.num = num ;
			this.idx = idx;
		}
	}
	public static void main(String[] args) {
		int[] prices = {1,2,3,4,5,3,4,5,6,5,7,8,9,3,4};
		int[] prices2 = {5,5,4,3,3,2,1,2,3};
		int[] prices3 = {5,4,3,6,3,4,1,2};
		int[] prices4 = {1,2,3,2,3};
		int[] answer = Solution3(prices4);
		printArray(answer);
	}
	public static void printArray(int[] t) {
		for(int i=0;i<t.length;i++) {
			System.out.print(t[i] +" ");
		}
	}
	public static int[] Solution3(int[] prices) {
		Stack<Element> st = new Stack<>();
		int[] answer = new int[prices.length];
		st.push(new Element(prices[0], 0));
		for(int curIdx=1;curIdx<prices.length;curIdx++) {
			int cur = prices[curIdx];
			while(!st.isEmpty() && st.peek().num > cur) {
				Element top = st.pop();
				answer[top.idx] = curIdx - top.idx;
			}
			st.push(new Element(cur, curIdx));	
		}
		while(!st.isEmpty()) {
			Element top = st.pop();
			answer[top.idx] = prices.length-1 - top.idx;
		}
		return answer;
	}
	public static int[] Solution(int[] prices) {
		int[] answer = new int[prices.length];
		for(int i=0;i<prices.length-1;i++) {
			for(int j=i+1;j<prices.length;j++) {
				if(prices[i] > prices[j] || j == prices.length-1) {
					answer[i] = j-i;
					break;
				}
			}
		}
		answer[prices.length-1]=0;
		return answer;
	}
	

}
