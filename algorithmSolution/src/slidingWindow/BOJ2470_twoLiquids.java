package slidingWindow;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2470_twoLiquids {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCount = sc.nextInt();
		int[] arr = new int[numCount];
		for(int i=0;i<numCount;i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		int leftidx = 0;
		int rightidx = numCount-1;
		long min = 2000000000;
		int[] bestSet = new int[2];
		while(leftidx<=rightidx) {
			long sum = arr[leftidx]+arr[rightidx];
			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				bestSet[0] = arr[leftidx];
				bestSet[1] = arr[rightidx];
			}
			if(sum>0) rightidx--;
			if(sum<0) leftidx++;
		}
		System.out.println(bestSet[0]+" "+bestSet[1]);
	}

}
