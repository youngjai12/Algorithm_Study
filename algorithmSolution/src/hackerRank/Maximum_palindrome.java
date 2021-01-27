package hackerRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// 21.01.26  : still not yet solved.
public class Maximum_palindrome {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		String s = sc.next();
		HashMap<Character, Integer> charMapTable = new HashMap<>();
		int charId =0;
		for(int i=0;i<s.length();i++) {
			char target = s.charAt(i);
			if(charMapTable.get(target) == null) {
				charId++;
				charMapTable.put(target, charId);
			}
		}
		System.out.println(charMapTable);
		int letterCount = charMapTable.keySet().size();
		int[][] charMap = new int[s.length()+1][letterCount+1];
		for(int i=1;i<=s.length();i++) {
			char target = s.charAt(i-1);
			int idx = charMapTable.get(target);
			charMap[i][idx] = charMap[i-1][idx]+1; 
			for(int j=1;j<=letterCount;j++) {
				if(j!=idx) {
					charMap[i][j] = charMap[i-1][j];
				}
			}
		}
		int lineCount = sc.nextInt();
		for(int q=0;q<lineCount;q++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			int[] info = new int[letterCount+1];
			for(int j=1;j<=letterCount;j++) {
				info[j] = charMap[r][j] = charMap[l][j];
			}
			System.out.println(calculate(info));;
		}
		
	}
	public static int calculate(int[] row) {
		int eventTot =0 ;
		int odd=0;
		ArrayList<Integer> exclude = new ArrayList<>();
		for(int i=0;i<row.length;i++) {
			if(row[i]%2==0) {
				eventTot+=(row[i]/2);
				if(row[i]/2 > 1) {
					exclude.add(row[i]/2);
				}
			}else {
				odd++;
			}
		}
		
		int[] factory;
		if(eventTot < 3) {
			factory  = factorialTable(2);
		}else {
			factory = factorialTable(eventTot);
		}
		int exclude2 = 1;
		for(int i=0;i<exclude.size();i++) {
			exclude2 = exclude2 * factory[exclude.get(i)];
		}
		
	}
	public static int getMax(int[] t) {
		int max = -1;
		for(int i=0;i<t.length;i++) {
			if( max < t[i]) {
				max = t[i];
			}
		}
		return max;
	}
	public static int[] factorialTable(int maxNum) {
		 int[] info = new int[maxNum+1];
		 info[0] = 1;
		 info[1] = 1;
		 int cur = 2;
		 while(cur <= maxNum) {
			 info[cur] = info[cur-1]*cur;
			 cur++;
		 }
		 return info;
		 
	}
	
	public static void printMap(int[][] t) {
		for(int i=0;i<t.length;i++) {
			for(int j=0;j<t[0].length;j++) {
				System.out.print(t[i][j]+" ");
			}
			System.out.println();
		}
	}

}
