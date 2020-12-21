package DFS;

import java.util.Arrays;

public class WordConversion_programmers {
	public static boolean reached=false;
	public static int min;
	public static void main(String[] args) {
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution("hit", "cog", words));
	}
	public static int solution(String begin, String target, String[] words) {
		if(!Arrays.asList(words).contains(target)){
			return 0;
		}else {
			boolean[] v = new boolean[words.length];
			String tmp_answer="";
			min=words.length+1000;
			dfs(0,begin, target, v, words, tmp_answer);
			return min;
		}	
	}
	public static void dfs(int count, String start, String target, boolean[] visit, String[] words, String acc) {
		if(start.equals(target)) {
			if(min>count) min = count;
		}else {
			for(int i=0;i<words.length;i++) {
				if(!visit[i] && check(start, words[i])) {
					visit[i]=true;
					dfs(count+1, words[i], target, visit, words, acc+" "+start);
					visit[i]=false;
				}
			}
		}
	}
	public static boolean check(String word1, String word2) {
		int diff=0;
		for(int i=0;i<word1.length();i++){
			if(word1.charAt(i)!=word2.charAt(i)) diff++;
		}
		if(diff==1) return true;
		else return false;
	}

}
