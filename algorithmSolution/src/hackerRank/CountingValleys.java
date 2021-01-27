package hackerRank;

import java.util.Scanner;
import java.util.Stack;

public class CountingValleys {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int arrCount = sc.nextInt();
		String map = sc.next();
		
		int height =0;
		Stack<Character> st = new Stack<>();
		st.push(map.charAt(0));
		if(map.charAt(0) == 'D') height=-1;
		else height=1;
		int answer=0;
		
		for(int i=1;i<arrCount;i++) {
			char step = map.charAt(i);
			if(st.isEmpty()) {
				st.push(step);
			}else {
			if(step != st.peek()) {
				st.pop();
				if(st.size()==0 && height<0) {
					answer++;
				}
			}else st.push(step);
			}
			if(step == 'D') height--;
			else height++;
		}
		System.out.println(answer);
	}

}
