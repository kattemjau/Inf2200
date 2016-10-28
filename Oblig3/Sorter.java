import java.util.ArrayList;
import java.util.HashMap;

class Sorter{
	private char needle[];
	private char haystack[];
	private HashMap<Character, Integer> pri = new HashMap<>();

	Sorter(String needle, String haystack){
		char temp[]=needle.toCharArray();
		char temp2[]=haystack.toCharArray();
		this.needle=temp;
		this.haystack=temp2;		
		settHopp();
	}

	void settHopp(){
		for (int i=0;i<needle.length; i++) {
		//	System.out.println("i: " + i + " " + needle[i]);
			
			if(needle[i]=='_'){
			//	System.out.println("___");
			}else{
				if(!pri.containsKey(needle[i]) && i==needle.length-1){
					//if 
					pri.put(needle[i], needle.length);
				//	System.out.println( needle[i] + " " + needle.length);
				}
				else{
					if(pri.containsKey(needle[i])){
						pri.remove(needle[i]);
						pri.put(needle[i], needle.length-i-1);												
					}else{
						pri.put(needle[i], needle.length-i-1);
					}

				//	System.out.println(needle[i] + " " + (needle.length-i-1));
				}
			} 
		}
		printKeys();	
	}

	void printKeys(){
		for (Character e: pri.keySet()) {
			System.out.println("needle: " + e + " " + pri.get(e));
		}	
		/*for (int i=0; i<needle.length; i++ ) {
			System.out.println("needle : " + needle[i]);
		} */
	}


}
