package general;

import java.util.Scanner;
import java.util.Stack;

public class BOJ5397_keyLogger {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		for(int i=0;i<count;i++) {
			String question = sc.next();
			//System.out.println(solution(question));
			System.out.println(solution2(question));
		}
		
	}
	public static String solution2(String target) {
		Stack<Character> st = new Stack<>();
		Stack<Character> tmp = new Stack<>();
		for(int i=0;i<target.length();i++) {
			char cur = target.charAt(i);
			if(cur == '<') {
				if(!st.isEmpty()) tmp.add(st.pop());
			}else if(cur == '>') {
				if(!tmp.isEmpty()) st.add(tmp.pop());
			}else if(cur == '-'){
				if(!st.isEmpty()) st.pop();
			}else {
				st.push(cur);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<st.size();i++) {
			sb.append(st.elementAt(i));
		}
		while(!tmp.isEmpty()) {
			sb.append(tmp.pop());
		}
		return sb.toString();
	}
	public static String solution(String target) {
		StringBuilder sb = new StringBuilder();
		int cursor=0;
		for(int i=0;i<target.length();i++) {
			char cur = target.charAt(i);
			if(cur == '>') {
				cursor++;
			}else if(cur == '<') {
				cursor--;
			}else if(cur =='-') {
				if(cursor>0 && sb.length()>=cursor){
					sb.deleteCharAt(cursor-1);
					cursor--;
				}
			}else {
				if(cursor > sb.length()) {
					cursor = sb.length();
				}
				if(cursor <0) {
					cursor = 0;
				}
				if(cursor ==0) {
					sb.insert(0, cur);
				}else if(cursor >0 && cursor < sb.length()) {
					sb.insert(cursor, cur);
				}else {
					sb.append(cur);
				}
				cursor++;
				
			}
			
		}
		return sb.toString();
	}

}
