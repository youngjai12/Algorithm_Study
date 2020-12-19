package slidingWindow;

import java.util.Scanner;

public class BOJ2531_sushi_twopointer {

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
		boolean[] eatInfo = new boolean[totKind+1];
		int stIdx=0;
		int endIdx=1; 
		int kind=1;
		eatInfo[menus[stIdx]]=true;
		if(menus[stIdx]!=coupon) kind++;
		int rotate=0;
		int seqSize=2;
		int max=-1;
		while(rotate<2) {
			if(endIdx==plateNum) endIdx=0;
			if(stIdx==plateNum) {
				stIdx=0;
				rotate++;
			}
			System.out.println("stIdx:"+stIdx+" endIdx:"+endIdx+" kind:"+kind+" SeqSize:"+seqSize);
			
			
			if(eatInfo[menus[endIdx]] || seqSize > eatNum) {
				eatInfo[menus[stIdx]]=false;
				if(coupon != menus[stIdx]) kind--;
				//if(menus[stIdx] == menus[endIdx]) kind++;
				stIdx++;seqSize--;
			}else {
				eatInfo[menus[endIdx]] = true;
				if(menus[endIdx]!=coupon) kind++;
				endIdx++;seqSize++;
			}
			
			if(max < kind) {
				max = kind;
			}
		}
		System.out.println(max);
	}

}
