package DFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1260_DFSandBFS {
	public static int edgeNum, nodeNum;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		nodeNum = sc.nextInt();
		edgeNum = sc.nextInt();
		int startNode = sc.nextInt();
		
		ArrayList<Integer>[] graph = new ArrayList[nodeNum+1];
		for(int i=0;i<=nodeNum;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<edgeNum;i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			graph[start].add(end);
			graph[end].add(start);
		}
		
		boolean[] visit = new boolean[nodeNum+1];
		visit[startNode]=true;
		dfsRecursive(graph, startNode, 1, visit, ""+startNode);
	}
	public static void dfsRecursive(ArrayList<Integer>[] graph, int start, int count, boolean[] visited, String path) {
		if(count == nodeNum) {
			System.out.println(path);
		}else {
			for(int i=0;i<graph[start].size();i++) {
				Collections.sort(graph[start]);
				int dest = graph[start].get(i);
				if(!visited[dest]) {
					visited[dest]=true;
					path = path+" "+dest;
					dfsRecursive(graph, dest, count+1, visited, path);
				}
			}
		}
	}
	
	public static void bfs(ArrayList<Integer>[] graph, int start, int count, boolean[] visited) {
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		String path = "";
		while(!que.isEmpty()) {
			int cur = que.poll();
			if(!visited[cur]) {
				visited[cur]=true;
				
			}
			for(int i=0;i<graph[cur].size();i++) {
				que.add(graph[cur].get(i));
			}
			
		}
	}

}
