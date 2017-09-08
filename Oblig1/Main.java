import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

class Main{
	public static void main(String[] args) {
		try{
		File fil = new File("dictionary.txt");
		Scanner in = new Scanner(fil);
		SettInn liste = new SettInn();

		while(in.hasNextLine()){
			String temp = in.nextLine();
			liste.insert(temp); //.toLowerCase()
		}
		//her er hele ordlisten satt inn i et binert tre
		//ui

		String valg=null;
		int velge=0;
		in=new Scanner(System.in);

		ArrayList<String> resultat;

		for (; ; ) {
			if(velge<4){
				if(velge==0){
					valg="achiev";
				}else if(velge==1){
					valg="achiese";
				}else if(velge==2){
					valg="achievee";
				}else if(velge==2){
					valg="ahcieve";
				}
				velge++;
			}else{
				System.out.println("Exit program (q), or search for a word");
				valg=in.nextLine();
				if(valg.equals("q")){
					System.out.println("storrelse pa listen: " + liste.antall);
					liste.printStatistikk();
					System.out.println("exiting program");
					break;
				}
			}

			//search for word
			if(liste.findWord(valg)){
				System.out.println("fant ordet: " + valg);
			}else {
				System.out.println("Fant ikke ordet: " + valg + " Leter etter liknende ord:");
				resultat=liste.findSimmular(valg);
				for(String e: resultat){
					System.out.println(e);
				}
			}
		}


	}catch(Exception e){
		System.out.println("File exception");
		e.printStackTrace();
	}

	}
}

class SettInn<E extends Comparable<E>>{
	//nodeliste for binert tre
	Node root=null;

	int antall =1;

	class Node{
		String data;
		Node left=null;
		Node right=null;
		Node back=null;

		Node(String temp){
			this.data=temp;
			// antall++;
		}


	}
	void insert(String data, Node n){
		if(n==null){
			return;
		}
		if(data.compareTo(n.data)<0){
			if(n.right==null){
				n.right=new Node(data);
			}
			insert(data, n.right);
		}else if(data.compareTo(n.data)>0){
			if(n.left==null){
				n.left=new Node(data);
			}
			insert(data, n.left);
		}
	}

	void insert(String data){
		antall++;
		if(root==null){
			root=new Node(data);
			return;
		}
		insert(data, root);
	}

		int antall(){
		return antall;
	}

	boolean findWord(String ord){
		return find(ord, root);
	}
	boolean find(String in, Node temp){
		if(temp==null){
			return false;
		}

		if(in.compareTo(temp.data) > 0){
			return find(in, temp.left);
		}else if(in.compareTo(temp.data) < 0){
			return find(in, temp.right);
		}else {
			return true;
		}
	}


	ArrayList<String> findSimmular(String data){
		ArrayList<String> liste = new ArrayList<>();

		char[] et = data.toCharArray();

		//endre plass pa bokstaver funker
		for (int i=0; i < (data.length() - 1); i++) {
			char[] lik = data.toCharArray();
			lik[i] = et[i+1];
			lik[i + 1] = et[i];
			String idar = new String(lik);
			if(findWord(idar) != false){
				liste.add(idar);
				//System.out.println("1");
				//System.out.println(idar);
			}
		}

		//bytt ut bokstaver funker
		for (int i=0; i < data.length(); i++) {
			for (char k='a';k <= 'z'; k++) {
				char[] lik = data.toCharArray();
				lik[i]=k;

				String idar = new String(lik);
				if(findWord(idar) != false){
					liste.add(idar);
					//System.out.println("2");
					//System.out.println(idar);

				}

			}
		}
		//en bokstav er fjernet funker
		for (int i=0; i < data.length(); i++) {
			String lik = "";
			for(int k=0; k < data.length(); k++){
				if(k!=i){
					lik += et[k];
				}
			}

			if(findWord(lik) != false){
				liste.add(lik);
				//System.out.println("3");
				//System.out.println(lik);

			}


		}
		//en bokstav lagt til
		for (int i=0; i < (data.length() +1 ); i++) {
			for (char k='a';k <= 'z'; k++) {
				String lik ="";
					for (char o: data.toCharArray()){
						if(lik.length() == i){
							lik+=k;
						}
						lik += o;
						if(lik.length() == data.length() && i == data.length()){
							lik+=k;
						}
					}
					//System.out.println(i + " " + k);
					//System.out.println(lik);
					if(findWord(lik) != false && !liste.contains(lik)){
						liste.add(lik);
					}
				}
			}
		return liste;
	}
	void printStatistikk(){
		//depth of tree
		checkMaxDepth(root, 0);
		//how manny nodes there are for eatch depth of the tree
		System.out.println("Dybde paa treeet");
		System.out.println(treeDepth);
		System.out.println();

		//aphabetically first and last word
		System.out.println("First word");
		System.out.println(lastWord(root));
		System.out.println(" ");

		System.out.println("Last Word");
		System.out.println(firstWord(root));

		//average depth of all nodeliste
		finnGjennomsnitt(root, 0);

		//gjennomsnitt
		int su =1, ant=1;

		for(Integer e: gjennomsnitt){
			ant++;
			su = su + e;
		}
		System.out.println(" ");
		System.out.println("gjennomsnitt");
		System.out.println(su/ant + " Noder");
	}
	// treeDepth(){
	// 	for (int i=0;i< treeDepth+1; i++) {
	// 		System.out.print("plass " + i + ": ");
	// 		System.out.println(nodes(root, i, 0));
	//
	// 	}
	// }
	int treeDepth=0;
	void checkMaxDepth(Node n, int depth){
		if(treeDepth < depth){
			treeDepth=depth;
		}
		if(n.left != null){
			checkMaxDepth(n.left, depth +1 );
		}if(n.right != null){
			checkMaxDepth(n.right, depth +1 );
		}
	}
	String lastWord(Node n){
		if(n.right==null){
			return n.data;
		}return lastWord(n.right);
	}
	String firstWord(Node n){
		if(n.left == null){
			return n.data;
		} return firstWord(n.left);
	}
	ArrayList<Integer> gjennomsnitt = new ArrayList<>();
	void finnGjennomsnitt(Node n, int depth){
		if(n.left==null || n.right==null){
			gjennomsnitt.add(depth);
		}
		if(n.left != null){
			finnGjennomsnitt(n.left, depth +1 );
		}if(n.right != null){
			finnGjennomsnitt(n.right, depth +1 );
		}
	}




}
