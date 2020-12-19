package DFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ15663_NM9 {
	public static int combNum;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCount = sc.nextInt();
		combNum = sc.nextInt();
		ArrayList<Integer> numList = new ArrayList<>();
		int[] countInfo = new int[10000];
		for(int i=0;i<numCount;i++) {
			int t = sc.nextInt();
			countInfo[t]++;
			if(countInfo[t]==1) {
				numList.add(t);
			}
		}
		Collections.sort(numList);
		dfs(combNum, countInfo, numList, "");
	}
	public static void dfs(int remnant, int[] countInfo, ArrayList<Integer> numList, String answer) {
		if(remnant == 0) {
			System.out.println(answer);
		}else {
			for(int i=0;i<numList.size();i++) {
				int target = numList.get(i);
				if(countInfo[target]>0) {
					countInfo[target]--;
					String tempString = "";
					if(remnant != combNum) {
						tempString = answer+" "+target;
					}else {
						tempString=""+target;
					}
					dfs(remnant-1, countInfo, numList, tempString);
					countInfo[target]++;
				}
			}
		}
	}

}
