package stack;

import java.util.ArrayList;
import java.util.Stack;

public class DevelopOrder_programmers {

	public static void main(String[] args) {
//		int[] prog = {95, 90, 99, 99, 80, 99};
//		int[] speeds = {1, 1, 1, 1, 1,1};
//		int[] prog = {95, 97, 98, 96, 98, 95};
//		int[] speeds = {2,2,2,2,3,3};
		int[] prog = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		printArr(solution(prog, speeds));
	}
	public static void printArr(int[] t) {
		for(int i=0;i<t.length;i++) {
			System.out.print(t[i]+" ");
		}
		System.out.println();
	}
	public static int[] solution(int[] progresses, int[] speeds) {
		int[] remnant = new int[speeds.length];
		for(int i=0;i<speeds.length;i++) {
			remnant[i] = (int) Math.ceil((double)(100 - progresses[i])/speeds[i]);
		}
		printArr(remnant);
		Stack<Integer> st = new Stack<>();
		for(int i=remnant.length-1;i>=0;i--) {
			st.push(remnant[i]);
		}
		ArrayList<Integer> ans = new ArrayList<>();
		int top = st.pop();
		int count=1;
		while(!st.isEmpty()) {
			if(top >= st.peek()) {
				st.pop(); count++;
			}else {
				ans.add(count);
				top = st.peek();
				count=0;
			}
		}
		ans.add(count);
		int[] answer= new int[ans.size()];
		for(int i=0;i<ans.size();i++) {
			answer[i] = ans.get(i);
		}
		return answer;
	}

}
