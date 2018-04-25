package snail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import aquarium.Aquarium;
import coin.Coin;
import org.junit.jupiter.api.Test;

class SnailTest {

  @Test
  void constructorTest() {
    Aquarium aq = new Aquarium();
    Snail snail = new Snail();
    assertTrue(snail.isRunning(), "The snail should be running");
    assertEquals('l', snail.getOrientation(), "The orientation should be l");
    assertEquals(50, snail.getMovementSpeed(), "The movement speed should be 50");
    assertEquals(80, snail.getPosition().getAbsis(), "The position absis should be 80");
    assertEquals(Aquarium.height - 140, snail.getPosition().getOrdinat(), "The position ordinat should be aquarium.height - 140");
    assertEquals(0, snail.getSetpoint().getAbsis(), "The set point should be 0");
    assertEquals(0, snail.getSetpoint().getOrdinat(), "The set point should be 0");
  }

  @Test
  void move() {
    Aquarium aq = new Aquarium();
    Snail snail = new Snail();
    snail.move();
    assertEquals('l', snail.getOrientation(), "The orientation should be r");
  }

  @Test
  void searchFood() {
    Aquarium aq = new Aquarium();
    Snail snail = new Snail();
    Coin coin = new Coin(10, 50, snail.getPosition());
    aq.coins.add(coin);
    snail.searchFood();
    assertTrue(snail.isChase(), "The snail should be chasing");
  }

  @Test
  void takeCoin() {
    Aquarium aq = new Aquarium();
    Snail snail = new Snail();
    Coin coin = new Coin(10, 50, snail.getPosition());
    aq.coins.add(coin);
    int temp = aq.coin;
    snail.searchFood();
    int temp1 = aq.coin - temp;
    assertEquals(10, temp1, "The coin should be +10");
  }
}