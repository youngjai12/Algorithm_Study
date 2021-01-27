package BFS;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Hacking_BOJ10282 {
	private static class Computer implements Comparable<Computer>{
		int name,time;
		public Computer(int name, int time) {
			this.name = name;
			this.time = time; 
		}
		@Override
		public int compareTo(Computer o) {
			if(time < o.time) return -1;
			else return 1;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCaseCount = sc.nextInt();
		for(int q=0;q<testCaseCount;q++) {
			int compCount = sc.nextInt(); 
			int dCount = sc.nextInt(); 
			int start = sc.nextInt(); 
			ArrayList<Computer>[] graph = new ArrayList[compCount+1];
			
			for(int i=0;i<dCount;i++) {
				int to = sc.nextInt();
				int from = sc.nextInt(); 
				int time = sc.nextInt(); 
				if(graph[from]==null) graph[from] = new ArrayList<>();
				graph[from].add(new Computer(to, time));
			}
			solution(graph, start);
		}
	}
	public static void printArr(int[] d) {
		for(int i=0;i<d.length;i++) {
			System.out.print(d[i]+" ");
		}
		System.out.println();
	}
	public static void solution(ArrayList<Computer>[] info, int start) {
		PriorityQueue<Computer> pq = new PriorityQueue<>();
		pq.add(new Computer(start, 0));
		int[] dist = new int[info.length];
		for(int i=1;i<info.length;i++) {
			dist[i] = 200000;
		}
		dist[start]=0;
		boolean[] v = new boolean[info.length];
		while(!pq.isEmpty()) {
			Computer cur = pq.poll();
			v[cur.name]=true;
			ArrayList<Computer> tmp = info[cur.name];
			if(tmp!=null) {
			for(int i=0;i<tmp.size();i++) {
				int next=tmp.get(i).name;
				if(!v[next]) {
					if(dist[next] > tmp.get(i).time + dist[cur.name]) {
						dist[next] = tmp.get(i).time + dist[cur.name];
					}
				pq.add(new Computer(next, dist[next]));
				}
			}
			}else {
				break;
			}
		}
		int max=-1; int count1=0;
		for(int i=1;i<dist.length;i++) {
			if(v[i]) {
				count1++;
				if( max < dist[i]) max = dist[i];
			}
		}
		System.out.println(count1+" "+max);
	}
	public static void solution2(ArrayList<Computer>[] info, int start) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(start);
		int[] dist = new int[info.length];
		for(int i=1;i<info.length;i++) {
			dist[i] = 200000;
		}
		dist[start]=0;
		boolean[] v = new boolean[info.length];
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			v[cur]=true;
			ArrayList<Computer> tmp = info[cur];
			if(tmp!=null) {
			for(int i=0;i<tmp.size();i++) {
				int next=tmp.get(i).name;
				if(!v[next]) {
					if(dist[next] > tmp.get(i).time + dist[cur]) {
						dist[next] = tmp.get(i).time + dist[cur];
					}
				pq.add(next);
				}
			}
			}else {
				break;
			}
		}
		int max=-1; int count1=0;
		for(int i=1;i<dist.length;i++) {
			if(v[i]) {
				count1++;
				if( max < dist[i]) max = dist[i];
			}
		}
		System.out.println(count1+" "+max);
	}
}
