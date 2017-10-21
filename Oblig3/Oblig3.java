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


		long t = System.nanoTime();
		a=vRadix(a);
		double tim = (System.nanoTime() -t)/ (double)1000000.0;
		System.out.println("VenstreRadix Sorterte "+n+" tall paa:" + tim + "millisek.");
		testSort(a);
		// System.out.println(" ");
		//
		//
		// long tt = System.nanoTime();
		// Arrays.sort(aClone);
		// double tid = (System.nanoTime() -tt)/ (double)1000000.0;
		// System.out.println("Array.sort Sorterte "+n+" tall paa:" + tid + "millisek.");
		// testSort(aClone);

	}
	int[] vRadix(int[] a){
		int max = a[0], numBit = 2, numDigits, n =a.length;
		//
		// int [] bit ;

	 // a) finn max verdi i a[]
	//  // b) bestem numBit = høyeste (mest venstre) bit i ‘max’ som ==1
		for (int i = 1 ; i < n ; i++)
			 if (a[i] > max) max = a[i];
		while (max >= (1L<<numBit) )numBit++; // antall binaere siffer i max
	 //
	// 	// bestem antall bit i numBits sifre
	// 	numDigits = Math.max(1, numBit/7);
	// 	bit = new int[numDigits];
	// 	int rest = (numBit%numDigits), sum =0;;
	 //
	// 	// fordel bitene vi skal sortere paa jevnt
	// 	for (int i = 0; i < bit.length; i++){
	// 		bit[i] = numBit/numDigits;
	// 			if ( rest-- > 0)  bit[i]++;
	// 	}

		numDigits = Math.max(1, numBit/7);

		// c) Første kall (rot-kallet) på VenstreRadix med a[], b[] , numBit, og lengden av første siffer
		// n=100 skal kjøre 3 ganger
		// starter på bit plass 2 så 1 så 0
		int len =numBit/numDigits;
		//finne ut hvor mange ganger den må gå bassert på max og mask
		radx(a, len, 0);	//a, antall bits den skal skifte med, posisjon

		return radx(a, len, 0);



	}
	int[] radx(int[] a, int len, int pos){
		int[] b = new int [a.length];
		int value=0, j, n = a.length;
		int mask = (1<<len) -1;
		int[] count = new int [mask+1];

		System.out.println("Math: " + Math.pow(len, pos));
		// System.out.println("len_ " + len);
		System.out.println("mask: " + mask);

		//count instances of eatch number
		// d) count[] =hvor mange det er med de ulike verdiene
		// av dette sifret I a [left..right]
		for (int i = 0; i < n; i++) {
			count[(a[i]/(int) Math.pow(10, pos)) & mask]++; //gjør om til å gå fra venstre til høyre
		}


		for (int i = 0; i < n; i++) {
			System.out.println(a[i] +  " == " + ((a[i]/(int) Math.pow(10, pos)) & mask));
		}
		// e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal
		// flytte første element med verdien ‘i’ vi sorterer.

		for (int i = 0; i <= mask; i++) {
			j = count[i];
			count[i] = value;
			value += j;
		}

		for (int i = 0; i < n; i++) {
			b[count[(a[i]/(int) Math.pow(10, pos)) & mask]++] = a[i]; //endre til å flytte bitsa fra venstre
		}

		//breaks if it reaches last run
		if(pos==0){
			return b;
		}
		return radx(a, len, pos-1);	//endre a til b
		// return b;
	}
	// int value=0, j, n = a.length;
	// int mask = (1<<maskLen) -1;
	// int[] count = new int [mask+1];
	// int[] b = new int [a.length];
	//
	//
	// // d) count[] =hvor mange det er med de ulike verdiene
	// // av dette sifret I a [left..right]
	// for (int i = 0; i < n; i++) {
	// 	count[(a[i]>>> shift) & mask]++; //gjør om til å gå fra venstre til høyre
	// }
	//
	// // e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal
	// // flytte første element med verdien ‘i’ vi sorterer.
	// for (int i = 0; i <= mask; i++) {
	// 	j = count[i];
	// 	count[i] = value;
	// 	value += j;
	// }
	// // f) Flytt tallene fra a[] til b[] sorter på dette sifferet I a[left..right] for
	// // alle de ulike verdiene for dette sifferet
	// for (int i = 0; i < n; i++) {
	// 	b[count[(a[i]>>>shift) & mask]++] = a[i]; //endre til å flytte bitsa fra venstre
	// }
	// // g) Kall enten innstikkSort eller rekursivt VenstreRadix
	// // på neste siffer (hvis vi ikke er ferdige) for alle verdiene vi har av nåværende siffer
	// // if(shift!=lastByte){
	// // 	radx(b, leftSortBit, maskLen);
	// // }
	// // Vurder når vi. skal kopiere tilbake b[] til a[] ??
	// return b;
	//
	// /** N.B. Sorterer a[] stigende – antar at: 0 ≤ a[i]) < 232 , returnerer tiden i millisek. */
	// final static int NUM_BIT =9; // eller: 6-13 er kanskje best.. finn selv ut hvilken verdi som er raskest
	// final static int MIN_NUM = 31; // mellom 16 og 60, kvikksort bruker 47
	// double VRadixMulti(int [] a) {
	// 	long tt = System.nanoTime();
	// 	int [] b = new int [a.length];
	// 	// a) finn ‘max’ verdi i a[]
	// 	// b) bestem numBit = høyeste (mest venstre) bit i ‘max’ som ==1
	// 	// c) Første kall (rot-kallet) på VenstreRadix med a[], b[] , numBit, og lengden av første siffer
	//
	// 	double tid = (System.nanoTime() -tt)/1000000.0;
	// 	testSort(a);
	// 	return tid; // returnerer tiden i ms. det tok å sortere a, som nå er sortert og testet
	// } // end VRadixMulti
	// // Sorter a[left..right] på siffer med start i bit: leftSortBit, og med lengde: maskLen bit,
	// void VenstreRadix ( int [] a, int [] b, int left, int right, int leftSortBit, int maskLen){
	// 	int mask = (1<<maskLen]) -1;
	// 	int [] count = new int [mask+1];
	// 	……………. Andre deklarasjoner ……………
	// 	// d) count[] =hvor mange det er med de ulike verdiene
	// 	// av dette sifret I a [left..right]
	// 	// e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal
	// 	// flytte første element med verdien ‘i’ vi sorterer.
	// 	// f) Flytt tallene fra a[] til b[] sorter på dette sifferet I a[left..right] for
	// 	// alle de ulike verdiene for dette sifferet
	// 	// g) Kall enten innstikkSort eller rekursivt VenstreRadix
	// 	// på neste siffer (hvis vi ikke er ferdige) for alle verdiene vi har av nåværende siffer
	// 	// Vurder når vi. skal kopiere tilbake b[] til a[] ??
	// }// end VenstreRadix



	void testSort(int[] a){
		for (int i = 0; i< a.length-1;i++) {
			if (a[i] > a[i+1]){
				System.out.println("SorteringsFEIL pa plass: "+i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
				return;

			}
		}
	}// end simple sorteingstest
}
