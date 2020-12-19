package DFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ9205_DrinkBeer {
	private static class Point{
		int row,col;
		Point(int row, int col){
			this.row = row; 
			this.col = col; 
		}
	}
	private static class Point2{
		int row,col;
		boolean v; 
		Point2(int row, int col, boolean v){
			this.row = row;
			this.col = col;
			this.v = v;
		}
	}
	public static int getDistance(Point a, Point b) {
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}
	public static boolean answerFlag;
	public static Point end;
	public static int storeCount;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		for(int question=0;question<testCase;question++) {
			storeCount = sc.nextInt(); 
			answerFlag=false;
			Point start = new Point(sc.nextInt(), sc.nextInt());
			Point[] storeInfo = new Point[storeCount+1];
			for(int i=0;i<storeCount;i++) {
				storeInfo[i] = new Point(sc.nextInt(), sc.nextInt());
			}
			storeInfo[storeCount] = new Point(sc.nextInt(), sc.nextInt());
			end = storeInfo[storeCount];
			//System.out.println("destination: ["+end.row+","+end.col+"]");
			boolean[] visit = new boolean[storeCount+1];
			//dfs(0, start, storeInfo, visit);
			boolean answer = bfs(start, storeInfo,visit);
			if(answer) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
			
		}
		
	}
	public static boolean bfs(Point st, Point[] stores, boolean[] visit) {
		Queue<Point> q = new LinkedList<>();
		q.add(st);
		boolean answer = false; 
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.row == end.row && cur.col == end.col) {
				answer=true;
				break;
			}
			for(int i=0;i<stores.length;i++) {
				if(getDistance(cur, stores[i])<=1000 && !visit[i]) {
					visit[i]=true;
					q.add(stores[i]);
				}
			}
		}
		return answer;
	}
	public static void dfs(int count, Point st, Point[] stores, boolean[] visit) {
		System.out.println(count+" arrived ["+st.row+","+st.col+"]");
		if(st.row == end.row && st.col == end.col) {
			answerFlag=true;
			visit[storeCount]=true;
		}else {
		for(int i=0;i<stores.length;i++) {
			if(getDistance(st, stores[i]) <= 1000 && !visit[i]) {
				visit[i] = true; 
				dfs(count+1, stores[i], stores, visit);
				visit[i] = false;
			}
		}
	}
	}

}
