// 1. fin max a()
//2. bestem numBit = mest venstre bit i max
// 3. første kall på VRadix med a, b, numbit, og lengden av første siffer
// rekursiv for antall bits ha med hvilken bit i konstruktøreturn
// sammenlikne med arrays.sort

/**
* Oblig3 Inf2220
*
*	Bassert på kode fra Arne Maus
**/



import java.util.*;

public class Oblig3{

	static final int MAX = 256;
	static final int BYTE = 8;

	int[] a;
	int n;
	public static void main(String[] args) {
		new Oblig3().accumulating(Integer.parseInt(args[0]));

	}
	public void accumulating(int len){
		a=new int[len];
		n=len;
		Random r = new Random(123);
		for (int i =0; i < len;i++) {	//generating Random array
			a[i] = r.nextInt(len);
		}

		int [] aClone=Arrays.copyOf(a, a.length);

		System.out.println(" ");
		int[] b = new int [a.length];


		long t = System.nanoTime();
		//tester radx sortering fra plass 24
		radx(a, b, 0, a.length-1, 24, BYTE);
		double tim = (System.nanoTime() -t)/ (double)1000000.0;
		System.out.println("VenstreRadix Sorterte "+n+" tall paa:" + tim + "millisek.");
		testSort(a);
		// System.out.println(" ");


		long tt = System.nanoTime();
		Arrays.sort(aClone);
		double tid = (System.nanoTime() -tt)/ (double)1000000.0;
		System.out.println("Array.sort Sorterte "+n+" tall paa:" + tid + "millisek.");
		testSort(aClone);

	}

	void radx(int [] a, int [] b, int left, int right, int leftSortBit, int maskLen){
		if(right<=left | leftSortBit<=0){
			// innstikk(a, left, right);
			return;
		}


		int value=0, j;
		// int mask = (1<<maskLen) -1;
		int[] count = new int [MAX+1];


		//teller antall instances
		for (int i = left; i <= right; i++) {
			count[((a[i]>>>leftSortBit-8) & MAX-1)+1]++; //gjør om til å gå fra venstre til høyre
			// System.out.println(a[i] + " a[i] " + (a[i]>>>leftSortBit-8)+ " shifted " + ((a[i]>>>leftSortBit-8) & MAX-1) + " anded");
		}

		//setter posisjon for insetting
		for (int i = 0; i < MAX; i++) {
			count[i+1]+=count[i];
		}

		// Sorterer delene
		for (int i = left; i <= right; i++) {
			b[count[(a[i]>>>leftSortBit-8) & MAX-1]++] = a[i]; //endre til å flytte bitsa fra venstre
		}


		for (int i=left;i<=right ;i++ ) {
			a[i]=b[i-left];

		}


		//recursive call
		if(count[0]>0 ){
			radx(a, b, left, left+count[0]-1, leftSortBit-BYTE, maskLen);
		}

		for (int i = 0; i < MAX; i++) {
			if(count[i+1]>count[i]){
				radx(a, b, left+count[i], left+count[i+1]-1, leftSortBit-BYTE, maskLen);	//endre a til b
			}
		}

	}

	// void innstikk(int []a, int left, int right) {
	// 	for(int i = left; i <= right; i++) {
	// 			for (int j = i; j > left && a[j] < a[j-1]; j--){
	// 				int temp=a[j];
	// 				a[j]=a[j-1];
	// 				a[j-1]=temp;
	//
	// 			}
	// 	}
	// 	// return a;
	// }

	void testSort(int[] a){
		for (int i = 0; i< a.length-1;i++) {
			if (a[i] > a[i+1]){
				System.out.println("SorteringsFEIL pa plass: "+i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
				return;

			}
		}
	}// end simple sorteingstest
}
