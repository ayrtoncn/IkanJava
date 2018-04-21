

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointTest {

  @Test
  void testPoint() {
    Point p = new Point();
    assertEquals(-99,p.getAbsis());
    assertEquals(-99,p.getOrdinat());
  }

  @Test
  void testPointDoubleDouble() {
    Point p = new Point(10,10);
    assertEquals(10,p.getAbsis());
    assertEquals(10,p.getOrdinat());
  }

  @Test
  void testGetOrdinat() {
    Point p = new Point(10,10);
    assertEquals(10,p.getOrdinat());
  }

  @Test
  void testSetOrdinat() {
    Point p = new Point(10,10);
    p.setOrdinat(5);
    assertEquals(5,p.getOrdinat());
  }

  @Test
  void testGetAbsis() {
    Point p = new Point(10,10);
    assertEquals(10,p.getAbsis());
  }

  @Test
  void testSetAbsis() {
    Point p = new Point(10,10);
    p.setAbsis(5);
    assertEquals(5,p.getAbsis());
  }

  @Test
  void testGetDistance() {
    Point p1 = new Point(3,4);
    Point p2 = new Point(0,0);
    assertEquals(5,p1.getDistance(p2));
  }

}
