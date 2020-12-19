package DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2644_relativeDistance {
	public static int A,B, peopleCount;
	private static class Person{
		int name, count;
		Person(int name, int count){
			this.name = name;
			this.count = count;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		peopleCount = sc.nextInt(); 
		A = sc.nextInt();
		B = sc.nextInt(); 
		int matchCount = sc.nextInt(); 
		int[] parentInfo = new int[peopleCount+1];
		ArrayList<Integer>[] childList = new ArrayList[peopleCount+1];
		for(int i=1;i<=peopleCount;i++) {
			childList[i] = new ArrayList<>();
		}
		for(int i=0;i<matchCount;i++) {
			int parent = sc.nextInt(); 
			int child = sc.nextInt();
			if(parentInfo[parent] == 0) parentInfo[parent]=parent;
			childList[parent].add(child);
			parentInfo[child]=parent;
		}
		System.out.println(bfs(parentInfo, childList));
	}
	public static int bfs(int[] parentInfo, ArrayList<Integer>[]childInfo) {
		Queue<Person> q = new LinkedList<>();
		boolean[] v = new boolean[peopleCount+1];
		q.add(new Person(A, 0));
		int answer = -1;
		while(!q.isEmpty()) {
			Person cur = q.poll();
			v[cur.name]=true;
			//System.out.println("!!out!! "+cur.name+" count: "+cur.count);
			if(cur.name == B) {
				answer = cur.count;
			}else {
				int parent = parentInfo[cur.name];
				if(parent != cur.name && !v[parent]) {
					//System.out.println("add parent: "+parent+" count:"+(cur.count+1));
					q.add(new Person(parent, cur.count+1));
				}
				for(int i=0;i<childInfo[cur.name].size();i++) {
					if(!v[childInfo[cur.name].get(i)] ) {
						//System.out.println("child add "+childInfo[cur.name].get(i)+" count:"+(cur.count+1));
						q.add(new Person(childInfo[cur.name].get(i), cur.count+1));
					}	
				}
				
			}
			
		}
		return answer;
	}

}
