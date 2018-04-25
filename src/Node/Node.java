package Node;

public class Node<T> {
  private T data;
  private Node<T> next;
  
  /**
   * constructor for node.
   * @param data = the value that the corresponding node store.
   */
  public Node(T data) {
    this.data = data;
  }
  
  /**
   * copy constructor like from c++.
   * @param nodeCopy = the node that we want to copy the value from.
   */
  public Node(Node<T> nodeCopy) {
    this.data = nodeCopy.getData();
    this.next = nodeCopy.getNext();
  }

  public T getData() {
    return this.data;
  }
  
  public Node<T> getNext() {
    return this.next;
  }
  
  public void setData(T data) {
    this.data = data;
  }
  
  public void setNext(Node<T> next) {
    this.next = next;
  }
}