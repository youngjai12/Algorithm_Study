package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BridgeTruck_programmers {
	private static class truck{
		int weight, loc;
		public truck(int weight, int loc) {
			this.weight = weight;
			this.loc = loc;
		}
	}
	private static class Truck2{
		int weight,time,loc; 
		public Truck2(int weight, int time, int loc) {
			this.weight = weight; 
			this.time = time;
			this.loc = loc;
		}
	}
	public static void main(String[] args) {
//		int bridge_length = 2; 
//		int weight = 10;
//		int[] truck_weights = {7,4,5,6};
		
		int bridge_length = 2; 
		int weight = 10;
		int[] truck_weights = {1,2,3,1,2,3};
		System.out.println(solution2(truck_weights, weight,bridge_length));
		System.out.println(solution3(bridge_length, weight, truck_weights));
	}
	public static int solution3(int bridge_length, int weight, int[] truck_weights) {
		int time = 1;
		Queue<Truck2> q = new LinkedList<>();
		int curWeight = weight - truck_weights[0];
		int truckCount =1;
		int idx=1;
		q.add(new Truck2(truck_weights[0], 0, 0));
		while(true) {
			if(idx < truck_weights.length && curWeight >= truck_weights[idx] && truckCount <= bridge_length) {
				q.add(new Truck2(truck_weights[idx], time, idx));
				curWeight -= truck_weights[idx];
				truckCount++;
				idx++;
			}
			time++;
			if(time - q.peek().time == bridge_length) {
				Truck2 popout = q.poll();
				if(popout.loc == truck_weights.length-1) break;
				curWeight += popout.weight;
				truckCount--;
				
			}
		}
		return time+1; 
	}
	public static int solution1(int bridge_length, int weight, int[] truck_weights) {
		int curWeight = weight;
		int truckCount=0;
		int totSpend=0;
		for(int i=0;i<truck_weights.length;i++) {
			if(curWeight >= truck_weights[i] || truckCount <= bridge_length) {
				if(i == truck_weights.length-1) totSpend+=(bridge_length + truckCount);
				curWeight -= truck_weights[i];
				truckCount++;
			}else if(curWeight<truck_weights[i] ) {
				if(i == truck_weights.length-1) totSpend+=bridge_length;
				totSpend += (bridge_length + (truckCount-1));
				curWeight = weight;
			}
		}
		return totSpend+1;
	}
	 public static int solution2(int[] tw,int tot_weight,int limit) {
			ArrayList<truck> bridge = new ArrayList<>();
			int idx = 0 ;
			bridge.add(new truck(tw[idx],0));
			int time = 1  ;
			int sum = tw[idx];
			idx=1; 
			while(true) {
				if(bridge.isEmpty() && idx>=tw.length) {
					break;
				}
				for(int i=0;i<bridge.size();i++) {
					truck t = bridge.get(i);
					t.loc = t.loc+1;
				}
				if(bridge.get(0).loc>=limit) {
					truck temp = bridge.remove(0);
					sum = sum-temp.weight;
				}
				if(idx<tw.length) {
					if(sum + tw[idx]<=tot_weight) {
						bridge.add(new truck(tw[idx],0));
	                    sum = sum+tw[idx];
						idx++;
					}
				}
				
				time++;
			}
			return time;
		}

}
