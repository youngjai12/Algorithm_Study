package general;

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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		target = sc.next();
		pattern = sc.next();
		System.out.println(solution4(target, pattern));
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
	public static String solution3(String target, String pattern) {
		String answer = "";
		int idx=0;
		ArrayList<Integer> orig = new ArrayList<>();
		ArrayList<Integer> work = new ArrayList<>();
		while(idx < target.length()) {
			char cur = target.charAt(idx);
			if(pattern.contains(cur+"")) {
				if(!work.isEmpty()) {
					int curIdx = pattern.indexOf(cur);
					char peekChar = target.charAt(work.get(work.size()-1));
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
			answer = "FRULA";
		}else {
			for(int i=0;i<orig.size();i++) {
				answer+=target.charAt(orig.get(i));
			}
		}
		return answer;
	}
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
