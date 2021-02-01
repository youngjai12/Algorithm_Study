package general;

import java.util.Arrays;

public class Tuple_programmers_kakaoIntern {
	private static class StrElem implements Comparable<StrElem>{
		int length; 
		String str;
		public StrElem(String given) {
			str = given;
			length = given.length();
		}
		@Override
		public int compareTo(StrElem target) {
			if(target.length > length) return -1;
			else return 1;
		}
	}
	public static void main(String[] args) {
		//String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		//String s = "{{20,111},{111}}";
		String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
		String[] d = s.substring(1, s.length()-2).split("},");
		
		StrElem[] sorted = new StrElem[d.length];
		for(int i=0;i<d.length;i++) {
			sorted[i] = new StrElem(d[i]);
		}
		Arrays.sort(sorted);
		int elemCount = sorted[sorted.length-1].str.substring(1).split(",").length;
		int[] answer = new int[elemCount];
		
		answer[0] = Integer.parseInt(sorted[0].str.substring(1));
		for(int i=1;i<sorted.length;i++) {
			String[] cur = sorted[i].str.substring(1).split(",");
			for(int j=0;j<cur.length;j++) {
				int elem = Integer.parseInt(cur[j]);
				boolean flag=false;
				for(int k=0;k<cur.length-1;k++) {
					if(elem == answer[k]) {
						flag=true;
						break;
					}
				}
				if(!flag) {
					answer[cur.length-1] = elem;
				}
			}
		}
		for(int i=0;i<answer.length;i++) {
			System.out.print(answer[i]+" ");
		}
		System.out.println();
		
	}

}
