import java.util.Scanner;
import java.io.File;

public class Oppg1{
  public static void main(String[] args) {
    try{
    File fil = new File("test.txt");
    Scanner in = new Scanner(fil);
  }catch(Exception e){
    e.printStackTrace();
  }


}
