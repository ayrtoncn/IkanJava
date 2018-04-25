package piranha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aquarium.Aquarium;
import guppy.Guppy;
import org.junit.jupiter.api.Test;
import point.Point;

class PiranhaTest {

  @Test
  void dropCoinTest() {
    Aquarium aq = new Aquarium();
    Piranha piranha = new Piranha(new Point(0,0),'l');
    piranha.dropCoin(10);
    assertEquals(1, aq.coins.getAmount(), "There should be 1 coin in the aquarium");
  }
  
  @Test
  void eatTest() {
    Aquarium aq = new Aquarium();
    Piranha piranha = new Piranha(new Point(0,0),'l');
    Guppy guppy = new Guppy(new Point(0,0), 'l');
    aq.guppy.add(guppy);
    assertEquals(1, aq.guppy.getAmount(), "There should be 1 guppy in the aquarium");
    aq.piranha.add(piranha);
    assertEquals(1, aq.piranha.getAmount(), "There should be 1 piranha in the aquarium");
    piranha.eat(guppy);
    assertEquals(0, aq.guppy.getAmount(), "There should be 0 now guppy in the aquarium");
    assertEquals(1, aq.coins.getAmount(), "There should be 1 coin in the aquarium");
  }

   @Test
   void searchFood() {
     Aquarium aq = new Aquarium();
     Piranha piranha = new Piranha(new Point(0,0),'l');
     Guppy guppy = new Guppy(new Point(0,0), 'l');
     aq.guppy.add(guppy);
     assertEquals(1, aq.guppy.getAmount(), "There should be 1 guppy in the aquarium");
     aq.piranha.add(piranha);
     assertEquals(1, aq.piranha.getAmount(), "There should be 1 piranha in the aquarium");
     piranha.searchFood();
     assertEquals(0, aq.guppy.getAmount(), "There should be 0 now guppy in the aquarium");
     assertEquals(1, aq.coins.getAmount(), "There should be 1 coin in the aquarium");
   }
}
