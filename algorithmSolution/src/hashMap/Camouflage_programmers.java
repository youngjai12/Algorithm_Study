package hashMap;

import java.util.HashMap;

public class Camouflage_programmers {

	public static void main(String[] args) {
		String[][] clothes = {
				{"t1", "upper"},
				{"t2", "upper"}, 
				{"t3", "upper"},
				{"p1", "pants"},
				{"p2", "pants"},
				{"p3", "pants"}, 
				{"s1", "shoes"},
				{"s2", "shoes"}
		};
		String[][] clothes2 = {
				{"t1", "upper"},
				{"t2", "upper"}, 
				{"p1", "pants"}};
		System.out.println(solution(clothes2));
	}
	public static int solution(String[][] clothes) {
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0;i<clothes.length;i++) {
			String[] tmp = clothes[i];
			if(map.get(tmp[1]) == null) {
				map.put(tmp[1], 2);
			}else {
				int val = map.get(tmp[1])+1;
				map.put(tmp[1], val);
			}
		}
		int counts=1;
		for(String kind : map.keySet()) {
			counts = counts*map.get(kind);
		}
		return counts-1;
	}
}
