package sort;

import java.util.Arrays;

public class BiggestNum_programmers {
	private static class Number implements Comparable<Number>{
		int idx,fakeNum;
		public Number(int idx, int fakeNum) {
			this.idx = idx; 
			this.fakeNum = fakeNum;
		}
		public int compareTo(Number target) {
			if(fakeNum < target.fakeNum) return 1;
			else return -1;
		}
	}
	public static void main(String[] args) {
		int[] numbers = {3, 30, 34, 53, 9324,1034};
		int[] numbers2 = {3, 30, 34, 5, 9};
		int[] numbers3 = {76, 767};
		int[] numbers4 = {0,1,11,13,14};
		int[] numbers5 = {1000,1000,22,33};
		int[] numbers6 = {71,777,77,7,7};
		int[] numbers7 = {21,212};
		int[] numbers8 = {2,20,200};
		
		
		
		
		
		
		System.out.println(solution(numbers8));
	}
	public static int getFirst(int num) {
		return Integer.parseInt(Integer.toString(num).charAt(0)+"");
	}
	public static int makeFakeNum(int real, int first) {
		if(real<10) return first*1000+first*100+first*10+first;
		else if(real/10 < 10) return real*100+real;
		else if(real/100 <10) return real*10+first;
		else return real;
	}
	public static void print(Number[] r, int[] given) {
		for(int i=0;i<r.length;i++) {
			System.out.print(given[r[i].idx]+" ");
		}
		System.out.println();
	}
	public static String solution(int[] numbers) {
		Number[] arr = new Number[numbers.length];
		for(int i=0;i<numbers.length;i++) {
			int first = getFirst(numbers[i]);
			arr[i] = new Number(i, makeFakeNum(numbers[i], first));
		}
		Arrays.sort(arr);
		String acc = "";
		for(int i=0;i<arr.length;i++) {
			acc += Integer.toString(numbers[arr[i].idx]);
		}
		if(acc.charAt(0)=='0') return "0";
		else return acc;
	}

}
