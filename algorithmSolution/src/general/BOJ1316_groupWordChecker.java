package general;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ1316_groupWordChecker {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int wordCount = sc.nextInt();
		int answer=0;
		for(int i=0;i<wordCount;i++) {
			if(solution(sc.next())) {
				answer++;
			}
		}
		System.out.println(answer);
	}
	public static boolean solution(String target) {
		HashMap<Character, Integer> visit = new HashMap<>();
		boolean flag = true;
		for(int i=0;i<target.length();i++) {
			char cur = target.charAt(i);
			if(visit.get(cur) == null) {
				visit.put(cur,1);
			}else {
				if(target.charAt(i-1) == cur) {
					visit.put(cur, visit.get(cur)+1);
				}else {
					flag=false;
				}
			}
		}
		return flag;
	}
	

}
