import java.util.ArrayList;

class SettInn<T extends Comparable<T>>{
	Node root = null;
	int antall;

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

	int antall(){
		return antall;
	}


	void insert(T data){
		if(data==null){
			return;
		}
		Node in = new Node(data);
		antall++;
		//System.out.println(root);
		if(root==null){
			root = in;
			return;
		}

		Node temp = root;



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

	T search(T in){
		return search(in, root);
	}

	T search(T in, Node temp){
		//if(in==null){
		//	return null;
		//}
		try{
		if(in.compareTo(temp.data) > 0){
			return search(in, temp.lavere);
		}else if(in.compareTo(temp.data) < 0){
			return search(in, temp.hoyere);
		}else {
			return in;
		}
	}catch(NullPointerException e){
		//e.printStackTrace();
		return null;
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


	void deleate(T in){
		Node temp = root;
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

	int treeDepth =0;


	void checkMaxDepth(Node n, int depth){
		if(treeDepth < depth){
			treeDepth=depth;
		}
		if(n.lavere != null){
			checkMaxDepth(n.lavere, depth +1 );
		}if(n.hoyere != null){
			checkMaxDepth(n.hoyere, depth +1 );
		}
	}
	int nodes(Node n, int i, int k){
		if(n==null){
			return 0;
		}if(k==i){
			return 1;
		}
		return nodes(n.lavere, i, k++) + nodes(n.hoyere, i, k++);
	}
	T lastWord(Node n){
		if(n.hoyere==null){
			return n.data;
		}return lastWord(n.hoyere);
	}

	T firstWord(Node n){
		if(n.lavere == null){
			return n.data;
		} return firstWord(n.lavere);
	}

	ArrayList<Integer> gjennomsnitt = new ArrayList<>();

	void finnGjennomsnitt(Node n, int depth){
		if(n.lavere==null || n.hoyere==null){
			gjennomsnitt.add(depth);
		}
		if(n.lavere != null){
			finnGjennomsnitt(n.lavere, depth +1 );
		}if(n.hoyere != null){
			finnGjennomsnitt(n.hoyere, depth +1 );
		}
	}


	void statistic(){
		checkMaxDepth(root, 0);
		System.out.println("antall noder i dybden");
    //might work
    System.out.println(treeDepth);
    System.out.println("antall noder pao bestemt plass.");
    //dosent work
    for (int i=0;i< treeDepth; i++) {
      System.out.print(nodes(root, i, 0));

    }
		System.out.println("forste node");
    System.out.println(lastWord(root));
		System.out.println("siste node ");
    System.out.println(firstWord(root));

		finnGjennomsnitt(root, 0);

		int sum =1, antall=1;

		for(Integer e: gjennomsnitt){
			antall++;
			sum = sum + e;
		}
	
		System.out.println("gjennomsnitt");
		System.out.println(sum/antall);


	}

}
