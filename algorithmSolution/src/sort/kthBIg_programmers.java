package sort;

import java.util.Arrays;

public class kthBIg_programmers {

	public static void main(String[] args) {
		int[] array1 = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands1 = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		solution(array1,commands1);
	}
	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		for(int i=0;i<commands.length;i++) {
			int start = commands[i][0]-1;
			int end = commands[i][1]-1; 
			int kth = commands[i][2];
			int[] tmp = new int[end-start+1];
			int idx=0;
			for(int j=start;j<=end;j++) {
				tmp[idx]=array[j];
				idx++;
			}
			Arrays.sort(tmp);
			answer[i] = tmp[kth-1];
		}
		return answer;
	}

}
