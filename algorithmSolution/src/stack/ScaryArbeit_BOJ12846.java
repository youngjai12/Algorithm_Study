package stack;

import java.util.Scanner;
import java.util.Stack;

public class ScaryArbeit_BOJ12846 {
	private static class Data{
		int idx, height;
		public Data(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		int[] info = new int[count];
		for(int i=0;i<count;i++) {
			info[i] = sc.nextInt();
		}
		System.out.println(solution(info));
	}
	public static int solution(int[] target) {
		Stack<Data> st = new Stack<>();
		st.push(new Data(0, target[0]));
		int[] heightInfo = new int[target.length];
		
		for(int i=1;i<target.length;i++) {
			if(!st.isEmpty() && target[i] < st.peek().height) {
				while(!st.isEmpty() && st.peek().height > target[i]) {
					
					Data top = st.pop();
					heightInfo[top.idx] = (i-top.idx) * top.height;
				}
			}	
			st.push(new Data(i, target[i]));
		}
		int max =-1;
		for(int i=0;i<target.length;i++) {
			if(heightInfo[i]==0) heightInfo[i] = (target.length-i)*target[i];
			if(max < heightInfo[i]) max=heightInfo[i];
		}
		return max;
	}
}
