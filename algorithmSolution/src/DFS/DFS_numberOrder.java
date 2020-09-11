package DFS;

import java.util.ArrayList;
import java.util.Stack;

public class DFS_numberOrder {

	public static void main(String[] args) {
		int[] target = {4,5,6,7,8};
		boolean[] visit = new boolean[target.length];
		ArrayList<Integer> answer = new ArrayList<>();
		dfs(0, 0, target, visit, answer);
		
	}
	
	public static void dfs(int count, int idx, int[] target, boolean[] visit, ArrayList answer) {
		if(count ==3) {
			for(int i=0;i<answer.size();i++) {
				System.out.print(answer.get(i)+" ");
			}
			System.out.println();
		}else {
			for(int i=idx; i<target.length;i++) {
				if(!visit[i]) {
					visit[i]=true;
					answer.add(target[i]);
					dfs(count+1, i, target, visit, answer);
					visit[i]=false;
					answer.remove(answer.size()-1);
				}
			}
		}
	}

}
