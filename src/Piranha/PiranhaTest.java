import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PiranhaTest {

  @Test
  void dropCoinTest() {
    Aquarium aq = new Aquarium();
    Piranha piranha = new Piranha(new Point(0,0),'l');
    piranha.dropCoin(10);
    assertEquals(1, Aquarium.coins.getAmount(), "There should be 1 coin in the aquarium");
  }
  
  @Test
  void eatTest() {
    Aquarium aq = new Aquarium();
    Piranha piranha = new Piranha(new Point(0,0),'l');
    Guppy guppy = new Guppy(new Point(0,0), 'l');
    Aquarium.guppy.add(guppy);
    assertEquals(1, Aquarium.guppy.getAmount(), "There should be 1 guppy in the aquarium");
    Aquarium.piranha.add(piranha);
    assertEquals(1, Aquarium.piranha.getAmount(), "There should be 1 piranha in the aquarium");
    piranha.eat(guppy);
    assertEquals(0, Aquarium.guppy.getAmount(), "There should be 0 now guppy in the aquarium");
    assertEquals(1, Aquarium.coins.getAmount(), "There should be 1 coin in the aquarium");
  }

   @Test
   void searchFood() {
     Aquarium aq = new Aquarium();
     Piranha piranha = new Piranha(new Point(0,0),'l');
     Guppy guppy = new Guppy(new Point(0,0), 'l');
     Aquarium.guppy.add(guppy);
     assertEquals(1, Aquarium.guppy.getAmount(), "There should be 1 guppy in the aquarium");
     Aquarium.piranha.add(piranha);
     assertEquals(1, Aquarium.piranha.getAmount(), "There should be 1 piranha in the aquarium");
     piranha.searchFood();
     assertEquals(0, Aquarium.guppy.getAmount(), "There should be 0 now guppy in the aquarium");
     assertEquals(1, Aquarium.coins.getAmount(), "There should be 1 coin in the aquarium");
   }
}
