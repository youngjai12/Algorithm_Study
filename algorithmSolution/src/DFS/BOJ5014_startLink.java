package DFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ5014_startLink {
	public static int totFloor, upCount, downCount, destination;
	public static int globalCount =-1;
	private static class Location{
		int location, count;
		Location(int location, int count){
			this.location = location;
			this.count=count;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		totFloor = sc.nextInt();
		int cur = sc.nextInt();
		destination = sc.nextInt(); 
		upCount = sc.nextInt(); 
		downCount = sc.nextInt();
		
		bfs(cur);
		if(globalCount==-1) {
			System.out.println("use the stairs");
		}else {
			System.out.println(globalCount);
		}
	}
	
	public static void bfs(int start) {
		Queue<Location> q = new LinkedList<>();
		q.add(new Location(start, 0));
		boolean[] v = new boolean[1000001];
		v[start]=true;
		while(!q.isEmpty()) {
			Location cur = q.poll();
			if(cur.location == destination) {
				globalCount=cur.count;
				break;
			}
			if(cur.location+upCount <= totFloor && !v[cur.location+upCount] ) {
				q.add(new Location(cur.location+upCount, cur.count+1));
				v[cur.location+upCount]=true;
			}
			if(cur.location-downCount>0 && !v[cur.location-downCount]) {
				q.add(new Location(cur.location-downCount, cur.count+1));
				v[cur.location-downCount]=true;
			}
		}
		
	}
	public static void dfs(int curFloor, boolean[] visit, int count) {
		if(curFloor == destination) {
			globalCount = count;
		}else {
			if(curFloor + upCount <= totFloor && !visit[curFloor+upCount]) {
				visit[curFloor+upCount]=true;
				dfs(curFloor+upCount, visit, count+1);
			}
			if(curFloor-downCount > 0 && !visit[curFloor+downCount]) {
				visit[curFloor-downCount]=true;
				dfs(curFloor-downCount, visit, count+1);
			}
		}
		
	}

}
