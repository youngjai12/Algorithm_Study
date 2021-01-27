package hackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Non_divisible_Subset {
	public static int arrCount=0;
	public static int[] given;
	public static int k, maxCount ;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arrCount = sc.nextInt();
		k = sc.nextInt();
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<arrCount;i++) {
			int val = sc.nextInt()%k;
			if(map.get(val) == null) {
				map.put(val, 1);
			}else {
				int cnt = map.get(val);
				map.put(val, cnt+1);
			}
		}
		ArrayList<Integer> keyset = new ArrayList<>(map.keySet());
		ArrayList<Integer> finalKeys = new ArrayList<>();
		
		for(int i=0;i<keyset.size();i++) {
			int curKey = keyset.get(i);
			if(curKey <=k/2) {
				int tmp = map.get(curKey);
				if(map.get(k-curKey)!=null) {
					tmp = Math.max(tmp, map.get(k-curKey));
				}
				map.put(curKey,tmp);
				finalKeys.add(curKey);
			}else if(map.get(k-curKey)==null) {
				map.put(k-curKey, map.get(curKey));
				finalKeys.add(k-curKey);
			}
			if(curKey == 0 || curKey * 2 == k) {
				map.put(curKey,1);
			}
		}
		System.out.println(map);
		System.out.println(finalKeys);
//		for(int i=0;i<finalKeys.size();i++) {
//			ArrayList<Integer> answer = new ArrayList<>();
//			answer.add(finalKeys.get(i));
//			dfs(i, finalKeys, answer, map);
//		}
		for(int i=0;i<finalKeys.size();i++) {
			maxCount+=map.get(finalKeys.get(i));
		}
		System.out.println(maxCount);
	}
	public static int getCount( ArrayList<Integer> keySet, ArrayList<Integer> candidate , HashMap<Integer, Integer> map) {
		int answer=0;
		for(int i=0;i<candidate.size();i++) {
			answer+=map.get(keySet.get(i));
		}
		return answer;
	}
	public static void dfs(int idx, ArrayList<Integer> keySet, ArrayList<Integer> candidate, HashMap<Integer, Integer> map) {
		if(idx >= keySet.size()) {
			int ans = getCount(keySet, candidate, map);
			if(maxCount < ans) {
				maxCount = ans;
			}
		}else {
			for(int i=idx+1;i<keySet.size();i++) {
				int val = keySet.get(i);
				boolean flag=true;
				for(int j=0;j<candidate.size();j++) {
					if((val + candidate.get(j)%k) == 0) {
						flag=false;
						break;
					}
				}
				if(flag) {
					candidate.add(val);
					dfs(i, keySet, candidate, map);
					candidate.remove(candidate.size()-1);
				}
			}
			int ans = getCount(keySet, candidate, map);
			if(maxCount < ans) {
				maxCount = ans;
			}
		}
	}

}
