package hackerRank;

import java.util.Scanner;
import java.util.Stack;

public class Highest_Palindrome {
//	5 4
//	11331
//
//	6 2
//	932239
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int digit = sc.nextInt();
		int chance = sc.nextInt();
		String target = sc.next();
		
		System.out.println(solution2(target, digit, chance));
	}
	public static String solution(String t, int digit, int chance) {
		int remnant = chance;
		StringBuilder str = new StringBuilder(t);
		int i=0;
		
		while(i<=digit/2 && remnant !=0) {
			char front = t.charAt(i); char back = t.charAt(digit-i-1);
			if(remnant > 1) {
 				if(front!='9') {
					str.setCharAt(i, '9');
					remnant--;
				}
				if(back!='9') {
					str.setCharAt(digit-i-1, '9');
					remnant--;
				}
			}else{
				if(i == digit-i-1) {
					str.setCharAt(i, '9');
				}else {
				if(front > back) {
					str.setCharAt(digit-i-1, front); remnant--;
				}else if(front < back) {
					str.setCharAt(i, back); remnant--;		
				}
				}
			}
			i++;
		}
		
		String answer = str.toString();
		if(digit==1) answer="9";
		System.out.println(answer);
		if(checkPal(answer, digit)) return answer;
		else return "-1";
	}
	public static String solution2(String t, int digit, int chance) {
		int remnant = chance;
		int i=0;
		StringBuilder str = new StringBuilder(t);
		boolean[] visit = new boolean[100001];
		
		//System.out.println("remnant:"+remnant+" str: "+str.toString());
		while(i <=digit/2 && remnant > 0) {
			char front = t.charAt(i); char back = t.charAt(digit-i-1);
			if(front > back) {
				str.setCharAt(digit-i-1, front); remnant--;visit[digit-i-1]=true;
			}else if(front < back) {
				str.setCharAt(i, back); remnant--;visit[i]=true;
			}
			i++;
		}
		//System.out.println("remnant:"+remnant+" str: "+str.toString());
		if(remnant>0) {
			int j=0;
			while(j<=digit/2 && remnant>0) {
				if(t.charAt(j)!='9') {
					if(j==digit-j-1) {
						str.setCharAt(j, '9');
						remnant--;
					}else {
					if(visit[j] || visit[digit-j-1]) {
						str.setCharAt(j, '9');
						str.setCharAt(digit-1-j, '9');
						remnant--;
					}else {
						if(remnant > 1) {
							str.setCharAt(j, '9');
							str.setCharAt(digit-1-j, '9');
							remnant-=2;
						}
					}
					}
				}
				j++;
			}
		}
		if(digit==1) return "9";
		String ans = str.toString();
		if(checkPal(ans, digit)) return ans;
		else return "-1";
	}
	public static boolean checkPal(String t, int digit) {
		boolean flag=true;
		for(int i=0;i<digit/2;i++) {
			if(t.charAt(i) != t.charAt(digit-1-i)) {
				flag=false;break;
			}
		}
		return flag;
	}

}
