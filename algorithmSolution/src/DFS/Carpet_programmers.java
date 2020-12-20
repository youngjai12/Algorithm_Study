package DFS;

public class Carpet_programmers {

	public static void main(String[] args) {
		int[] asd = solution(10,2);
		System.out.println(asd[0]+" "+asd[1]);
	}
	public static int[] solution(int brown, int yellow) {
		int i=1;
		int[] answer = new int[2];
		if(yellow==1) {
			answer[0]=3;answer[1]=3;
		}else {
		while(i <= yellow/2) {
			if(i > yellow/2) break;
			if(yellow % i ==0) {
				if(calculate(i, yellow/i, brown)) {
					if(i+2 >= yellow/i+2) {
						answer[0] = i+2;
						answer[1] = yellow/i+2;
					}else {
						answer[0] = yellow/i+2;
						answer[1] = i+2;
					}
				}
			}
			i++;
		}
		}
		return answer;
	}
	public static boolean calculate(int a, int b, int brown) {
		return brown == 2*(a+b)+4;
	}

}
