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
	
	/**
	 * 
	 * @param String s
	 * @return boolean
	 * 
	 * LAVVAL
	 * 
	 * lastIndex = 4
	 * 
	 * s.length()/2 = 3
	 * 
	 * 0: L != L -> false 
	 * 1: A != A -> false
	 * 2: V != V -> false
	 * 
	 * result: true
	 */
	static boolean anagramChecker(String s)
	{
		s = s.toLowerCase();
		
		int lastIndex = s.length() - 1;
		
		for(int i=0; i<s.length()/2; i++)
		{
			System.out.println("comparing: " + s.charAt(i) + " AND " + s.charAt(lastIndex - i));
			if(s.charAt(i) != s.charAt(lastIndex - i))
			{
				return false;
			}
		}
		return true;
	}
}
