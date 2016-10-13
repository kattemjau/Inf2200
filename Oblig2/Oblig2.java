class Oblig2{
  public static void main(String[] args) {
    Prosjekt pro = new Prosjekt();
    try{
      pro.innleser("buildhouse1.txt");
      pro.settPointer();
      pro.settEarliest();
      pro.getLatest();
    //  pro.printTask();

  }catch(Exception e){
    e.printStackTrace();
  }

  }

}
