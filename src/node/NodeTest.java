package node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NodeTest {

  @Test
  void constructorTest() {
    Node<Integer> newNode = new Node<Integer>(10);
    assertEquals((Integer)10, newNode.getData(), "The node data should be 10");
  }

  @Test
  void cctorTest() {
    Node<Integer> newNode = new Node<Integer>(12);
    Node<Integer> anotherNode = new Node<Integer>(newNode);
    assertEquals((Integer)12, anotherNode.getData(), "The copy constructor is not working");
  }
  
  @Test
  void getDataTest() {
    Node<Integer> newNode = new Node<Integer>(12);
    assertEquals((Integer)12, newNode.getData(), "The getData is not working");
  }
  
  @Test
  void getNextTest() {
    Node<Integer> newNode = new Node<Integer>(12);
    Node<Integer> anotherNode = new Node<Integer>(10);
    anotherNode.setNext(newNode);
    assertEquals((Integer)12, anotherNode.getNext().getData(), "The getNext is not working");
  }
  
  @Test
  void setDataTest() {
    Node<Integer> newNode = new Node<Integer>(12);
    newNode.setData(10);
    assertEquals((Integer)10, newNode.getData(), "The setData is not working");
  }
  
  @Test
  void setNextTest() {
    
  }
}
