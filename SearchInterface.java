import java.util.ArrayList;

class SearchInterface{
	SettInn<String> tree;

	SearchInterface(SettInn<String> tree){
		this.tree=tree;
	}

	ArrayList<String> searchSimmular(String data){
		ArrayList<String> liste = new ArrayList<>();

		char[] et = data.toCharArray();



		//endre plass pa bokstaver funker
		for (int i=0; i < (data.length() - 1); i++) {
			char[] lik = data.toCharArray();
			lik[i] = et[i+1];
			lik[i + 1] = et[1];
			String idar = new String(lik);
			if(tree.search(idar) != null){
				liste.add(idar);
				//System.out.println("1");
				//System.out.println(idar);
			}
		}

		//bytt ut bokstaver funker
		for (int i=0; i < data.length(); i++) {
			for (int k=0;k < 26; k++) {
				char[] lik = data.toCharArray();
				lik[i]=((char) ('a' + k));

				String idar = new String(lik);
				if(tree.search(idar) != null){
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

			if(tree.search(lik) != null){
				liste.add(lik);
				//System.out.println("3");
				//System.out.println(lik);

			}


		}
		//en bokstav lagt til funker
		for (int i=0; i < data.length(); i++) {
			for (int k=0;k < 26; k++) {
				char[] lik = et;
				lik[i]=((char) ('a' + k));

				String idar = new String(lik);
				if(tree.search(idar) != null){
					liste.add(idar);
					//System.out.println("4");
					//System.out.println(idar);

				}
			}
		}
		return liste;
	}
}
