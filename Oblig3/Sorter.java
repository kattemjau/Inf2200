import java.util.ArrayList;
import java.util.HashMap;

class Sorter{
	private char needle[];
	private char haystack[];
	private HashMap<Character, Integer> pri = new HashMap<>();
	private ArrayList<Integer> los = new ArrayList<>();

	Sorter(String needle, String haystack){
		char temp[]=needle.toCharArray();
		char temp2[]=haystack.toCharArray();
		this.needle=temp;
		this.haystack=temp2;		
		settHopp();
		finnNeedle();
	}
	void finnNeedle(){
		if(needle.length == 1 || needle.length > haystack.length){
			System.out.println("false needle");
			return;
		}
		//int maxHopp = haystack.length - needle.length;
		for (int i=needle.length;i<haystack.length; i++) {
			
			if(needle[needle.length-1] == haystack[i]){
				int count =1;
				System.out.println("test");
				for (int k=needle.length-2; k>=0; k--) {
				//sjekker en mulig kombo: '_' er freepass
					if(needle[k] == haystack[i-count] || needle[k]== '_'){
						if(k==0){
							//fant en mulighet
							los.add(i-count);
							System.out.println("LOSNING: haystack pa plass " + (i-count));
						}
					}else{
						//hopper til neste kombo
						i+=pri.get(haystack[i])-1;
						System.out.println("BREAK");
						break;
					}
					count++;
				}
	
			}else{
				//nullpointer
				if(pri.containsKey(haystack[i])){
					i+=pri.get(haystack[i])-1;
				}else{
					i+=needle.length-2;
				}
			}


		}
		printLos();
	}

	void printLos(){
		for(Integer e: los){
			System.out.println("losningen er: " + e);
			for (int i=e; i<needle.length+e; i++) {
				System.out.print(haystack[i]);
				
			}
			System.out.println(" ");
		}
	}

	void settHopp(){
		for (int i=0;i<needle.length; i++) {
		//	System.out.println("i: " + i + " " + needle[i]);
			
			if(needle[i]=='_'){
			//	System.out.println("___");
			}else{
				if(!pri.containsKey(needle[i]) && i==needle.length-1){
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
