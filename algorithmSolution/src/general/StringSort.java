package general;

import java.util.Arrays;

public class StringSort {
	private static class Word implements Comparable<Word>{
		int idx; 
		String word; 
		public Word(int idx, String word) {
			this.idx = idx;
			this.word = word;
		}
		@Override
		public int compareTo(Word arg) {
			char thisLetter = word.charAt(this.idx);
			char targetLetter = arg.word.charAt(arg.idx);
			if(thisLetter > targetLetter) {
				return 1;
			}else if(thisLetter < targetLetter) {
				return -1;
			}else {
				return this.word.compareTo(arg.word);
			}
			
		}
	}
	public static void main(String[] args) {
		String[] sample = {"car", "bed", "sun"};
		String[] sample2 = {"abcd", "abce", "cdx"};
		
		solution(sample2, 2);
		}
	public static void printArr(String[] ar) {
		for(int i=0;i<ar.length;i++) {
			System.out.print(ar[i]+" ");
		}
		System.out.println();
	}
	public static void printArr(Word[] ar) {
		for(int i=0;i<ar.length;i++) {
			System.out.print(ar[i].word+" ");
		}
		System.out.println();
	}
	public static String[] solution(String[] strings, int n) {
		Word[] tmp = new Word[strings.length];
		for(int i=0;i<strings.length;i++) {
			tmp[i] = new Word(n, strings[i]);
		}
		Arrays.sort(tmp);
		String[] answer = new String[tmp.length];
		for(int i=0;i<tmp.length;i++) {
			answer[i] = tmp[i].word;
		}
		return answer;
	}

}
