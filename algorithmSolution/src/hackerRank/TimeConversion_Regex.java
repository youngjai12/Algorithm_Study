package hackerRank;

import java.util.Scanner;

public class TimeConversion_Regex {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String target = sc.nextLine();
		String[] splitted = target.substring(0, target.length()-2).split(":");
		
		if(target.substring(target.length()-2).equals("PM")) {
			int hour = Integer.parseInt(splitted[0]);
			if(hour != 12) {
				System.out.println((hour+12)+":"+splitted[1]+":"+splitted[2]);
			}else {
				System.out.println((hour)+":"+splitted[1]+":"+splitted[2]);
			}
		}else {
			int hour = Integer.parseInt(splitted[0]);
			if(hour == 12 ) {
				System.out.println("00:"+splitted[1]+":"+splitted[2]);		
			}else {
				System.out.println(splitted[0]+":"+splitted[1]+":"+splitted[2]);
			}
		}
	}

}
