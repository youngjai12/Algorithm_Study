package general;

public class CompetitionMeet_prog {
	
	public static void main(String[] args) {
		System.out.println(solution(16,9, 12));
		//System.out.println(solution(8,4, 7));
		
	}
	public static void solution(int n, int a, int b) {
		//int answer = Math.max(find(a,1,n), find(b,1,n));
		
		dfs(a,)
		//return answer;
	}
	public static void dfs(int a, int b, int count) {
		if(Math.abs(a-b) <=1) {
			System.out.println(count);
		}else {
			dfs(a/2, b/2, count+1);
		}
	}
	public static int find(int target, int start, int end) {
		int mid = (start+end)/2;
		if(target <=mid) {
			if(mid==target) {
				return 1;
			}else {
				return find(target , start, mid)+1;
			}
		}else {
			return find(target , mid+1, end)+1;
		}
	}
	public 

}
