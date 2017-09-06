import java.util.ArrayList;
import java.util.HashMap;

class Sorter{
	private char needle[];
	private char haystack[];
	private HashMap<Character, Integer> pri = new HashMap<>();
	private ArrayList<Integer> los = new ArrayList<>();

	Sorter(String ned, String hay){
		char temp[]=ned.toCharArray();
		char temp2[]=hay.toCharArray();
		this.needle=temp;
		this.haystack=temp2;

		if(needle.length > haystack.length){
			System.out.println("false needle");
			return;
		}if(needle.length == 1){
			// Hvis kun en posisjon i needle bruteforce
			for(int i=0; i<haystack.length;i++){
				if(needle[0]==haystack[i] || needle[0]=='_'){
					los.add(i);

				}
			}
		}else{
			settHopp();
			finnNeedle();
		}
		printLos();
	}


/*
 * Method finnNeedle()
 *
 * Sjekker kombinasjoner av needle i haystack. Hopper antall prioriteringer bortover 
 * da den finner en char som ogsa er i needle.
 * Legger posisjonen til losningen i en ArrayList som printes ut senere.
 *
 *
 */
	void finnNeedle(){
		for (int i=needle.length-1;i<haystack.length; i++) {
			if(needle[needle.length-1] == haystack[i] || needle[needle.length-1]== '_'){
				int count =1;
				for (int k=needle.length-2; k>=0; k--) {
					//sjekker en mulig kombo: '_' er freepass
					if(needle[k] == haystack[i-count] || needle[k]== '_'){
						if(k==0){
							//fant en losning
							los.add(i-count);
						}
					}else{
						//hopper til neste instance av kombinasjon
						if(pri.containsKey(haystack[i])){
							i+=pri.get(haystack[i])-1;
						}
						break;
					}
					count++;
				}

			}else{
				if(pri.containsKey(haystack[i])){
					i+=pri.get(haystack[i])-1;
				}
			}


		}
	}

/* Printer losningene */
	void printLos(){
		for(Integer e: los){
			System.out.println("losningen pa posisjon: " + e);
			for (int i=e; i<needle.length+e; i++) {
				System.out.print(haystack[i]);

			}
			System.out.println(" ");
		}
	}

/* Setter prioritering pa hvor mange plasser programmet skal hoppe */
	void settHopp(){
		for (int i=0;i<needle.length; i++) {
			if(needle[i]=='_'){
			}else{
				if(i==needle.length-1){
					if(!pri.containsKey(needle[i])){
						pri.put(needle[i], i);
					}else{
						pri.put(needle[i], needle.length-i);
					}
				}
				else{
					pri.put(needle[i], needle.length-i-1);
				}
			}
		}
	}
}
