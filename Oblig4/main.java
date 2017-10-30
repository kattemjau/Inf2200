import java.io.File;
import java.util.Scanner;


class main{
	public static void main(String[] args) {

		System.out.println("testing program:");
		new Sorter("i", "ingiegogo");
		new Sorter("bbabbb", "bbbabbb");
		new Sorter("_ma", "panama");
		new Sorter("ab_cd", "xxabycdxx");
		new Sorter("ab_cd", "");
		new Sorter("", "xxabycdxx");
		new Sorter("_", "xxabycdxx");
		new Sorter("xxabycdxx", "_");
		new Sorter("x_", "xxabycdxx");
		System.out.println("Testing ferdig:");
		System.out.println();

		if(args.length==0){
			System.out.println("usage: java main <filnavn1> <filnavn2>");
			return;
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
