import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class Main{

  public static void main(String[] args) {
    try{
    File fil = new File("dictionary.txt");
    Scanner in = new Scanner(fil);
    SettInn<String> liste = new SettInn<String>();

    while(in.hasNextLine()){
      String data = in.nextLine();
      //System.out.println(data);
      liste.insert(data.toLowerCase());
    }

    System.out.println(liste.antall() + " Antall ord");
    Scanner leser = new Scanner(System.in);
    SearchInterface search = new SearchInterface(liste);
    String valg;

    ArrayList<String> likeOrd;

    liste.deleate("busybody");
    liste.insert("busybody");

    System.out.println("testing deleate:");
    System.out.println("superman ");
    liste.deleate("superman");
    System.out.println("x amn word");
    liste.insert("x_last_word");
    liste.deleate("x_last_word");
    System.out.println("cool");
    liste.deleate("cool");
    System.out.println("testing done!");


    while(true){
      System.out.println("Press (q) to exit program, or search for a word");
      valg=leser.nextLine();
      if(valg.equals("q")){
        break;
      } else {
        if(liste.search(valg.toLowerCase())==null){
          System.out.println("Couldent find the word: " + valg);
          //search simmular words
          Long start = System.nanoTime();
          likeOrd = search.searchSimmular(valg);
          Long slutt = System.nanoTime();


          System.out.println("fant disse liknende ordene");
          for(String s : likeOrd) {
              System.out.println(s);
            }
          System.out.println("Tid pa look up: " + Math.round((slutt - start)/ (float) 10000 )/100.0 + " ms" );
        }else{
          System.out.println("Found the word: " + valg);
        }

      }
    }
    liste.statistic();

    } catch(FileNotFoundException e){
      e.printStackTrace();
    }

  }

}
