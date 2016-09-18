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

    Scanner leser = new Scanner(System.in);
    SearchInterface search = new SearchInterface(liste);
    String valg;

    ArrayList<String> likeOrd;
    //remove busybody


    while(true){
      System.out.println("Press (q) to exit program, or search for a word");
      valg=leser.nextLine();
      if(valg.equals("q")){
        break;
      } else {
        if(liste.search(valg.toLowerCase())==null){
          System.out.println("Couldent find the word: " + valg);
          //search simmular words
          likeOrd = search.searchSimmular(valg);
          System.out.println("fant disse liknende ordene");
          for(String s : likeOrd) {
              System.out.println(s);
            }
        }else{
          System.out.println("Found the word: " + valg);
        }

      }
    }

    } catch(FileNotFoundException e){
      e.printStackTrace();
    }

  }

}
