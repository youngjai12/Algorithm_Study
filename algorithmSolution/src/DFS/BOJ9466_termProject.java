package DFS;

import java.util.Scanner;

public class BOJ9466_termProject {
	public static int[] visit, cycleStart, info;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int qCount = sc.nextInt();
		for(int i=0;i<qCount;i++) {
			int nodeCount = sc.nextInt();
			info = new int[nodeCount+1];
			for(int j=1;j<=nodeCount;j++) {
				info[i] = sc.nextInt();
			}
			visit = new int[nodeCount+1];
			cycleStart = new int[nodeCount+1];
			int answer=0;
			for(int j=1;j<=nodeCount;j++) {
				if(visit[j]==0) {
					System.out.println("start "+j);
					cycleStart[j]=j;
					answer += getCycleMemberCount(j, j, 1);
				}
			}
			System.out.println(nodeCount-answer);
			
		}
	}
	public static int getCycleMemberCount(int prev, int start, int level) {
		int next = info[start];
		visit[start]=level;
		cycleStart[start] = cycleStart[prev];
		// cycle이 형성되었다.
		if(cycleStart[start] == next && visit[next]!=0) {
			return  (level - visit[next]+1);
		}else if(cycleStart[start]!=next && visit[next]!=0) {
			return 0;
		}
		return getCycleMemberCount(start, next, level+1);
	}

}
