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
    }for (Task e: array ) {
      System.out.println("ID: " + e.id + " Earliest: "+ e.getEarliest());
    }

  }
  Task findMaxTime(){
    int latest=0;
    Task last = null;
    for (Task e: array) {
      if(e.earlyStart > latest){
        latest = e.earlyStart;
        last =e;
      }
    }
    return last;
  }
  void getLatest(){
    Task temp = findMaxTime();
    System.out.println(" ");
    for (Task e: array ) {
      e.latestStart=temp.earlyStart + temp.time;
    }

    temp.getLatest(temp.latestStart);

    for (Task k: array) {
      if(k.latestStart ==( temp.earlyStart + temp.time)){
        k.getLatest(k.latestStart);
      }
    }


    for (Task i: array ) {
      System.out.println("ID: " + i.id + " Latest: "+ i.latestStart);

    }
    System.out.println(" ");
  }

  void printTasks(){
    Task temp=findMaxTime();
    int maxTime = temp.earlyStart+temp.time;
    int man =0;
    for (int i =0;i< maxTime;i++ ) {
      boolean tim =true;
      for(Task e: array){
        if(i== e.earlyStart + e.time){
          if(tim){System.out.println("Time: " + i);}
          System.out.println("\tFinished: "+e.id );
          tim =false;
          man-=e.man;
        }

      }
      for (Task k: array) {
        if(i == k.earlyStart){
          if(tim){System.out.println("Time: " + i);}
          System.out.println("\tStarting: " +k.id );
          tim =false;
          man+=k.man;
        }
      }
      if(!tim){
        System.out.println("   Current staff: " + man);
        System.out.println(" ");
      }


    }

  }





  class Task {
    int id, time, man;
    String name;
    int[] dep;
    int earlyStart, latestStart=0;
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
      System.out.println("Earliest start: " + earlyStart + " latestStart: " + latestStart + " slack: " + (latestStart- earlyStart));
      for (Task e: edges) {
        System.out.print("dependencies: " + e.id + " ");
        }
        System.out.println(" ");
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
          System.out.println("CYCLE!!" );
          System.out.print(i);
          for (Task e:edges) {
            e.printCycle(i);
          }
          System.out.println( " << " + id);
          System.exit(0);
        }
        else if(edges!=null){
        for (Task e: edges) {
          finished = true;
          e.checkCycle(i);
        }
      }

      }
      void printCycle(int i){
        if(edges.size()!=0){
          System.out.print( " << " + id);

        }
        for (Task e:edges) {
          if(e.id==i){
            System.out.println( " << " + i);
            System.exit(0);
          }
          e.printCycle(i);

        }
      }

        void getLatest(int fis){
          if(edges.size()==0){
            latestStart=0;
            return;
          }
          else if(edges != null){
            int latest = fis - time;
            if(latestStart > latest){
              latestStart = latest;
            }

            for(Task e: edges){
              e.getLatest(latestStart);
            }
          }
        }

    }
}
