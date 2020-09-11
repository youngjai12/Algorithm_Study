package kakao_recruitment_2020;

import java.util.ArrayList;
import java.util.Collections;

public class ColumnBeam {
	public static int n ;
	private static class StructInfo{
		int x,y,struct;
		public StructInfo(int x, int y, int struct) {
			this.x = x;
			this.y = y;
			this.struct = struct;
		}
		
	}
	public static int[][] ex1 = {
			{0,0,0,1},
			{0,1,1,1},
			{1,1,0,1},
			{1,2,1,1},
			{1,1,0,0},
			{0,1,1,0},
			{0,0,0,0}
	};
	public static int[][] ex2 = {
			{0,0,0,1},{2,0,0,1}, {4,0,0,1}, {0,1,1,1},
			{1,1,1,1}, {2,1,1,1}, {3,1,1,1}, {2,0,0,0},
			{1,1,1,0}, {2,2,0,1}
	};
	public static int[][] ex3 = {
			{1,0,0,1},{1,1,1,1}, {2,1,1,1}, {3,0,0,1},
			{2,1,0,1}
	};
	public static void main(String[] args) {
		n = 5; 
		int[][] buildFrame = ex3; 
		int seq = buildFrame.length;
		boolean[][] colmap = new boolean[n+4][n+2];
		boolean[][] bomap = new boolean[n+4][n+2];
		
		int count=0;
		for(int i=0;i<seq;i++) {
			int x = buildFrame[i][0];
			int y = buildFrame[i][1];
			int struct = buildFrame[i][2];
			int inst = buildFrame[i][3];
			
			if(inst == 1) {
			if(struct == 0) {
				if(colInstCheck(x,y,colmap, bomap)) {
					System.out.println("("+x+","+y+") column install");
					count++;
					colmap[x+2][y+1]=true;
				}
			} else {
				if(boInstCheck(x,y,colmap,bomap)) {
					System.out.println("("+x+","+y+") bo install");
					count++;
					bomap[x+2][y+1]=true;
				}
			}
			} else {
				if(struct == 0) {
					if(colDelCheck(x,y,colmap,bomap)) {
						count--;
						System.out.println("("+x+","+y+") col delete");
						colmap[x+2][y+1] = false;
					}
				} else {
					if(boDelCheck(x,y,colmap,bomap)) {
						count--;
						System.out.println("("+x+","+y+") bo delete");
						bomap[x+2][y+1] = false;
					}
				}
			}
		
		}
		int[][] answer = new int[count][3];
		int idx=0;
		for(int i=0;i<=n+2;i++) {
			for(int j=0;j<=n+1;j++) {
				if(colmap[i][j]) answer[idx++] = new int[] {i-2,j-1,0};
				if(bomap[i][j]) answer[idx++] = new int[] {i-2,j-1,1};
				
			}
		}
		for(int i=0;i<count;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(answer[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	public static boolean colDelCheck(int oldx, int oldy, boolean[][] colmap, boolean[][] bomap) {
		boolean flag = true; 
		int x = oldx+2; int y = oldy+1;
		if(bomap[x][y+1]) {
			if(!colmap[x+1][y] && (!bomap[x-1][y+1] || !bomap[x+1][y+1])) flag=false;
		}
		if(bomap[x-1][y+1]) {
			if(!colmap[x-1][y] && (!bomap[x-2][y+1] || !bomap[x][y+1])) flag=false;
		}
		if(!bomap[x][y+1] && !bomap[x-1][y+1] && colmap[x][y+1]) flag=false;
		
		return flag;
	}
	public static boolean boDelCheck(int oldx, int oldy, boolean[][] colmap, boolean[][] bomap) {
		boolean flag = true; 
		int x = oldx+2; int y = oldy+1;
		
		if(colmap[x][y]) {
			if(!bomap[x-1][y] && colmap[x][y-1]) flag=false;
		}
		if(colmap[x+1][y]) {
			if(!bomap[x+1][y] && !bomap[x+1][y-1]) flag=false;
		}
		if(bomap[x-1][y] && !colmap[x-1][y-1]) flag=false;
		if(bomap[x+1][y] && !colmap[x+2][y-1]) flag=false;
		
		return flag;
	}
	public static boolean colInstCheck(int oldx, int oldy, boolean[][] colmap, boolean[][] bomap) {
		boolean flag = false;
		int x = oldx+2; int y = oldy+1;
		if(y==1) { // 땅이라면..
			flag = true;
		} else if(y >1) {
			if(colmap[x][y-1]) {
				flag=true;
			}else {
				if(bomap[x-1][y] || bomap[x][y]) flag=true;
			}
		}
		return flag;
	}
	public static boolean boInstCheck(int oldx, int oldy, boolean[][] colmap, boolean[][] bomap) {
		boolean flag = false; 
		int x = oldx+2; int y = oldy+1;
		if(colmap[x][y-1] || colmap[x+1][y-1]) {
			flag=true;
		} else {
			if(bomap[x-1][y] && bomap[x+1][y]) flag=true;
		}
		return flag;
	}

}
