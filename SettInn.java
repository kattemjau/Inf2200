
class SettInn<T extends Comparable<T>>{
	Node root;

	class Node{
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

		void leggTil(){
			antall ++;
		}
	}



	void insert(T data){
		if(data==null){
			return;
		}

		Node temp = root;

		Node in = new Node(data);

		int k;
		while(true){
			k=temp.data.compareTo(in.data);

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
		}else if(temp.data.compareTo(in.data) > 0){
			return search(temp.lavere);
		}else if(temp.data.compareTo(in.data) < 0){
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
		if(temp.data.compareTo(temp.bak.hoyere.data)==0){
			temp.bak.hoyere=null;
		}else{
			temp.bak.lavere=null;
		}
		settInnListe(temp);

	}
	/*	start p rekursiv metode
	if(in==null){
		return null;
	}

		if(data.compareTo(in.data) < 0){
			temp.bak.lavere=deleate(temp.lavere);
		}else if(data.compareTo(in.data) > 0){
			temp.bak.hoyere= deleate(temp.hoyere);
		}else {
			if(in.lavere==null){
				in = in.hoyere;
			}else if(n.right == null){
				in = in.lavere;
			}
			else {
				in.antall--;
			}
		}
		return in;


	}


		} */

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
