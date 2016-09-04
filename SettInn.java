class SettInn{
	Node root;

	class Node<String extends Comparable<String>>{
		//hoyere==horyre
		Node hoyere;
		//lavere==venstre
		Node lavere;
		//slik at man kan gå tilbake
		Node bak;
		//ordet til noden
		String data;

		Node(String data){
			this.data=data;
		}
		int compareTo(Node in){
			return this.data.compareTo(in);
		}
	}



	void insert(Node in){
		Node temp = root;
		while(true){
			if(temp.compareTo(in) <0){
				temp=temp.lavere;
			}
			else if(temp.compareTo(in)>0){
				temp=temp.hoyere;
			}
		}
		
	}
	Node search(String in){
		Node temp = root;
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
				continue;
			}
		}
		return temp;
	}
	void deleate(String in){
		Node temp = search(in);
		if(temp.compareTo(temp.bak.hoyere.data)==0){
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
			}else{continue;}

		}

	}

}


