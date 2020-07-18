package unionFind;

import java.util.Scanner;

public class BOJ1976_goTrip {
	public static boolean[][] adjMap;
	public static int cityCount;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cityCount = sc.nextInt(); 
		int plannedCityCount = sc.nextInt(); 
		int[] plan = new int[plannedCityCount];
		
		adjMap = new boolean[cityCount+1][cityCount+1];
		for(int i=1;i<=cityCount;i++) {
			for(int j=1;j<=cityCount;j++) {
				int temp = sc.nextInt(); 
				if(temp == 1) adjMap[i][j] = true;
				else adjMap[i][j] = false; 
			}
		}
		for(int i=0;i<plannedCityCount;i++) {
			plan[i] = sc.nextInt(); 
		}
		int flag = 0;
		for(int i=1; i<plannedCityCount;i++) {
			if(isPossible(0, plan[i-1], plan[i])) {
				flag++;
			}
		}
		if(flag == plannedCityCount -1) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
	}
	
	public static boolean isPossible(int prev, int start, int dest) {
		boolean answer = false;
		if(!adjMap[start][dest]) {
			for(int i=1;i<=cityCount;i++) {
				if(i!=prev && adjMap[start][i]) {
					answer = isPossible(start, i, dest);
					if(answer) break;
				}
			}
			return answer; 
		}else {
			return true;
		}
	}

}
