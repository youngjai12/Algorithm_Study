package slidingWindow;

import java.util.Scanner;

public class BOJ2531_rotateSushi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int plateNum = sc.nextInt(); 
		int totKind = sc.nextInt(); 
		int eatNum = sc.nextInt(); 
		int coupon = sc.nextInt(); 
		
		int[] menus = new int[plateNum];
		
		for(int i=0;i<plateNum;i++) {
			menus[i] = sc.nextInt();
		}
		int[] eatInfo = new int[totKind+1];
		int kind=0;
		
		// 초기  setting
		for(int i=0;i<eatNum;i++) {
			if(eatInfo[menus[i]]==0) kind++;
			eatInfo[menus[i]]++;
		}
		if(eatInfo[coupon]==0) kind++;
		eatInfo[coupon]++;
	
		int max=-1;
		//먹기 
		for(int st=0;st<plateNum;st++) {
			int endIdx = (st+eatNum)%plateNum;
			eatInfo[menus[st]]--;
			if(eatInfo[menus[st]]==0) kind--;
			if(eatInfo[menus[endIdx]]==0) kind++;
			eatInfo[menus[endIdx]]++;
			
			if(max < kind) max=kind;
		}
		System.out.println(max);
	}

}
