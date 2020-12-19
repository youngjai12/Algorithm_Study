package slidingWindow;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2473_threeLiquids {
	public static long min = Long.MAX_VALUE;
	public static int bestLeft, bestRight, mid = -1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCount = sc.nextInt(); 
		int[] arr = new int[numCount];
		for(int i=0;i<numCount;i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		for(int i=0;i<arr.length;i++) {
			solve(i, arr);
		}
		int[] answer = new int[3];
		answer[0]=arr[bestLeft];
		answer[1] = arr[mid];
		answer[2]  = arr[bestRight];
		Arrays.parallelSort(answer);
		System.out.println(answer[0]+" "+answer[1]+" "+answer[2]);
		
	}
	public static void solve(int givenIdx, int[] arr) {
		int leftIdx=0;
		int rightIdx=arr.length-1;
		while(leftIdx<rightIdx) {
			if(leftIdx == givenIdx) leftIdx++;
			if(rightIdx == givenIdx) rightIdx--;
			
			int tmpsum = arr[leftIdx]+arr[rightIdx];
			if(Math.abs(tmpsum+arr[givenIdx]) < min ) {
				min = Math.abs(tmpsum+arr[givenIdx]);
				bestRight = rightIdx;
				bestLeft = leftIdx;
				mid = givenIdx;
			}
			if(Math.abs(tmpsum+arr[givenIdx]) > 0) {
				leftIdx++;
			}else if(Math.abs(tmpsum+arr[givenIdx]) < 0) {
				rightIdx--;
			}else {
				break;
			}
		}
	}

}
