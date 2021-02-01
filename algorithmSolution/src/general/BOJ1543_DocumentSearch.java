package general;

import java.util.Scanner;

public class BOJ1543_DocumentSearch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String given = sc.nextLine();
		String pattern = sc.nextLine();
		
		int i=0;int count=0;
		while(i<given.length()) {
			if(given.charAt(i) == pattern.charAt(0) && check(i, given, pattern)){
				i+=pattern.length();
				count++;
			}else {
				i++;
			}
		}
		System.out.println(count);
	}
	
	public static boolean check(int idx, String given, String pattern) {
		boolean answer = true;
		int curIdx = idx;
		int patternIdx=0;
		int tmp = 0;
		while(curIdx < given.length()) {
			if(pattern.charAt(patternIdx) == given.charAt(curIdx)) {
				curIdx++;
				tmp++;
				patternIdx++;
				if(patternIdx == pattern.length()) {
					break;
				}
			}else {
				answer=false;
				break;
			}
		}
		if(tmp!=pattern.length()) {
			answer=false;
		}
		return answer;
	}

}
