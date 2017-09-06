class Oblig2{
  public static void main(String[] args) {
    Prosjekt pro = new Prosjekt();
    try{
      pro.innleser(args[0]);
      pro.settPointer();
      pro.settEarliest();
      pro.getLatest();
      pro.printTasks();
      pro.printTask();

  }catch(Exception e){
    e.printStackTrace();
  }

  }

}
