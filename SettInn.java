import java.lang.Comparable;

class SettInn{
	Node root;

	class Node<T extends Comparable<T>>{
		//hoyere==horyre
		Node hoyere;
		//lavere==venstre
		Node lavere;
		//slik at man kan gao tilbake
		Node bak;
		//ordet til noden
		T data;

		int antall=1;

		Node(T data){
			this.data=data;
		}
		int compareTo(Node<T> in){
			return this.data.compareTo(in.data);
		}
		void leggTil(){
			antall ++;
		}
	}



	void insert(Node in){
		Node temp = root;

		int k;
		while(true){
			k=temp.compareTo(in);

			if(k == 0){temp.leggTil(); break;}

			else if(k <0){
				if(temp.lavere == null){
					temp.lavere=in;
					in.bak=temp;
					break;
				}
				temp=temp.lavere;
			}
			else if(k>0){
				if(temp.hoyere == null){
					temp.hoyere=in;
					in.bak=temp;
					break;
				}
				temp=temp.hoyere;
			}
		}

	}

	Node search(Node in){
		Node temp = root;

		if(temp==null){
			return null;
		}else if(temp.compareTo(in) > 0){
			return search(temp.lavere);
		}else if(temp.compareTo(in) < 0){
			return search(temp.hoyere);
		}else {
			return temp;
		}

		/* ikke rekursiv
		while(true){
			if(temp.compareTo(in) <0){
				if(temp.lavere != null){
					temp=temp.lavere;
				}else{
					return null;
				}
			}else if(temp.compareTo(in)>0){
				if(temp.hoyere != null){
					temp=temp.hoyere;
				}else{
					return null;
				}
			}else{
				break;
			}
		}
		return temp; */
	}

	void deleate(Node in){
		Node temp = search(in);
		if(temp.compareTo(temp.bak.hoyere)==0){
			temp.bak.hoyere=null;
		}else{
			temp.bak.lavere=null;
		}
		settInnListe(temp);

	}

	void settInnListe(Node in){
		Node temp=in;

		while(true){
			if(temp.hoyere!=null){
				settInnListe(temp.hoyere);
			}else if(temp.lavere!=null){
				temp=temp.lavere;
			}else{break;}

		}

	}

}
