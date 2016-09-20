import java.util.ArrayList;

class SearchInterface{
	SettInn<String> tree;

	SearchInterface(SettInn<String> tree){
		this.tree=tree;
	}

	ArrayList<String> searchSimmular(String data){
		ArrayList<String> liste = new ArrayList<>();

		char[] et = data.toCharArray();

		int tor = data.length();


		//endre plass pa bokstaver
		for (int i=0; i < (tor - 1); i++) {
			char[] lik = et;
			lik[i] = et[i+1];
			lik[i + 1] = et[1];
			String idar = new String(lik);
			if(tree.search(idar) != null){
				liste.add(idar);
			}
		}

		//bytt ut bokstaver
		for (int i=0; i < tor; i++) {
			for (int k=0;k < 26; k++) {
				char[] lik = et;
				lik[i]=((char) ('a' + k));

				String idar = new String(lik);
				if(tree.search(idar) != null){
					liste.add(idar);
				}

			}
		}
		//en bokstav er fjernet funker ikke
		for (int i=0; i < tor; i++) {
			String lik = "";
			for(int k=0; k < tor; k++){
				if(k!=i){
					lik += et[k];
				}
			}

			if(tree.search(lik) != null){
				liste.add(lik);
			}


		}
		//en bokstav lagt til funker ikkje ved plass 0
		for (int i=0; i < tor; i++) {
			for (int k=0;k < 26; k++) {
				char[] lik = et;
				lik[i]=((char) ('a' + k));

				String idar = new String(lik);
				if(tree.search(idar) != null){
					liste.add(idar);
				}
			}
		}
		return liste;
	}
}
