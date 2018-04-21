package ikanmedhok;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LinkedListTest {

  @Test
  void defautlConstructorTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    assertEquals(0, listInt.getAmount(), "The initial amount must be 0");
  }
  
  @Test
  void nodeParamConstructorTest() {
    Integer testData = 91;
    Node<Integer> newNode = new Node<Integer>(testData);
    LinkedList<Integer> listInt = new LinkedList<Integer>(newNode);
    assertEquals(testData, listInt.get(0), "The value of the first index must be 0");
  }
  
  @Test
  void addingObjectTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    for (Integer i = 0; i < 1000; i++) {
      listInt.add(i);
    }
    for (Integer i = 0; i < 1000; i++) {
      assertEquals(i, listInt.get(i), "The value of the " + Integer.toString(i) + "th"
          + " oject must be " + Integer.toString(i));
    }
  }
  
  @Test
  void deleteObjectTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    for (Integer i = 0; i < 1000; i++) {
      listInt.add(i);
    }
    for (Integer i = 0; i < 500; i++) {
      listInt.del(i);
    }
    for (Integer i = 0; i < 500; i++) {
      Integer temp = i * 2 + 1;
      assertEquals(temp, listInt.get(i), "All element must be odd");
    }
  }
  
  @Test
  void findValTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    for (Integer i = 0; i < 100; i++) {
      listInt.add(i);
    }
    assertEquals(4, listInt.find(4), "Index for 4 should be 4");
  }
  
  @Test
  void getValTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    for (Integer i = 0; i < 100; i++) {
      listInt.add(i);
    }
    assertEquals((Integer)99, listInt.get(99), "The value on the 99th index should be 99");
  }
  
  @Test
  void getAmountTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    for (Integer i = 0; i < 100; i++) {
      listInt.add(i);
    }
    assertEquals(100, listInt.getAmount(), "The amount should be 100");
  }
  
  @Test
  void getNodeTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    Node<Integer> nodeInt = new Node<Integer>(10);
    for (Integer i = 0; i < 100; i++) {
      listInt.add(i);
    }
    assertEquals(nodeInt.getData(), listInt.getNode(10).getData(), "The node should be the same");
  }
  
  @Test
  void isEmptyTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    assertEquals(true, listInt.isEmpty(), "List should be empty");
    for (Integer i = 0; i < 100; i++) {
      listInt.add(i);
    }
    for (Integer i = 0; i < 100; i++) {
      listInt.del(0);
    }
    assertEquals(true, listInt.isEmpty(), "List should be empty");
  }
  
  @Test
  void removeTest() {
    LinkedList<Integer> listInt = new LinkedList<Integer>();
    for (Integer i = 0; i < 100; i++) {
      listInt.add(i);
    }
    for (Integer i = 0; i < 100; i++) {
      listInt.remove(i);
    }
    assertEquals(true, listInt.isEmpty(), "List should be empty");
  }
}
