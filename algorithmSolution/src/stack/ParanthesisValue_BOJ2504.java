package stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ParanthesisValue_BOJ2504 {
	private static class Value{
		boolean plus; 
		int num;
		public Value(boolean plus, int num) {
			this.plus = plus;
			this.num = num;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String target = sc.nextLine();
//		System.out.println(solution(target));
//		System.out.println(solution("(()[[]])([])"));
//		System.out.println(solution("([])"));
//		System.out.println(solution("(()[])()"));
//		System.out.println(solution("([)[])()"));
		
		HashMap<Character, Integer> mapTable = new HashMap<Character, Integer>();
		mapTable.put('[', -1);
		mapTable.put('(', -2);
		mapTable.put(')', -3);
		mapTable.put(']', -4);
		
		System.out.println(solution2(target, mapTable));
		
	}
	public static int calculate(Stack<Value> target) {
		ArrayList<Value> answer = new ArrayList<>();
		while(!target.isEmpty()) {
			Value cur = target.pop();
			if(!cur.plus && target.size()>0) {
				if(target.peek().plus) {
					Value tmp = answer.get(answer.size()-1);
					answer.add(new Value(true, tmp.num*cur.num));
				}else answer.add(cur);
			}else answer.add(cur);
		}
		
		int ans=0;
		for(int i=0;i<answer.size();i++) {
			Value tmp = answer.get(i);
			if(tmp.plus) ans+=tmp.num;
			else ans = ans*tmp.num;
		}
		return ans;
	}
	
	public static int solution2(String target, HashMap<Character, Integer> mapTable) {
		// when pushing to stack : '['=-1, '('=-2, ')'=-3 ']'=-4
		Stack<Integer> st = new Stack<>();
		boolean error=false;
		for(int i=0;i<target.length();i++) {
			char cur = target.charAt(i);
			int curValue = mapTable.get(cur);
			if(curValue >-3) st.push(curValue);
			else {
				if(curValue == -3) {
					if(st.isEmpty()) {
						error=true;
						break;
					}else {
					if(st.peek()>0) {
						int tmpSum = 0;
						while(!st.isEmpty() && st.peek()>0) {
							int top = st.pop();
							tmpSum+=top;
						}
						if(st.isEmpty() || st.peek() == -1 ) {
							error=true;
							break;
						}
						else if(st.peek() == -2) {
							st.pop();
							st.push(tmpSum*2);
						}
					}else {
						if(st.peek() == -2) {
							st.pop();
							st.push(2);
						}
						else {
							error=true;
							break;
						}
					}
				}
				}else if(curValue == -4) {
					if(st.isEmpty()) {
						error=true;
						break;
					}else { 
						if(st.peek() > 0) {
						int tmpSum = 0;
						while(!st.isEmpty() && st.peek() > 0) {
							int top  = st.pop();
							tmpSum+=top;
						}
						if(st.isEmpty() || st.peek() == -2) {
							error=true;
							break;
						}else if(st.peek() == -1) {
							st.pop();
							st.push(tmpSum*3);
						}
					}else {
						if(st.peek() == -1) {
							st.pop();
							st.push(3);
						}
						else {
							error=true;
							break;
						}
					}
					}
				}
			}
		}
		int answer=0;
		while(!st.isEmpty()) {
			answer+=st.pop();
		}
		if(error) answer=0;
		return answer;
	}
	public static int solution(String target) {
		Stack<Value> calculation = new Stack<>();
		Stack<Character> numStack = new Stack<>();
		boolean error = false;
		int totSum=0;
		for(int i=0;i<target.length();i++) {
			char cur = target.charAt(i);
			if(cur == '(') {
				char next = target.charAt(i+1);
				numStack.push('(');
				if(next == ')') calculation.push(new Value(true, 2));
				else if(next == '(' || next == '[') calculation.push(new Value(false, 2));	
				else if(target.charAt(i+1) == ']') {
					error=true;
					break;
				}
			}else if(cur == '[') {
				char next = target.charAt(i+1);
				numStack.push('[');
				if(next == ']') calculation.push(new Value(true, 3));
				else if(next == '(' || next == '[') calculation.push(new Value(false, 3));	
				else if(next == ')') {
					error=true;
					break;
				}
			}else if(cur == ']') {
				if(numStack.isEmpty()|| numStack.peek() == '(' || numStack.peek() == ']' || numStack.peek() == ')') {
					error=true;
					break;
				}else numStack.pop();
			}else if(cur == ')') {
				if(numStack.isEmpty() || numStack.peek() == '[' || numStack.peek() == ']' || numStack.peek() == ')') {
					error=true;
					break;
				}else numStack.pop();
			}
			
			if(numStack.isEmpty()) {
				totSum+=calculate(calculation);
			}
		}
		if(error || !numStack.isEmpty()) totSum =0;
		return totSum;
	}

}
