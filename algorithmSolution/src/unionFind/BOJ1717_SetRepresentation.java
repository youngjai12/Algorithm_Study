package unionFind;

import java.util.Scanner;

public class BOJ1717_SetRepresentation {

	public static int[] root;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totNum = sc.nextInt(); 
		int opCount = sc.nextInt(); 
		
		root = new int[totNum+1];
		for(int i=0;i<=totNum;i++) {
			root[i] = i;
		}
		
		for(int i=0;i<opCount;i++) {
			int op = sc.nextInt(); 
			int x = sc.nextInt(); 
			int y = sc.nextInt(); 
			
			if(op == 0) {
				union(x, y);
			}else {
				if(Find(x) == Find(y)) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}
		}
	}
	public static void union(int x, int y) {
		int rootx = Find(x);
		int rooty = Find(y);
		
		if(rootx != rooty) {
			if(rootx > rooty) {
				root[rootx] = rooty;
			}else {
				root[rooty] = rootx;
			}
		}
	}
	public static int Find(int x) {
		if( x == root[x]) {
			return x;
		}else {
			int p = Find(root[x]);
			root[x] = p;
			return p;
		}
	}

}
