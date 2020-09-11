package unionFind;

import java.util.Scanner;

public class BOJ1976_goTrip {
	public static int cityCount;
	public static int[] rootInfo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cityCount = sc.nextInt(); 
		int plannedCityCount = sc.nextInt(); 
		int[] plan = new int[plannedCityCount];
		rootInfo = new int[cityCount+1];
		for(int i=1;i<=cityCount;i++) {
			rootInfo[i] = i;
		}
		for(int i=1;i<=cityCount;i++) {
			for(int j=1;j<=cityCount;j++) {
				int temp = sc.nextInt();
				if(temp == 1) {
					if(find(i) != find(j)) union(i, j);
				}
			}
		}
		
		for(int i=0;i<plannedCityCount;i++) {
			plan[i] = sc.nextInt();
		}
		int flag=0;
		for(int i=1;i<plannedCityCount;i++) {
			if(find(plan[i-1]) == find(plan[i])) {
				flag++;
			}
		}
		if(flag==plannedCityCount-1) System.out.println("YES");
		else System.out.println("NO");
		
	}
	public static void union(int a, int b) {
		int roota = find(a);
		int rootb = find(b);
		if(roota != rootb) {
			if(roota < rootb) {
				rootInfo[rootb] = roota;
			}else {
				rootInfo[roota] = rootb;
			}
		}
	}
	public static int find(int a) {
		if(a == rootInfo[a]) return a;
		else {
			int p = rootInfo[a];
			rootInfo[a] = find(p);
			return rootInfo[a];
		}
	}
}
