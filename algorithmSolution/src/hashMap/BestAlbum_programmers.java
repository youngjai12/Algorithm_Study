package hashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BestAlbum_programmers {
	private static class Genre implements Comparable<Genre>{
		String name;
		int count;
		public Genre(String name, int count) {
			this.name = name; 
			this.count = count;
		}
		@Override // 내림차순 
		public int compareTo(Genre target) {
			if(count > target.count) {
				return -1;
			}else return 1;	
		}
	}
	private static class Song implements Comparable<Song>{
		String genre; 
		int idx, count;
		public Song(int idx, String genre, int count) {
			this.idx = idx;
			this.genre = genre;
			this.count = count;
		}
		@Override
		public int compareTo(Song target) {
			if(count > target.count) return -1;
			else if(count < target.count) return 1;
			else {
				if(idx < target.idx) return -1;
				else return 1;
			}
		}
	}
	public static void main(String[] args) {
		String[] genres = {"classic", "pop"};
		int[] plays = {500, 600};
		printArr(solution(genres, plays));
		
	}
	public static int[] solution(String[] genres, int[] plays) {
		HashMap<String, ArrayList<Song>> genreInfo = new HashMap<>();
		for(int i=0;i<genres.length;i++) {
			if(genreInfo.get(genres[i]) == null) {
				ArrayList<Song> tmp = new ArrayList<>();
				tmp.add(new Song(i, genres[i], plays[i]));
				genreInfo.put(genres[i], tmp);
			}else {
				genreInfo.get(genres[i]).add(new Song(i, genres[i], plays[i]));
			}
		}
		Genre[] tmpGenre = new Genre[genreInfo.keySet().size()];
		int tmpIdx =0; 
		int answerCount=0;
		for(String gen : genreInfo.keySet()) {
			int genreCount=0;
			if(genreInfo.get(gen).size() > 2) {
				answerCount+=2;
			}else answerCount += genreInfo.get(gen).size();
			for(int i=0;i<genreInfo.get(gen).size();i++) {
				genreCount+=genreInfo.get(gen).get(i).count;
			}
			tmpGenre[tmpIdx] = new Genre(gen, genreCount);
			tmpIdx++;
		}
		Arrays.sort(tmpGenre);
		
		int[] answer = new int[answerCount];
		int tmpidx=0;
		for(int i=0;i<tmpGenre.length;i++) {
			Collections.sort(genreInfo.get(tmpGenre[i].name));
			for(int j=0;j<Math.min(genreInfo.get(tmpGenre[i].name).size(), 2);j++) {
				answer[tmpidx] = genreInfo.get(tmpGenre[i].name).get(j).idx;
				tmpidx++;
			}
		}
		return answer;
	}
	public static void printArr(int[] t) {
		for(int i=0;i<t.length;i++) {
			System.out.print(t[i]+" ");
		}
		System.out.println();
	}
	public static void printArr(Genre[] t) {
		for(int i=0;i<t.length;i++) {
			System.out.println(t[i].name+":"+t[i].count);
		}
		System.out.println();
	}
	public static void printMap(HashMap<String, ArrayList<Song>> t) {
		for(String tt : t.keySet()) {
			System.out.println(tt+" => ");
			for(int i=0;i<t.get(tt).size();i++) {
				System.out.println(t.get(tt).get(i).genre+"-"+t.get(tt).get(i).idx+"-"+t.get(tt).get(i).count);
			}
			System.out.println();
		}
	}
}
