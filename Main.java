import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main{

  public static void main(String[] args) {
    try{
    File fil = new File("test.txt");
    Scanner in = new Scanner(fil);
    SettInn<String> liste = new SettInn<String>();

    while(in.hasNextLine()){
      String data = in.nextLine();
      liste.insert(data);
    }

    Scanner leser = new Scanner(System.in);
    String valg;

    while(true){
      valg=leser.nextLine();

      if(valg.equals("q")){
        break;
      } else {
        liste.search(valg);
      }
    }

    } catch(FileNotFoundException e){
      e.printStackTrace();
    }

  }

}
