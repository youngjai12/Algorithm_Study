package heap;


import java.util.Arrays;
import java.util.PriorityQueue;

public class DiskController_programmers {
	private static class Work implements Comparable<Work>{
		int enter,spend;
		public Work(int enter, int spend) {
			this.enter=enter;
			this.spend=spend;
		}
		@Override
		public int compareTo(Work target) {
			if(spend < target.spend) return -1;
			else return 1;
		}
	}
	private static class Work1 implements Comparable<Work1>{
		int enter,spend;
		public Work1(int enter, int spend) {
			this.enter=enter;
			this.spend=spend;
		}
		@Override
		public int compareTo(Work1 target) {
			if(enter < target.enter) return -1;
			else if(enter > target.enter) return 1;
			else {
				if(spend < target.spend) return -1 ; 
				else return 1;
			}
		}
	}
	public static void main(String[] args) {
		int[][] jobs = {{0,3},{1,9},{2,6}};
		int[][] jobs2 = {{0,4},{15,3}, {3,7}, {19, 3}};
		int[][] jobs3 = {{2,4}, {6,2}, {8,3}};
		int[][] jobs4 = {{2,4}, {10,4}, {6,4}, {11,3}, {12,4}};
		int[][] jobs5 = {{1,2}, {2,2}, {3,2}, {4,2}};
		int[][] jobs6 = {{2,2}, {1,2}, {4,2},{3,2}};
		int[][] jobs7 = {{3,7},{3,2},{6,3},{1,4}};
		int[][] jobs8 = {{2,2},{2,4},{3,25},{4,7}};
		int[][] jobs9 = {{2,3},{6,3},{10,3},{15,3}};
		int[][] jobs10 = {{300,600},{400,200},{500,150}};
		int[][] jobs11 = {{0, 9}, {0, 4}, {0, 5}, {0, 7}, {0, 3}};  //13
		int[][] jobs12 = {{1, 9}, {1, 4}, {1, 5}, {1, 7}, {1, 3}};  //13
		int[][] jobs13 = {{0, 3}, {4, 4}, {5, 3}, {4, 1}}; // 3
		int[][] jobs14 = {{0, 3}, {1, 9}, {500, 6}};
		
		
		
		System.out.println(solution(jobs12));
	}
	public static void printWork(Work1[] t) {
		for(int i=0;i<t.length;i++) {
			System.out.println(t[i].enter+":"+t[i].spend);
		}
		System.out.println();
	}
	public static Work1[] orderWork(int[][] t) {
		Work1[] answer = new Work1[t.length]; 
		for(int i=0;i<t.length;i++) {
			answer[i] = new Work1(t[i][0], t[i][1]);
		}
		Arrays.sort(answer);
		return answer;
	}
	public static int solution(int[][] jobs) {
		
		Work1[] worklist = orderWork(jobs);
		printWork(worklist);
		int workIdx=1;
		int totTime=0;
		PriorityQueue<Work> pq = new PriorityQueue<>();
		pq.add(new Work(worklist[0].enter, worklist[0].spend));
		int curTime = worklist[0].enter;
		while(true) {
			while(!pq.isEmpty()) {
				Work tmp = pq.poll();
				curTime+=tmp.spend;
				totTime+=(curTime - tmp.enter);
				while(workIdx < jobs.length && curTime >= worklist[workIdx].enter) {
					pq.add(new Work(worklist[workIdx].enter, worklist[workIdx].spend));
					workIdx++;
				}
			}
			curTime++;
			if(workIdx >= jobs.length) break;
			if(curTime >= worklist[workIdx].enter) {
				pq.add(new Work(worklist[workIdx].enter, worklist[workIdx].spend));
				workIdx++;
			}
			
		}
		return totTime /jobs.length;
	}

}
