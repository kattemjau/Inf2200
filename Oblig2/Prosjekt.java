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


    for(int counter=0; counter<num; counter++){
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
        //System.out.println("id" + dep[k]);
        i++;
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
  void settPointer(){
  //  System.out.println("denne kjorer");
    for (Task i: array) {
      if(i.dep!=null){
      for (int e: i.dep) {
        //System.out.println(e);
        i.edges.add(array[e-1]);
      }
    }
    }
  }
  void settEarliest(){
    for(Task e: array){
      e.checkCycle(e.id);
      //System.out.println("ID: " + e.id + " Earliest: "+ e.getEarliest());
    }
  }





  class Task {
    int id, time, man;
    String name;
    int[] dep;
    int earlyStart;
    ArrayList<Task > edges;

    boolean finished = false;

    Task(int id, int time, int man, String name, int[] dep){
      this.id=id;
      this.time=time;
      this.man=man;
      this.name=name;
      this.dep=dep;
      edges = new ArrayList<>();
    }
    void print(){
      System.out.println( "ID: " + id + " TIME: " + time + " man: " + man + " name: " + name);
      for (Task e: edges) {
        System.out.println("edges: " + e.id);
        }
        System.out.println(" ");
      }


      int getEarliest(){
        if(edges==null){
          earlyStart=0;
          return 0;
        }
        else{
          int earliestB=0;
          for (Task e: edges) {
            int earliest = e.time + e.getEarliest();

            if(earliest>earliestB){
              earliestB=earliest;
            }

          }
          earlyStart=earliestB;
          return earliestB;
        }
      }

      void checkCycle(int i){
        if(finished == true && i==id){
          System.out.println("CYCLE!!");
          //gatt i cycle
          System.exit(0);
        }
        else if(edges!=null){
        for (Task e: edges) {
          finished = true;
          e.checkCycle(i);
        }
      }

      }
    }
}
