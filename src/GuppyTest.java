import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GuppyTest {

  @Test
  void constructorTest() {
    Guppy guppy = new Guppy(new Point(0,0), 'l');
    assertEquals(0,guppy.getPosition().getOrdinat(),"The guppy init absis should be 0");
    assertEquals(0,guppy.getPosition().getAbsis(),"The guppy init ordinat should be 0");
  }

  @Test
  void dropCoinTest() {
    Aquarium aq = new Aquarium();
    Guppy guppy = new Guppy(new Point(0,0), 'l');
    guppy.dropCoin(10);
    assertEquals(1,Aquarium.coins.getAmount(),"There should be 1 coin in the aquarium");
  }
  
  @Test
  void eatTest() {
    Aquarium aq = new Aquarium();
    Food f = new Food(0.0);
    Aquarium.foods.add(f);
    Guppy guppy = new Guppy(new Point(0,0), 'l');
    guppy.eat(f);
    assertEquals(1, guppy.getGrowthLevel(), "The guppy should have 1 growth level");
  }
  
  @Test
  void searchFoodTest() {
    Aquarium aq = new Aquarium();
    Food f = new Food(0.0);
    f.setPosition(new Point(0,0));
    Aquarium.foods.add(f);
    Guppy guppy = new Guppy(new Point(0,0), 'l');
    guppy.searchFood();
    assertEquals(1, guppy.getGrowthLevel(), "The guppy should have 1 growth level");
  }
  
}
