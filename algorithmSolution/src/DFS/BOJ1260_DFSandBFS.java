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
		System.out.println(dfsRecursive(graph, startNode, visit, ""+startNode));
		visit = new boolean[nodeNum+1];
		visit[startNode]=true;
		bfs(graph, startNode, visit);
	}
	
	public static String dfsRecursive(ArrayList<Integer>[] graph, int start, boolean[] visited, String path) {
		String answer = path;	
		Collections.sort(graph[start]);
			for(int i=0;i<graph[start].size();i++) {
				int dest = graph[start].get(i);
				if(!visited[dest]) {
					visited[dest]=true;
					path = path+" "+dest;
					answer = dfsRecursive(graph, dest, visited, path);
				}
			}
		return answer;
			
	}
	
	public static void bfs(ArrayList<Integer>[] graph, int start, boolean[] visited) {
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		String path = ""+start;
		while(!que.isEmpty()) {
			int cur = que.poll();
			if(!visited[cur]) {
				visited[cur]=true;
				path = path+" "+cur;
			}
			Collections.sort(graph[cur]);
			for(int i=0;i<graph[cur].size();i++) {
				int next = graph[cur].get(i);
				if(!visited[next]) {
					que.add(next);
				}
			}
		}
		System.out.println(path);
	}

}
