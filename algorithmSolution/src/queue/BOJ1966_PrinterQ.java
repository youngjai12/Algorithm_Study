package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1966_PrinterQ {
	private static class Doc{
		int num, idx;
		public Doc(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCount = sc.nextInt();
		for(int q=0;q<testCount;q++) {
			int numCount = sc.nextInt();
			int targetIdx = sc.nextInt();
			int[] info = new int[numCount];
			ArrayList<Doc> qq = new ArrayList<>();
			int curPriority=0;
			for(int i=0;i<numCount;i++) {
				qq.add(new Doc(sc.nextInt(), i));
			}
			int outCount=0;
			while(!qq.isEmpty()) {
				Doc out = qq.get(0); qq.remove(0);
				
				boolean outFlag=true;
				for(int i=0;i<qq.size();i++) {
					if(out.num < qq.get(i).num) {
						qq.add(out);
						outFlag=false;
						break;
					}
				}
				if(outFlag) {
					if(out.idx == targetIdx) {
						break;
					}else {
						outCount++;
					}
				}
			}
			System.out.println(outCount+1);
			
		}
	}

}
