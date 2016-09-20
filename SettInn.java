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
		if(temp==null){
			return null;
		}

		if(in.compareTo(temp.data) > 0){
			return search(in, temp.lavere);
		}else if(in.compareTo(temp.data) < 0){
			return search(in, temp.hoyere);
		}else {
			return in;
		}

	}



	void deleate(T in, Node n){
		if(n==null){
			return;
		}
		if(n.data.compareTo(in)==0){
			n.bak.hoyere=null;
			n.bak.lavere=null;
			return;
		}if(n.hoyere.data.compareTo(in)<0){
			deleate(in, n.hoyere);
			return;
		}else{
			deleate(in, n.lavere);
			return;
		}

	}
	void deleate(T in){
		deleate(in, root);
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
		//	System.out.println("0");
			return 0;
		}
		if(i==k){
		//	System.out.println("1");
			return 1;
		}

	//	System.out.println("rekursiv" + i + k);
		k=k+1;
		return nodes(n.lavere, i, k) + nodes(n.hoyere, i, k);
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
   		//works
		System.out.println(treeDepth);


		System.out.println("antall noder pao bestemt plass.");
		for (int i=0;i< treeDepth+1; i++) {
			System.out.print("plass " + i + ": ");
			System.out.println(nodes(root, i, 0));

		}



		System.out.println(" ");
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
		System.out.println(" ");

		System.out.println("gjennomsnitt");
		System.out.println(sum/antall + " Noder");


	}

}
