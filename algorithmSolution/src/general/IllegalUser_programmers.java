package general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class IllegalUser_programmers {
	public static HashSet<ArrayList<Integer>> sset; 
	public static void main(String[] args) {
//		String[] user_id =  {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id = {"fr*d*", "abc1**"};
		
		String[] user_id =  {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
		
		
		HashMap<String, ArrayList<Integer>> info = new HashMap<>();
		for(int i=0;i<banned_id.length;i++) {
			for(int j=0;j<user_id.length;j++) {
				if(patternMatch(user_id[j], banned_id[i])) {
					if(info.get(banned_id[i]) == null) {
						ArrayList<Integer> tmp = new ArrayList<>();
						tmp.add(j);
						info.put(banned_id[i],tmp);
					}else {
						if(!info.get(banned_id[i]).contains(j)) info.get(banned_id[i]).add(j);
					}
				}
			}
		}
		sset = new HashSet<>();
		
		ArrayList<Integer> ans = new ArrayList<>();
		
		boolean[] v= new boolean[user_id.length];
		solve(0, user_id, banned_id, info, v, ans);
		System.out.println(sset.size());
	}
	public static void solve(int patIdx, String[] user, String[] banned, HashMap<String, ArrayList<Integer>> info, boolean[] visit, ArrayList<Integer> answer) {
		if(patIdx == banned.length) {
			ArrayList<Integer> ttmp = new ArrayList<>(answer);
			Collections.sort(ttmp);
			sset.add(ttmp);
		}else {
			ArrayList<Integer> tmp = info.get(banned[patIdx]);
			for(int i=0;i<tmp.size();i++) {
				if(!visit[tmp.get(i)]) {
					visit[tmp.get(i)] = true;
					answer.add(tmp.get(i));
					solve(patIdx+1, user, banned, info, visit, answer);
					visit[tmp.get(i)]=false;
					answer.remove(answer.size()-1);
				}
			}
		}
	}
	
	public static boolean patternMatch(String given, String pattern) {
		boolean flag=true;
		if(given.length()!=pattern.length()) {
			flag=false;
		}else {
		for(int i=0;i<pattern.length();i++) {
			if(pattern.charAt(i)!='*' && given.charAt(i) != pattern.charAt(i)) {
				flag=false;
				break;
			}
		}
		}
		return flag;
	}

}
