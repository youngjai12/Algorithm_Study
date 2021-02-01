package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StoneCross_programmers {

	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		solution3(stones, 3);
	}
	public static void solution2(int[] stones, int k) {
		int min = 200000001;
		
		for(int i=0;i<=stones.length-k;i++) {
			int tmpMax = -1;
			for(int j=i;j<i+k;j++) {
				if(tmpMax  < stones[j]) tmpMax = stones[j];
			}
			if(tmpMax < min) min = tmpMax;
		}
		System.out.println(min);
	}
	public static void solution3(int[] stones, int k) {
		int max = -1;
		int min = 200000001;
		for(int i=0;i<stones.length;i++) {
			if(max < stones[i]) max = stones[i];
			if(min > stones[i]) min = stones[i];
		}
		int answer = 0;
		while(true) {
			
			int mid = (max + min)/2;
			if(max <=min) {
				answer=mid;
				break;
			}
			if(canPass(stones, k, mid)) {
				min = mid+1;
			}else {
				max = mid-1;
			}
		}
		System.out.println(answer);
		
	}
	public static boolean canPass(int[] stones, int k, int h) {
		int zeroCount=0;
		boolean answer=true;
		for(int i=0;i<stones.length;i++) {
			if(stones[i]-h > 0) zeroCount=0;
			else zeroCount++;
			if(zeroCount==k) {
				answer=false;
				break;
			}
		}
		return answer;
	}

}
