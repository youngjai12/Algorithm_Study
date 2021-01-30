package general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ9935_StringExplode {
	public static String pattern, target;
	private static class Elem{
		char ch;
		int idx;
		public Elem(char ch, int idx) {
			this.ch = ch;
			this.idx = idx;
		}
	}
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
//		target = sc.next();
//		pattern = sc.next();
//		System.out.println(solution5(target.toCharArray(), pattern));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String origin = br.readLine();
		String remove = br.readLine();
		
		System.out.println(solution5(origin.toCharArray(), remove));
		//System.out.println(solution1(origin,remove));
		
	}
	
	public static String solution1(String target, String pattern) {
		Pattern p = Pattern.compile(pattern);
		
		String result = target;
		while(true) {
			Matcher m = p.matcher(result);
			if(m.find()) {
				result = m.replaceAll("");
			}else break;
		}
		if(result.length()==0) return "FRULA";
		else return result;
	}
	public static void printArr(ArrayList<Integer> d) {
		for(int i=0;i<d.size();i++) {
			System.out.print(d.get(i)+" ");
		}
		System.out.println();
	}
	public static String solution6(String target, String pattern) {
		int i=0;
		String answer="";
		while(i < target.length()) {
			char cur = target.charAt(i);
			if(cur == pattern.charAt(0)) {
				i  = isBomb(i, target, pattern);
			}else {
				answer+=cur;
			}
			i++;
		}
		if(answer.length()==0) {
			return "FRULA";
		}else {
			return answer;
		}
	}
	public static int isBomb(int curIdx, String target, String pattern) {
		int tmpIdx = 0;
		int idx = curIdx;
		int startCount=0;
		int answerIdx=curIdx;
		while(idx < target.length()) {
			char curChar = target.charAt(idx);
			if(pattern.charAt(tmpIdx) == target.charAt(idx)) {
				if(tmpIdx == pattern.length()-1) {
					if(startCount==0) {
						answerIdx=idx;
						break;
					}
					else {
						startCount--;
						tmpIdx = pattern.indexOf(target.charAt(idx - pattern.length()))+1;
					}
				}else {
					tmpIdx++;
				}
			}else {
				if(target.charAt(idx) == pattern.charAt(0)) {
					startCount++;
					tmpIdx=1;
				}else {
					answerIdx = curIdx;
					break;
				}
			}
			idx++;
		}
		return answerIdx;
	}
	public static String solution5(char[] target, String pattern) {
		Stack<Integer> st = new Stack<>();
		for(int i=target.length-1;i>=0;i--) {
			char cur = target[i];
			st.push(i);
			if(cur == pattern.charAt(0) && st.size()>=pattern.length()) {
				boolean flag=true;
				for(int j=0;j<pattern.length();j++) {
					if(pattern.charAt(j) != target[st.get(st.size()-j-1)]) {
						flag=false;break;
					}
				}
				if(flag) {
					for(int c=0;c<pattern.length();c++) {
						st.pop();
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		if(st.isEmpty()) {
			return "FRULA";
		}else {
		while(!st.isEmpty()) {
			sb.append(target[st.pop()]);
		}
		return sb.toString();
		}
	}
	// 메모리 초과나서, arrayList 안쓰고, String으로 붙여주는 것. 
	public static String solution4(String target, String pattern) {
		String answer = "";
		int idx=0;
		String work ="";
		String orig="";
		while(idx < target.length()) {
			char cur = target.charAt(idx);
			if(pattern.contains(cur+"")) {
				if(work.length()!=0) {
					int curIdx = pattern.indexOf(cur);
					char peekChar = work.charAt(work.length()-1);
					int peekIdx = pattern.indexOf(peekChar);
					if(peekIdx+1 == curIdx || curIdx == 0) {
						if(curIdx == pattern.length()-1) {
							int tmp=0;
							work = work.substring(0, work.length()-pattern.length()+1);
						}else {
							work+=cur;
						}
					}else {
						orig+=work;
						work="";
					}
				}else {
					if(cur == pattern.charAt(0)) {
						work+=cur;
					}else {
						orig+=cur;
					}
				}
			}else {
				orig+=cur;
			}
			idx++;
		}
		if(orig.length()==0) {
			answer = "FRULA";
		}else {
			answer = orig;
		}
		return answer;
	}
	
	// character 의 ArrayList 라서 메모리초과인가 싶어서, Idx 들을 tracking 하는 arrayList 구현 
	public static String solution3(char[] target, String pattern) {
		//String answer = "";
		int idx=0;
		ArrayList<Integer> orig = new ArrayList<>();
		ArrayList<Integer> work = new ArrayList<>();
		StringBuilder answ = new StringBuilder();
		
		while(idx < target.length) {
			char cur = target[idx];
			if(pattern.contains(cur+"")) {
				if(!work.isEmpty()) {
					int curIdx = pattern.indexOf(cur);
					char peekChar = target[work.get(work.size()-1)];
					int peekIdx = pattern.indexOf(peekChar);
					if(peekIdx+1 == curIdx || curIdx == 0) {
						if(curIdx == pattern.length()-1) {
							int tmp=0;
							while(tmp<pattern.length()-1) {
								work.remove(work.size()-1);
								tmp++;
							}
						}else {
							work.add(idx);
						}
					}else {
						while(!work.isEmpty()) {
							orig.add(work.get(0));
							work.remove(0);
						}
						orig.add(idx);
					}
				}else {
					if(cur == pattern.charAt(0)) {
						work.add(idx);
					}else {
						orig.add(idx);
					}
				}
			}else {
				orig.add(idx);
			}
			idx++;
		}
		
		if(orig.size()==0) {
			return "FRULA";
		}else {
			for(int i=0;i<orig.size();i++) {
				answ.append(target[orig.get(i)]);
			}
			return answ.toString();
		}
	}
	
	//  
	public static String solution2(String target, String pattern) {
		String answer = "";
		int idx=0;
		ArrayList<Character> orig = new ArrayList<>();
		ArrayList<Character> work = new ArrayList<>();
		while(idx < target.length()) {
			char cur = target.charAt(idx);
			if(pattern.contains(cur+"")) {
				if(!work.isEmpty()) {
					int curIdx = pattern.indexOf(cur);
					char peekChar = work.get(work.size()-1);
					int peekIdx = pattern.indexOf(peekChar);
					if(peekIdx+1 == curIdx || curIdx == 0) {
						if(curIdx == pattern.length()-1) {
							int tmp=0;
							while(tmp<pattern.length()-1) {
								work.remove(work.size()-1);
								tmp++;
							}
						}else {
							work.add(cur);
						}
					}else {
						while(!work.isEmpty()) {
							orig.add(work.get(0));
							work.remove(0);
						}
						orig.add(cur);
					}
				}else {
					if(cur == pattern.charAt(0)) {
						work.add(cur);
					}else {
						orig.add(cur);
					}
				}
			}else {
				orig.add(cur);
			}
			idx++;
		}
		if(orig.size()==0) {
			answer = "FRULA";
		}else {
			for(int i=0;i<orig.size();i++) {
				answer+=orig.get(i);
			}
		}
		return answer;
	}

}
