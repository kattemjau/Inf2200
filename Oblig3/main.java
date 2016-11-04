import java.io.File;
import java.util.Scanner;


class main{
	public static void main(String[] args) {
		System.out.println("testing program:");
		Sorter test1 = new Sorter("g_c", "gacogkco");
		Sorter test2 = new Sorter("ac_o", "akksaokaackookao");
		System.out.println("Testing ferdig:");
		System.out.println();

		if(args[0] == null){
			System.out.println("usage: java main <filnavn1> <filnavn2>");
			System.exit(0);
		}

		String needle, haystack;
		try{
			File fil = new File(args[0]);
			File hay = new File(args[1]);
			Scanner inn= new Scanner(fil);
			Scanner n = new Scanner(hay);
			needle=inn.nextLine();

			while (n.hasNextLine()) {
				haystack=n.nextLine();
				Sorter in = new Sorter(needle, haystack);
			}
		}catch (Exception e){
			System.out.println("Cant read file");
		}



	}

}
