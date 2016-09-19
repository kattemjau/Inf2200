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

    System.out.println(liste.antall());
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
    System.out.println("antall noder i dybden");
    System.out.println(liste.checkMaxDepth());
    System.out.println("antall noder pao bestemt plass.");
    //dosent work
    for (int i=0;i< liste.checkMaxDepth(); i++) {
      System.out.println(liste.nodes(i));

    }
    System.out.println("siste node ");
    System.out.println(liste.lastWord());
    System.out.println("forste node");
    System.out.println(liste.firstWord());

    } catch(FileNotFoundException e){
      e.printStackTrace();
    }

  }

}
