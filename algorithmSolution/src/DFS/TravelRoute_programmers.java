package DFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class TravelRoute_programmers {
	public static ArrayList<String> global;
	public static boolean reached=false;
	public static int tc;
	private static class Destination implements Comparable<Destination>{
		String name; boolean visit;
		public Destination(String name, boolean visit){
			this.name = name;
			this.visit = visit;
		}
		@Override
		public int compareTo(Destination o) {
			return name.compareTo(o.name);
		}
	}
	public static void main(String[] args) {
		String[][] cities = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		String[][] cities2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"},
				{"ATL","SFO"}};
		String[][] cities3 = {{"ICN", "A"}, {"ICN", "B"}, {"ICN", "C"},
									{"A", "C"},{"B", "D"},{"B", "K"},
									{"C", "Q"}, {"K", "A"}};
		String[][] cities4 = {{"A", "C"}, {"A", "B"}, {"C", "K"}, {"K", "A"}};
		
		String[] d = solution(cities4);
		for(int i=0;i<d.length;i++) {
			System.out.print(d[i]+"=>");
		}
		System.out.println();
	}
	public static String[] solution(String[][] tickets) {
		HashMap<String, ArrayList<Destination>> dest = new HashMap<>();
		for(int i=0;i<tickets.length;i++) {
			if(dest.get(tickets[i][0]) == null) {
				ArrayList<Destination> tmp = new ArrayList<>();
				tmp.add(new Destination(tickets[i][1],false));
				dest.put(tickets[i][0], tmp);
			}else {
				dest.get(tickets[i][0]).add(new Destination(tickets[i][1], false));
			}
		}
		for(String key : dest.keySet()) {
			Collections.sort(dest.get(key));
		}
		tc = tickets.length;
		Stack<String>answer=new Stack<>();
		global = new ArrayList<>();
		answer.push("A");
		dfs(0,"A", dest, answer);
		String[] ans = new String[global.size()];
		for(int i=0;i<global.size();i++){
			ans[i] = global.get(i);
		}
		return ans;
	}
	public static void dfs(int ticketCount, String start, HashMap<String, ArrayList<Destination>> d, Stack<String> answer) {
		if(ticketCount == tc-1) {
			while(!answer.isEmpty()) {
				global.add(answer.pop());
			}
		}
		else {
		if(d.get(start) != null && !reached){
			for(int i=0;i<d.get(start).size();i++) {
				if(!d.get(start).get(i).visit) {
					answer.push(d.get(start).get(i).name);
					d.get(start).get(i).visit=true;
					dfs(ticketCount+1, d.get(start).get(i).name, d, answer);
					d.get(start).get(i).visit=false;
					answer.pop();
				}
			}
			
		}
		}
	}

}
