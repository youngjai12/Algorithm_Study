package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BOJ1181_wordSort {
	private static class Word implements Comparable<Word>{
		String word;
		int length;
		public Word(String word) {
			this.word = word;
			this.length = word.length();
		}
		@Override
		public int compareTo(Word target) {
			if(this.length < target.length) return -1;
			else if(length > target.length) return 1;
			else {
				if(strCompare(word, target.word)) return -1;
				else return 1;
			}
		}
	}
	public static boolean strCompare(String s1, String s2) {
		boolean answer = false;
		for(int i=0;i<s1.length();i++) {
			if(s1.charAt(i) < s2.charAt(i)) {
				answer=true;
				break;
			}else if(s1.charAt(i)>s2.charAt(i)) {
				break;
			}
		}
		return answer;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int wordCount = sc.nextInt();
		ArrayList<Word> wordList = new ArrayList<>();
		HashMap<String, Boolean> visit = new HashMap<>();
		for(int i=0;i<wordCount;i++) {
			Word curWord = new Word(sc.next());
			if(visit.get(curWord.word) == null) {
				wordList.add(curWord);
				visit.put(curWord.word, true);
			}
		}
		Collections.sort(wordList);
		for(int i=0;i<wordList.size();i++) {
			System.out.println(wordList.get(i).word);
		}
	}
}
