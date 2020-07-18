package DFS;

import java.util.Scanner;

public class BOJ10451_cycle {
	public static int count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int caseCount = sc.nextInt(); 
		for(int c=0;c<caseCount;c++) {
			int num = sc.nextInt();
			int[] info = new int[num+1];
			for(int j=1;j<=num;j++) {
				info[j] = sc.nextInt();
			}
			solve(info);
			count=0;
		}
		
	}
	public static void solve(int[] info) {
		int[] visit = new int[info.length];
		for(int i=1;i<info.length;i++) {
			if(visit[i]==0) {
				connect(i, info, visit);
			}
		}
		System.out.println(count);
	}
	public static void connect(int start, int[] info, int[] visit) {
		if(visit[start]==0) {
			visit[start]++;
			connect(info[start], info, visit);
		}else {
			count++;
		}
	}

}
