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
		radx(a, b, 0, a.length-1, 24, 8);
		double tim = (System.nanoTime() -t)/ (double)1000000.0;
		System.out.println("VenstreRadix Sorterte "+n+" tall paa:" + tim + "millisek.");
		testSort(a);
		System.out.println(" ");

		//
		// long tt = System.nanoTime();
		// Arrays.sort(aClone);
		// double tid = (System.nanoTime() -tt)/ (double)1000000.0;
		// System.out.println("Array.sort Sorterte "+n+" tall paa:" + tid + "millisek.");
		// testSort(aClone);

	}
	void vRadix(int[] a){
		int max = a[0], numBit = 2, numDigits, n =a.length;

		for (int i = 1 ; i < n ; i++)
			 if (a[i] > max) max = a[i];
		while (max >= (1L<<numBit) )numBit++; // antall binaere siffer i max
		System.out.println("MAX: " + max);
		System.out.println("numbit: " + numBit);
	 //
		int[] b = new int [a.length];
		// return radx(a, len, 8);	24 fordi
		radx(a, b, 0, a.length-1, 24, 8);	//endre a til b

	}
	void radx(int [] a, int [] b, int left, int right, int leftSortBit, int maskLen){
		if(right<=left+15 ){
			innstikk(a, left, right);
			return;
		}


		int value=0, j;
		// int mask = (1<<maskLen) -1;
		int[] count = new int [256+1];


		for (int i = left; i <= right; i++) {
			count[((a[i]>>>leftSortBit) & 255)+1]++; //gjør om til å gå fra venstre til høyre
		}


		for (int i = 0; i <= 255; i++) {
			count[i+1]+=count[i];
		}

		for (int i = left; i <= right; i++) {
			b[count[(a[i]>>>leftSortBit) & 255]++] = a[i]; //endre til å flytte bitsa fra venstre
		}

		//breaks if it reaches last run
		for (int i=left;i<=right ;i++ ) {
			a[i]=b[i-left];

		}

		if(count[0]>0){
			radx(a, b, left, left+count[0]-1, leftSortBit-8, maskLen);
		}
		for (int i = 0; i <= 255; i++) {
			if(count[i+1]>count[i])
			radx(a, b, left+count[i], left+count[i+1]-1, leftSortBit-8, maskLen);	//endre a til b
		}

		//everything should be in a
		}

	void innstikk(int []a, int left, int right) {
		for(int i = left; i <= right; i++) {
				for (int j = i; j > left && a[j] < a[j-1]; j--){
					int temp=a[j];
					a[j]=a[j-1];
					a[j-1]=temp;

				}
		}
		// return a;
	}

	void testSort(int[] a){
		for (int i = 0; i< a.length-1;i++) {
			// System.out.println(a[i]);
			if (a[i] > a[i+1]){
				System.out.println("SorteringsFEIL pa plass: "+i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
				return;

			}
		}
	}// end simple sorteingstest
}
