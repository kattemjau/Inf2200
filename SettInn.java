class SettInn{

  void insert(Node in){

  }
  Node search(String in){
    return null;
  }
  void deleate(Node in){

  }
}

class Node<String extends Comparable<String>>{
  public Node hoyere;
  public Node lavere;
  public Node bak;

  public String data;

  Node(String data){
    this.data=data;
  }
  boolean compareTo(Node in){
    return data.equals(in.data);
  }


}
