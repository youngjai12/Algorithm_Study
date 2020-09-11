package unionFind;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ4195_friendNetwork {

	private static class RootNode{
		int nodeNum, childCount;
		public RootNode(int nodeNum, int count) {
			this.nodeNum = nodeNum;
			childCount = count;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int qCount = sc.nextInt();
		for(int q=0;q<qCount;q++) {
			int friendRelationCount = sc.nextInt();
			HashMap<String,Integer> friendIndex = new HashMap<String,Integer>();
			int idx=1;
			RootNode[] rootInfo = new RootNode[2*friendRelationCount];
			for(int i=0;i<friendRelationCount;i++) {
				String friend1 = sc.next();
				String friend2 = sc.next();
				if(friendIndex.get(friend1) == null) {
					friendIndex.put(friend1, idx);
					RootNode temp = new RootNode(idx, 1);
					rootInfo[idx]=temp;
					idx++;
				}
				if(friendIndex.get(friend2) == null) {
					friendIndex.put(friend2, idx);
					RootNode temp = new RootNode(idx, 1);
					rootInfo[idx]=temp;
					idx++;
				}
				int friend1Idx = friendIndex.get(friend1);
				int friend2Idx = friendIndex.get(friend2);
				
				if(find(rootInfo[friend1Idx], rootInfo) != find(rootInfo[friend2Idx], rootInfo)){
					union(rootInfo[friend1Idx], rootInfo[friend2Idx], rootInfo);
					RootNode answer = find(rootInfo[friend1Idx], rootInfo);
					System.out.println(answer.childCount);
				}else {
					RootNode answer = find(rootInfo[friend1Idx], rootInfo);
					System.out.println(answer.childCount);
				}
			}
		}
	}
	public static void union(RootNode x, RootNode y, RootNode[] root) {
		RootNode rootx = find(x, root);
		RootNode rooty = find(y, root);
		
		if(rootx.nodeNum != rooty.nodeNum) {
			if(rootx.nodeNum < rooty.nodeNum) {
				rootx.childCount +=rooty.childCount;
				root[rooty.nodeNum] = rootx;
			}else {
				rooty.childCount += rootx.childCount;
				root[rootx.nodeNum] = rooty;
			}
		}
	}
	public static RootNode find(RootNode x, RootNode[] root) {
		if(root[x.nodeNum].nodeNum == x.nodeNum) return x;
		else {
			RootNode parent = root[x.nodeNum];
			root[x.nodeNum] = find(parent, root);
			return root[x.nodeNum];
		}
	}

}
