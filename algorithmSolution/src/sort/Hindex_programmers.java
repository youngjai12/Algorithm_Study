package sort;

import java.util.Arrays;

public class Hindex_programmers {

	public static void main(String[] args) {
		int[] c1 = {3,0,6,1,5};
		int[] c2 = {2,2,2,2,2};
		int[] c3 = {2,4,4,10,10,10,10,10,10};
		int[] c4 = {2,2,3,4,4,5,7};
		int[] c5 = {2,2,7,7,8,8,9};
		int[] c6 = {1,1,0,0};
		int[] c7 = {1,0};
		int[] c8 = {2,45,34};
		int[] c9 = {22,42};
		
		
		
		
		
		System.out.println(solution(c9));
		
	}
	public static void printArr(int[] a) {
		for(int i=0;i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	public static int solution(int[] citations) {
		Arrays.sort(citations);
		int totlen = citations.length;
		int[] counts = new int[totlen];
		counts[0] = totlen;
		int consecutive=1;
		for(int i=1;i<totlen;i++) {
			if(citations[i] == citations[i-1]) {
				consecutive++;
				counts[i] = counts[i-1];
			}else {
				counts[i] = counts[i-1]-consecutive;
				consecutive=1;
			}
		}
		int max=-1;
		
		if(citations[totlen-1] == 1 || totlen==1) {
			max=1;
		}else if(citations[0] > totlen) {
			max=totlen;
		}else {
		for(int idx=0;idx<totlen-1;idx++) {
			if(citations[idx] == citations[idx+1]) {
				if(citations[idx]<=counts[idx] && max<citations[idx])max=citations[idx];
			}else {
				for(int j=citations[idx];j<citations[idx+1];j++) {
					if(j == citations[idx]) {
						if(j<=counts[idx] && max < j) max=j;
					}else {
						if(j<=counts[idx+1] && max < j) max=j;
					}
				}
			}
		}
		}
		return max;
	}
	
}
