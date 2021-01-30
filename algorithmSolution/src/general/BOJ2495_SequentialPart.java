package general;

import java.util.Scanner;

public class BOJ2495_SequentialPart {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<3;i++) {
			System.out.println(solve(sc.next()));
		}
	}
	public static int solve(String target) {
		int seq=1;
		int max =1;
		for(int i=1;i<target.length();i++) {
			char cur = target.charAt(i);
			if(cur == target.charAt(i-1)) {
				seq++;
				if(seq>max) {
					max = seq;
				}
			}else {
				seq=1;
			}
		}
		return max;
	}

}
