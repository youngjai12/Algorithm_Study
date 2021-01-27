package hackerRank;

import java.util.Scanner;

public class BirthDayCake_Candles {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int arrCount = sc.nextInt();
		int[] given = new int[arrCount];
		for(int i=0;i<arrCount;i++) {
			given[i] = sc.nextInt();
		}
		System.out.println(solution(given));
	}
	public static int solution(int[] ar) {
		int maxCount=0;
		int max = -1;
		for(int i=0;i<ar.length;i++) {
			if(max < ar[i]) {
				max = ar[i];
				maxCount=1;
			}else if( max == ar[i]) {
				maxCount++;
			}
		}
		return maxCount;
	}

}
