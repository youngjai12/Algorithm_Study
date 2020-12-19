package stack;

import java.util.Scanner;
import java.util.Stack;


public class BiggestRectangle_BOJ1725 {
	private static class Data{
		int idx, height;
		public Data(int idx,  int height) {
			this.idx = idx;
			this.height = height;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count  = sc.nextInt(); 
		int[] info = new int[count];
		for(int i=0;i<count;i++) {
			info[i] = sc.nextInt();
		}
		System.out.println(solution(info));
	}
	
	public static long solution(int[] target) {
		Stack<Data> st = new Stack<>();
		st.push(new Data(0, target[0]));
		
		long max = -1;
		for(int i=1;i<target.length;i++) {
			if(!st.isEmpty() && target[i] < st.peek().height) {
				int tmpIdx=0;
				while(!st.isEmpty() && st.peek().height > target[i]) {
					Data top = st.pop();
					tmpIdx = top.idx;
					long v = (i-top.idx) * top.height;
					if(v > max) max = v;
				}
				st.push(new Data(tmpIdx, target[i]));
			}else {
				st.push(new Data(i, target[i]));
			}
		}
		while(!st.isEmpty()) {
			Data tmp = st.pop();
			long value = (target.length - tmp.idx)*tmp.height;
			if(value > max) max = value;
		}
		return max;
	}
}
