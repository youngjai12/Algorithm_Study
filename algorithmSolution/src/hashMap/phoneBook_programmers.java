package hashMap;

public class phoneBook_programmers {

	public static void main(String[] args) {
		String[] pbook = {"119", "97674223", "21195524421"};
		String[] pbook2 = {"123", "465", "781239"};
		String[] pbook3 = {"12","123","1235","567","88"};
		
		System.out.println(solution(pbook3));
		
	}
	public static boolean solution(String[] phone_book) {
		boolean answer= true;
		loop1:for(int i=0;i<phone_book.length;i++) {
			String pattern = phone_book[i]; 
			System.out.println("patter: "+pattern);
			for(int j=0;j<phone_book.length;j++) {
				if(i!=j && phone_book[j].matches(pattern+".*")) {
					answer=false; 
					break loop1;
				}
			}
		}
		return answer;
	}
	
}
