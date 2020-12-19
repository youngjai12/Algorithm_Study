package DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2573_iceBerg {
	private static class Point{
		int r, c, height;
		Point(int r, int c , int height){
			this.r = r;
			this.c =c;
			this.height=height;
		}
	}
	public static int[] dr = {-1,0,1,0};
	public static int[] dc = {0,1,0,-1};
	public static int[][] givenmap;
	public static int givenRow, givenCol;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		givenRow = sc.nextInt();
		givenCol = sc.nextInt(); 
		givenmap = new int[givenRow][givenCol];
		ArrayList<Point> q = new ArrayList<>();
		for(int i=0;i<givenRow;i++) {
			for(int j=0;j<givenCol;j++) {
				givenmap[i][j] = sc.nextInt();
				if(givenmap[i][j]!=0) q.add(new Point(i,j,givenmap[i][j]));
			}
		}
		int year=0;
		while(true) {
			year++;
			getNextMap(q);
			int flag = isDivided();
			if(flag<0) {
				year=0;
				break;
			}
			else if(flag==1) break;
		}
		System.out.println(year);
	}
	
	public static int isDivided() {
		boolean[][] visit = new boolean[givenRow][givenCol];
		int answer=0;
		boolean allMelt=true;
		for(int i=1;i<givenRow-1;i++) {
			for(int j=1;j<givenCol-1;j++) {
				if(!visit[i][j] && givenmap[i][j]!=0) {
					allMelt=false;
					dfs(i, j, visit);
					answer++;
				}
			}
		}
		//System.out.println("component "+answer);
		if(allMelt) return -1;
		else {
			if(answer>1) return 1;
			else return 0;
		}
	}
	public static void bfs(int row, int col, boolean[][] v) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(row, col, -1));
		while(!q.isEmpty()) {
			Point ppp= q.poll();
			v[ppp.r][ppp.c]=true;
			for(int dir=0;dir<4;dir++) {
				int nextr = ppp.r+dr[dir];
				int nextc = ppp.c+dc[dir];
				if(givenmap[nextr][nextc]!=0 && !v[nextr][nextc]) {
					q.add(new Point(nextr, nextc, -1));
				}
			}
		}
	}
	
	public static void dfs(int row, int col, boolean[][] v) {
		if(!v[row][col] && givenmap[row][col]!=0) {
			v[row][col]=true;
			for(int i=0;i<4;i++) {
				int nextr = row+dr[i];
				int nextc = col+dc[i];
				dfs(nextr, nextc, v);
			}
		}
	}
//	public static void printMap(int[][] m) {
//		for(int i=0;i<m.length;i++) {
//			for(int j=0;j<m[0].length;j++) {
//				System.out.print(m[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}

	public static void getNextMap(ArrayList<Point> curPointList){
		for(int i=0;i<curPointList.size();i++) {
			Point cur = curPointList.get(i);
			int seacount=0;
			for(int dir=0; dir<4; dir++) {
				int nextrow = cur.r+dr[dir];
				int nextcol = cur.c+dc[dir];
				if(givenmap[nextrow][nextcol] ==0) seacount++;
			}
			cur.height-=seacount;
		}
		for(int i=0;i<curPointList.size();i++) {
			Point cur = curPointList.get(i);
			if(cur.height>0) {
				givenmap[cur.r][cur.c] = cur.height;
			}else {
				givenmap[cur.r][cur.c]=0;
			}
		}
	}

}
