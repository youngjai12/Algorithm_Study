package DFS;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ2331_repeatingNumber {
	public static int start ,num ;
	public static ArrayList<Integer> answer ;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		start = sc.nextInt();
		num = sc.nextInt(); 
		answer = new ArrayList<>();
		// 9의 5제곱은 59049 -> 4자리까지 가능하니깐 최대 24만 
		int[] check = new int[240000];
		getMultiple(start, check);
	}
	public static int getRepeatedCount(int lastNum, int[] check) {
		int temp = lastNum;
		int count= 1;
		while(true) {
			temp = check[temp];
			count += 1;
			if(temp == lastNum) {
				break;
			}
		}
		return count;
	}
	public static int getNextNum(int target) {
		int temp = target;
		int sum =0;
		while(temp > 0) {
			sum += (int) Math.pow((temp % 10) , num);
			temp = temp / 10; 
		}
		return sum;
	}
	public static void getMultiple(int target, int[] check) {
		if(check[target]==0) {
			int sum = getNextNum(target);
			check[target] = sum;
			answer.add(sum);
			getMultiple(sum, check);
		}else {
			System.out.println((answer.size() - getRepeatedCount(getNextNum(target), check)+1));
		}
	}

}
