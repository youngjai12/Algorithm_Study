package hackerRank;

import java.util.Scanner;

public class GridSearch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int caseCount = sc.nextInt();
		for(int q=0;q<caseCount;q++) {
			int rowCount = sc.nextInt();
			int colCount = sc.nextInt();
			int[][] map = new int[rowCount][colCount];
			for(int i=0;i<rowCount;i++) {
				String tmp = sc.next();
				for(int j=0;j<colCount;j++) {
					map[i][j] = tmp.charAt(j);
				}
			}
			int rowC = sc.nextInt();
			int colC = sc.nextInt();
			int[][] pattern = new int[rowC][colC];
			for(int i=0;i<rowC;i++) {
				String tmp = sc.next();
				for(int j=0;j<colC;j++) {
					pattern[i][j]=tmp.charAt(j);
				}
			}
			String answer = "NO";
			loop1:for(int i=0;i<=rowCount-rowC;i++) {
				for(int j=0;j<=colCount-colC;j++) {
					if(find(i,j,map,pattern)) {
						answer = "YES";
						break;
					}
				}
			}
			System.out.println(answer);
		}
	}
	public static void printMap(int[][] m) {
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[0].length;j++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	public static String find2(String[] G, String[] P) {
		String answer = "NO";
		loop1:for(int i=0;i<G.length-P.length;i++) {
			for(int j=0;j<G[0].length()-P[0].length();j++) {
				if(match(i,j,G,P)) {
					answer = "YES";
					break;
				}
			}
		}
		return answer;
	}
	public static boolean match(int row, int col, String[]G, String[] P) {
		boolean answer = true;
		loop1: for(int i=0;i<P.length;i++) {
			for(int j=0;j<P[0].length();j++) {
				if(G[row+i].charAt(col+j) == P[i].charAt(j)) {
					answer = false;
					break loop1;
				}
			}
		}
		return answer;
	}
	public static boolean find(int row, int col, int[][] map, int[][] pattern) {
		boolean answer=true;
		loop1:for(int i=0;i<pattern.length;i++) {
			for(int j=0;j<pattern[0].length;j++) {
				if(map[row+i][col+j]!=pattern[i][j]) {
					answer=false;
					break loop1;
				}
			}
		}
		return answer;
	}

}
