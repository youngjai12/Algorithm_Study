package hackerRank;

import java.util.Scanner;

public class Common_child {

	public static void main(String[] args) {
		// SHINCHAN  &  NOHARAAA
	   //   NHA 로 3개..
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		String str2 = sc.next();
		int[][] map = new int[str1.length()+1][str2.length()+1];
		boolean[] visit = new boolean[str1.length()+1];
		for(int i=1;i<=str1.length();i++) {
			boolean first=false;
			for(int j=1;j<=str2.length();j++) {
				int tmp = Math.max(map[i-1][j], map[i][j-1]);
				if(str2.charAt(i-1) != str1.charAt(j-1)) {
					map[i][j] = tmp;
				}else {
					
					map[i][j]= map[i-1][j-1]+1;
				}
			}
		}
		printMap(map);
		System.out.println(map[str2.length()][str1.length()]);
	}
	public static void printMap(int[][] t ) {
		for(int i=0;i<t.length;i++) {
			for(int j=0;j<t[0].length;j++) {
				System.out.print(t[i][j]+" ");
			}
			System.out.println();
		}
	}

}
