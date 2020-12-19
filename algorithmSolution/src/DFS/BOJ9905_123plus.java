package DFS;

import java.util.Scanner;

public class BOJ9905_123plus {
	public static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		for(int i=0;i<a;i++) {
			int target = sc.nextInt();
			dfs(target,0, "");
			System.out.println(count);
			count=0;
		}
	}
	public static void dfs(int remnant, int num, String answer) {
		if(remnant<=0) {
			if(remnant==0) {
				//System.out.println(answer);
				count++;
			}
		}else {
			for(int i=1;i<4;i++) {
				if(remnant-i>=0) {
					dfs(remnant-i, i, answer+"+"+i);
				}
			}
		}
	}

}
