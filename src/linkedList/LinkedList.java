package linkedList;

import node.Node;
import java.lang.ArrayIndexOutOfBoundsException;

public class LinkedList<T> {
  private Node<T> head;
  private int amount;
  
  /**
   * linkedList constructor.
   */
  public LinkedList() {
    head = null;
    amount = 0;
  }
  
  /**
   * constructor with a node as initial head value.
   * @param nodeInit = the node to fill head.
   */
  public LinkedList(Node<T> nodeInit) {
    this.head = new Node<T>(nodeInit);
    amount = 1;
  }
  
  /**
   * find the index from the linkedList from given value.
   * @param val = the value that we are looking the index for.
   * @return the index of the given value.
   * @throws ArrayIndexOutOfBoundsException in case the value is not in the linkedList.
   */
  public int find(T val) throws ArrayIndexOutOfBoundsException {
    Node<T> current = head;
    int i = 0;
    while (current.getData() != val && ++i < amount) {
      current = current.getNext();
    }
    if (i == amount) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      return i;
    }
  }
  
  public int getAmount() {
    return amount;
  }
  
  /**
   * check if linkedList is empty.
   * @return true if linkedList empty and false if otherwise.
   */
  public boolean isEmpty() {
    return (amount == 0);
  }
  
  /**
   * add new element to linkedList, the element will be placed at the end.
   * @param val is the value to be added to the linkedList.
   */
  public void add(T val) {
    Node<T> current = head;
    if (amount == 0) {
      head = new Node<T>(val);
    } else {
      while (current.getNext() != null) {
        current = current.getNext();
      }
      Node<T> newNode = new Node<T>(val);
      current.setNext(newNode);
    }
    amount++;
  }
  
  /**
   * remove an element based on the element value.
   * will remove ALL occurrence.
   * @param val is the value we want to remove.
   */
  public void remove(T val) throws ArrayIndexOutOfBoundsException {
    try {
      int idx = find(val);
      del(idx);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw e;
    } catch (Exception e) {
      System.out.println("LikendList val = " + val + " " + e);
      throw e;
    }
  }
  
  /**
   * get the data from linkedList at the given index.
   * @param i is the index.
   * @return the data at the index.
   * @throws ArrayIndexOutOfBoundsException in case the index is invalid.
   */
  public T get(int i) throws ArrayIndexOutOfBoundsException {
    if (i >= amount || i < 0) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      Node<T> current = this.head;
      for (int j = 1; j <= i; j++) {
        current = current.getNext();
      }
      return current.getData();
    }
  }
  
  /**
   * node getter.
   * @param i is the index of the node.
   * @return node at the index i.
   * @throws ArrayIndexOutOfBoundsException if i is invalid.
   */
  public Node<T> getNode(int i) throws ArrayIndexOutOfBoundsException {
    Node<T> current = head;
    if (i >= amount || i < 0) {
      throw new ArrayIndexOutOfBoundsException();
    } else {
      for (int j = 1; j <= i; j++) {
        current = current.getNext();
      }
      return current;
    }
  }
  
  /**
   * delete linkedList member at the given index.
   * @param idx = the location of the member that's going to be deleted.
   * @throws ArrayIndexOutOfBoundsException in case the index is not valid.
   */
  public void del(int idx) throws ArrayIndexOutOfBoundsException {
    if (idx >= amount || idx < 0) {
      throw new ArrayIndexOutOfBoundsException();
    }
    
    Node<T> prev = new Node<T>(head);
    Node<T> current = head;
    int j = 0;
    while (j != idx) {
      prev = current;
      current = current.getNext();
      j++;
    }
    if (current == head) {
      head = current.getNext();
    } else {
      current = current.getNext();
      prev.setNext(current);
    }
    amount--;
  }
}
