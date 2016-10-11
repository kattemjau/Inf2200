import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

class Prosjekt{
  Task array[];
  int num=0;

  void innleser(String filnavn) {
    try{
    File fil = new File(filnavn);
    Scanner in = new Scanner(fil);


    num = Integer.parseInt(in.nextLine());
    array = new Task[num];

    in.nextLine();


    for(int counter=0; counter<num;counter++  ){
      String linje = in.nextLine();
      String splittet[] = linje.split("\\s+");

      int lengden = splittet.length-5;
      int dep[];
      if(lengden <0){
        dep = null;
      }
      else{
      dep=new int[lengden];
      int i=4;
      for(int k =0; k<dep.length; k++){
        dep[k]=Integer.parseInt(splittet[i]);

      }
    }
      //System.out.println(counter);
      Task temp= new Task(Integer.parseInt(splittet[0]), Integer.parseInt(splittet[2]), Integer.parseInt(splittet[3]), splittet[1], dep);
      array[counter]=temp;
    }
  }catch(Exception e){
    e.printStackTrace();
  }
  }
  void printTask(){
    for (int i=0;i<num ;i++ ) {
      array[i].print();

    }
  }


  class Task {
    int id, time, man;
    String name;
    int[] dep;
    ArrayList<Task > edges;

    Task(int id, int time, int man, String name, int[] dep){
      this.id=id;
      this.time=time;
      this.man=man;
      this.name=name;
      this.dep=dep;

    }
    void print(){
      System.out.println( "ID: " + id + " TIME: " + time + " man: " + man + " name: " + name);
      for (int i =0;i<dep.length ;i++ ) {
        System.out.println("peker til Tasken: " +dep[i]);
        }
        System.out.println(" ");
      }
    }
}
