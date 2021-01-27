package general;

import java.util.Scanner;

public class BOJ1912_SequentialSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCount = sc.nextInt();
		int[] info = new int[numCount];
		int sum=0;
		int max = Integer.MIN_VALUE;
		for(int i=0;i<numCount;i++) {
			int curValue = sc.nextInt();
			sum+=curValue;
			if(sum > max) {
				max = sum;
			}
			if(sum<0) sum=0;
			
			
		}
		System.out.println(max);
	}

}
