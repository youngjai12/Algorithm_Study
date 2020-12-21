package DFS;

import java.util.ArrayList;
import java.util.Arrays;

public class MockTest_programmers {

	public static void main(String[] args) {
		int[] q1 = {1,2,3,4,5};
		int[] q2 = {1,3,2,4,2};
		
		printArr(solution(q1));
	}
	public static void printArr(int[] t) {
		for(int i=0;i<t.length;i++) {
			System.out.print(t[i]+" ");
		}
		System.out.println();
	}
	public static int[] solution(int[] answers) {
		 int p1count=0;
		 int p2count=0;
		 int p3count=0;
		 
		 for(int i=0;i<answers.length;i++) {
			 if(answers[i] == p1(i)) p1count++;
			 if(answers[i] == p2(i)) p2count++;
			 if(answers[i] == p3(i)) p3count++;
		}
		int[] tmp = new int[4];
		ArrayList<Integer> ans = new ArrayList<>();
		tmp[1] = p1count; tmp[2]=p2count; tmp[3]=p3count;
		int max= getMax(tmp);
		for(int i=1;i<4;i++) {
			if(max == tmp[i]) ans.add(i);
		}
		int[] answer = new int[ans.size()];
		for(int i=0;i<ans.size();i++) {
			answer[i] = ans.get(i);
		}
		Arrays.sort(answer);
		return answer;
	}
	public static int getMax(int[] d) {
		int max=-1;
		for(int i=0;i<d.length;i++) {
			if(max < d[i]) max = d[i];
		}
		return max;
	}
	public static int p1(int target) {
		if((target+1)%5==0) return 5;
		else return (target+1)%5;
	}
	public static int p2(int targ) {
		int target = targ+1;
		if(target%2==1) return 2;
		else {
			if((target/2)%4 == 1) return 1;
			else if((target/2)%4 == 2) return 3;
			else if((target/2)%4 == 3) return 4;
			else return 5; 
		}
	}
	public static int p3(int targ) {
		int target = targ+1;
		if(target%10 ==1||target%10 ==2) return 3;
		else if(target%10 ==3||target%10 ==4) return 1;
		else if(target%10 ==5||target%10 ==6) return 2;
		else if(target%10 ==7||target%10 ==8) return 4;
		else return 5;
	}

}
