package DFS;

import java.util.Scanner;

public class BOJ9905_dp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCount = sc.nextInt();
		int[] dp = new int[11];
		dp[1] =1; dp[2] =2; dp[3] =4;
		
		for(int i=0;i<numCount;i++) {
			System.out.println(getAnswer(sc.nextInt(), dp));
		}
	}
	public static int getAnswer(int idx, int[] dp) {
		if(dp[idx] ==0) {
			dp[idx]= getAnswer(idx-1, dp) + getAnswer(idx-2, dp)+(getAnswer(idx-3, dp));
		}
		return dp[idx];
	}

}
