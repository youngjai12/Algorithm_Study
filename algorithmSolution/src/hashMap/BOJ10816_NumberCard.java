package hashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ10816_NumberCard {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> info = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			int card = Integer.parseInt(st.nextToken());
			if(info.get(card) == null) {
				info.put(card, 1);
			}else {
				int cnt = info.get(card);
				info.put(card,cnt+1);
			}
		}
		int numCount= Integer.parseInt(br.readLine());
	   st = new StringTokenizer(br.readLine());
		
	   for(int i=0;i<numCount;i++) {
			int question =  Integer.parseInt(st.nextToken());
			if(info.get(question)==null) {
				System.out.print(0+" ");
			}else {
				System.out.print(info.get(question)+" ");
			}
		}
	}

}
