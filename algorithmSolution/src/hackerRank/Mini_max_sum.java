package hackerRank;

import java.util.Scanner;

public class Mini_max_sum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long[] arr = new long[5];
		long tot_sum = 0;
		for(int i=0;i<5;i++) {
			arr[i] = sc.nextLong();
			tot_sum+=arr[i];
		}
		long min = Integer.MAX_VALUE;
		long max = -1;
		for(int i=0;i<5;i++) {
			long value = tot_sum -arr[i];
			if(value>max) max = value;
			if(value < min ) min = value;
		}
		System.out.println(min+"  "+max);
	}

}
