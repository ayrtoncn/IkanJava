

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Guppy extends Fish {
  private int growthLevel;
  private static double guppyCoinPeriod = 5;
  private static double guppyHungerPeriod = 15;
  private static int guppyMovementSpeed = 80;
  private static int guppyPrice = 10;

  /**Constructor Guppy.
   * 
   * @param position = posisi Guppy
   * @param orientation = orientasi arah Guppy
   */
  public Guppy(Point position, char orientation) {
    super("Guppy", guppyPrice, guppyHungerPeriod, guppyCoinPeriod, guppyMovementSpeed,
        position, orientation);
    growthLevel = 0;
  }
  
  public int getGrowthLevel() {
    return growthLevel;
  }
  
  public void setGrowthLevel(int growthLevel) {
    this.growthLevel = growthLevel;
  }
  
  /**Prosedur Guppy makan Food.
   * 
   */
  public void eat() {
    hungerPeriod = 15;
    Point p = new Point();
    destination = p;
    hungry = false;
    growthLevel++;
  }
  
  /**Controller for Class Guppy.
   * 
   */
  public void guppyController() {
    Random rand = new Random();
    prevtime = System.nanoTime();
    delay = rand.nextDouble() % 4 + 1;
    direction = rand.nextDouble() % 8 + 1;
    Date timestamp = new Date();
    start = timestamp.getTime();
    boolean running = true;
    while (running) {
      now = System.nanoTime();
      secSinceLast = now - prevtime;
      hungerPeriod -= secSinceLast;
      coinPeriod -= secSinceLast;
      prevtime = now;
      if (hungerPeriod <= 10 && hungerPeriod >= 0) {
        hungry = true;
      } else if (hungerPeriod < 0) {
        name = "die";
      }
      if (coinPeriod <= 0) {
        dropCoin = true;
      } else {
        dropCoin = false; 
      }
      move();
    }
    position.setAbsis(-100);
    position.setOrdinat(-100);
  }
}
