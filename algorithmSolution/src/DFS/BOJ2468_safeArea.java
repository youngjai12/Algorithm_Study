package DFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ2468_safeArea {
	public static int n;
	public static int[] dr = {-1,0,1,0};
	public static int[] dc = {0,1,0,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[][] givenMap = new int[n][n];
		boolean[] v = new boolean[100];
		ArrayList<Integer> numberList = new ArrayList<>();
		numberList.add(1);
		numberList.add(100);
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				givenMap[i][j] = sc.nextInt();
				if(!v[givenMap[i][j]]) {
					numberList.add(givenMap[i][j]);
					v[givenMap[i][j]]=true;
				}
			}
		}
		Collections.sort(numberList);
		System.out.println(sink(numberList, givenMap));
	}
	
	public static void dfs(int row, int col, boolean[][] sink, boolean[][] visit) {
		for(int i=0;i<4;i++) {
			int nextrow = row+dr[i];
			int nextcol = col+dc[i];
			if(nextrow>=0 && nextcol >=0 && nextrow<n && nextcol<n && !visit[nextrow][nextcol] && !sink[nextrow][nextcol]) {
				visit[nextrow][nextcol]=true;
				dfs(nextrow, nextcol, sink, visit);
				//visit[nextrow][nextcol]=false;
			}
		}
	}
	
	public static int getComponent(boolean[][] sink) {
		int answer=0;
		boolean[][] visit = new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visit[i][j] && !sink[i][j]) {
					//System.out.println("start: ["+i+","+j+"]");
					visit[i][j]=true;
					dfs(i,j, sink, visit);
					answer++;
				}
			}
		}
		return answer;
	}
	public static int sink(ArrayList<Integer> heightList, int[][] map) {
		boolean[][] sinkMap = new boolean[n][n];
		int max=1;
		for(int hidx=0; hidx<heightList.size();hidx++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j] <= heightList.get(hidx)) sinkMap[i][j]=true;
				}
			}
			int compCount = getComponent(sinkMap);
			System.out.println("===== height: "+heightList.get(hidx)+" => "+compCount);
			if(compCount>max) max=compCount;
		}
		return max;
	}

}
