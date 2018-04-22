

public class Node<T> {
  private T data;
  private Node<T> next;
  
  /**
   * constructor for node.
   * @param data = the value that the corresponding node store.
   */
  Node(T data) {
    this.data = data;
  }
  
  /**
   * copy constructor like from c++.
   * @param nodeCopy = the node that we want to copy the value from.
   */
  Node(Node<T> nodeCopy) {
    this.data = nodeCopy.getData();
    this.next = nodeCopy.getNext();
  }

  T getData() {
    return this.data;
  }
  
  Node<T> getNext() {
    return this.next;
  }
  
  void setData(T data) {
    this.data = data;
  }
  
  void setNext(Node<T> next) {
    this.next = next;
  }
}