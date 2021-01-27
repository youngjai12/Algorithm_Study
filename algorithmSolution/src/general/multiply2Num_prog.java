package general;

import java.util.Arrays;
import java.util.Collections;

public class multiply2Num_prog {
	public static int min;
	public static void main(String[] args) {
		int[] a = {1, 4, 2};
		int[] b = {5, 4, 4};
		
		int[] a1 = {1, 2};
		int[] b1 = {3, 4};
		
		System.out.println(solution(a1,b1));
	}
	public static void printArr(int[] t) {
		for(int i=0;i<t.length;i++) {
			System.out.print(t[i]+" ");
		}
		System.out.println();
	}
	public static int solution(int []A, int []B) {
		Arrays.sort(A);
		Arrays.sort(B);
		int[] newB = new int[B.length];
		for(int i=0;i<newB.length;i++) {
			newB[i] = B[newB.length-1-i];
		}
		printArr(A);
		printArr(newB);
		int acc=0;
		for(int i=0;i<A.length;i++) {
			acc += A[i]*newB[i];
		}
		return acc;
	}
	
	public static void dfs(int count, int acc, int[] a, int[] b, boolean[] visitb) {
		if(count == a.length) {
			if(min > acc) min = acc;
		}else {
			for(int i=0;i<b.length;i++) {
				if(!visitb[i]) {
					int tmp = a[count] * b[i];
					visitb[i]=true;
					dfs(count+1, acc+tmp, a, b, visitb);
					visitb[i]=false;
				}
			}
		}
	}

}
