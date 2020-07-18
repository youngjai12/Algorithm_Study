package DFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2178_MazeFind {
	public static int n, m;
	public static int min = 99999;
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {-1, 0, 1, 0};
	private static class Point{
		int x,y, count;
		public Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); 
		m = sc.nextInt();
		sc.nextLine();
		int[][] map = new int[n][m];
		boolean[][] visit = new boolean[n][m];
		for(int i=0;i<n;i++) {
			String line = sc.nextLine();
			for(int j =0;j<m;j++) {
				map[i][j] = line.charAt(j)-48;
			}
		}
		find(map);
		
	}
	public static void find(int[][] map) {
		boolean[][] visit = new boolean[n][m];
		Queue<Point> que= new LinkedList<>();
		que.add(new Point(0,0,1));
		loop1: while(!que.isEmpty()) {
			Point cur = que.poll();
			for(int i=0;i<4;i++) {
				int nextx = cur.x+dx[i];
				int nexty = cur.y+dy[i];
				if(nextx >=0 && nexty >=0 && nextx<n && nexty<m && map[nextx][nexty]==1 && !visit[nextx][nexty]) {
					if(nextx == n-1 && nexty == m-1) {
						System.out.println((cur.count+1));
						break loop1;
					}
					visit[nextx][nexty]=true;
					que.add(new Point(nextx, nexty, cur.count+1));
				}
			}
		}
	}
	

}
