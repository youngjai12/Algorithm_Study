package hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LilyHomework {

	public static void sol2(int[] ar) {
		int max = ar[0];
		int min = ar[0];
		int maxCount=0;
		int minCount=0;
		for(int i=1;i<ar.length;i++) {
			if(max > ar[i]) {
				maxCount++;
			}else {
				max = ar[i];
			}
			if(min >= ar[i]) {
				min =ar[i];
			}else{
				minCount++;
			}
		}
		System.out.println(Math.min(minCount, maxCount));
	}
	
	public static void solution2(ArrayList<Integer> ar, ArrayList<Integer> ar2) {
		int size = ar.size();
	
		Map<Integer, Integer> asc = new HashMap<>();
		Map<Integer, Integer> desc = new HashMap<>();
		ArrayList<Integer> sorted = new ArrayList<Integer>(ar);
		ArrayList<Integer> reverse = new ArrayList<Integer>(ar2);
		Collections.sort(sorted);
		Collections.sort(reverse, Collections.reverseOrder());
		
		for(int i=0;i<size;i++) {
			asc.put(ar.get(i), i);
			desc.put(ar.get(i), i);
		}
		int ascCount=0;
		for(int i=0;i<size;i++) {
			if(sorted.get(i)!=ar.get(i)) {
				int toSwapPos = asc.get(sorted.get(i));
				int toSwapNum = ar.get(i);
				ar.set(i, sorted.get(i));
				ar.set(toSwapPos, toSwapNum);
				asc.replace(sorted.get(i), i);
				asc.replace(toSwapNum, toSwapPos);
				ascCount++;
			}
		}
		int descCount=0;
		for(int i=0;i<size;i++) {
			if(reverse.get(i)!=ar2.get(i)) {
				int toSwapPos = desc.get(reverse.get(i));
				int toSwapNum = ar2.get(i);
				ar2.set(i, reverse.get(i));
				ar2.set(toSwapPos, toSwapNum);
				desc.replace(reverse.get(i), i);
				desc.replace(toSwapNum, toSwapPos);
				descCount++;
			}
		}
		System.out.println(Math.min(ascCount, descCount));
	}
	
	// solution2 : time-out : why? ar.IndexOf 때문인듯. 
	public static void solution(ArrayList<Integer> ar, ArrayList<Integer> ar2) {
		int size = ar.size();
		ArrayList<Integer> sorted = new ArrayList<Integer>(ar);
		ArrayList<Integer> reverse = new ArrayList<Integer>(ar);
	
		Collections.sort(sorted);
		Collections.sort(reverse, Collections.reverseOrder());
		
		int ascCount=0;
		for(int i=0;i<ar.size();i++) {
			if(ar.get(i)!=sorted.get(i)) {
				int tmp = ar.get(i);
				int swapPos = ar.indexOf(sorted.get(i));
				ar.set(i, sorted.get(i));
				ar.set(swapPos, tmp);
				ascCount++;
			}
		}
		int descCount=0;
		for(int i=0;i<ar2.size();i++) {
			if(ar2.get(i)!=reverse.get(i)) {
				int tmp = ar2.get(i);
				int swapPos = ar2.indexOf(reverse.get(i));
				ar2.set(i, reverse.get(i));
				ar2.set(swapPos, tmp);
				descCount++;
			}
		}
		System.out.println(Math.min(descCount, ascCount));
	}
	public static void printArr(ArrayList<Integer> target) {
		for(int i=0;i<target.size();i++) {
			System.out.print(target.get(i)+" ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		ArrayList<Integer> ar = new ArrayList<>();
		ArrayList<Integer> ar2 = new ArrayList<>();
		
		for(int i=0;i<count;i++) {
			int given = sc.nextInt();
			ar.add(given);
			ar2.add(given);
		}
		solution2(ar, ar2);
	}

}
