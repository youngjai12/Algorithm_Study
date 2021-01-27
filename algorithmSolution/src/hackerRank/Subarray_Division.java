package hackerRank;

import java.util.Scanner;

public class Subarray_Division {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt(); 
		int[] array = new int[count];
		for(int i=0;i<count;i++) {
			array[i] = sc.nextInt();
		}
		int day = sc.nextInt();
		int month = sc.nextInt();
		
		System.out.println(solve(array, month, day));
	}
	public static int solve(int[] ar, int month, int day) {
		int answer=0;
		for(int i=0;i<=ar.length-month;i++) {
			int sum=0;
			for(int j=i;j<i+month;j++) {
				sum+=ar[j];
			}
			if(sum == day) answer++;
		}
		return answer;
	}

}
