package stringManipulation;


public class Example {

	static String reverseString(String s) {
		
		String result = "";
		
		for(int i = s.length()-1; i>=0; i--){
			result = result + s.charAt(i);
		}
		
		return result;
	}
	
	static String reverseStringDivideAndConquer(String s){
		
		char[] result = new char[s.length()];
		int lastIndex = s.length() - 1;
		
		for(int i=0; i<= s.length()/2; i++){
			result[i] = s.charAt(lastIndex - i);
			result[lastIndex - i] = s.charAt(i);
		}
		
		return new String(result);
	}
	
	static String 

}
