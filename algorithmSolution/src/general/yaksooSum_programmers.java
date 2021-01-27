package general;

public class yaksooSum_programmers {

	public static void main(String[] args) {
		System.out.println(solution(8));
	}
	public static int solution(int n) {
		int sum=1+n;
		if(n==0) sum=0;
		else if(n==1 || n==2) sum=n;
		else {
			for(int i=2;i<=n/2;i++) {
				if(n%i==0) sum+=i;
			}
		}
		return sum;
	}

}
