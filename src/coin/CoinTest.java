import static org.junit.jupiter.api.Assertions.*;
import coin.Coin;
import org.junit.jupiter.api.Test;
import point.Point;

class CoinTest {

  @Test
  void constructorTest() {
    Coin coin = new Coin(1,2,new Point(1,2));
    assertEquals(1,coin.getValue(), "The coin value should be 1");
    assertEquals(2,coin.getMovementSpeed(), "The movement speed should be 2");
    assertEquals(1,(int)coin.getPosition().getAbsis(), "coin's x position should be 1");
    assertEquals(2,(int)coin.getPosition().getOrdinat(), "coin's y position should be 2");
  }
  
  @Test
  void runAndStopTest() {
    Coin coin = new Coin(1,2,new Point(1,2));
    coin.start();
    assertEquals(true, coin.isRunning(), "The coin should be running");
    coin.stop();
    assertEquals(false, coin.isRunning(), "The coin should not be running");
  }
  
  @Test
  void runTest() {
    Coin coin = new Coin(1,500, new Point(100,100));
    coin.start();
    while(coin.isRunning()) {
      System.out.println(coin.getPosition().getOrdinat());
    };
    coin.stop();
    assertTrue(coin.getPosition().getOrdinat() >= 100.0, "The y location should be more than 100");
  }

}
