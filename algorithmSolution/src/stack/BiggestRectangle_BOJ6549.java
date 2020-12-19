package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class BiggestRectangle_BOJ6549 {
	private static class Data{
		int idx, height;
		public Data(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}
	/**
	 * 
	 7 2 1 4 5 1 3 3
	4 1000 1000 1000 1000
	5 3 2 3 4 5
	10 2 3 5 6 5 4 5 6 2 8
	10 2 4 5 6 5 4 5 6 2 8
	10 2 3 9 10 9 4 5 6 2 8
	10 2 3 10 9 10 4 5 6 2 8
	10 2 3 11 12 10 4 5 6 2 8
	2 0 0
	3 1 0 1
	4 3 2 0 1
	3 2 6 5 	
	4 2 4 2 4
	5 5 4 2 1 0
	0
	 */
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n==0) break;
			int[] info = new int[n];
			for(int i=0;i<n;i++) {
				info[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(solution(info)).append("\n");
		}
		System.out.println(sb);
		br.close();
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
