package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DoublePQ_programmers {

	public static void main(String[] args) {
		String[] op = {"I 7","I 5","I -5","D -1"};
		String[] op1 = {"I 16","D -1"};
		
		String[] op2 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
		print(solution(op2));
	}
	public static void print(int[] d) {
		for(int i=0;i<d.length;i++) {
			System.out.print(d[i]+" ");
		}
		System.out.println();
	}
	public static int[] solution(String[] operations) {
		PriorityQueue<Integer> pqMax = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> pqMin = new PriorityQueue<>();
		
		for(int i=0;i<operations.length;i++) {
			String[] tmp = operations[i].split(" ");
			if(tmp[0].equals("I")) {
				pqMax.add(Integer.parseInt(tmp[1]));
				pqMin.add(Integer.parseInt(tmp[1]));
			}else {
				if(tmp[1].equals("1")) {
					int max = pqMax.poll();
					pqMin.remove(max);
				}else {
					int min = pqMin.poll();
					pqMax.remove(min);
				}
			}
		}
		int globalMin = 0; int globalMax=0;
		if(!pqMin.isEmpty()) globalMin = pqMin.poll();
		if(!pqMax.isEmpty()) globalMax = pqMax.poll();
		int[] answer = new int[2];
		answer[0] = globalMax; answer[1]=globalMin;
		return answer;
	}

}
