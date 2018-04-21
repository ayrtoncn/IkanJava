

import java.util.Date;
import java.util.Random;

public class Piranha extends Fish {
  private static int piranhaCoinPeriod;
  private static int piranhaHungryPeriod;
  private static int piranhaMovementSpeed;
  private static int piranhaPrice;
  
  public Piranha(Point position, char orientation) {
    super("Piranha", piranhaPrice, piranhaHungryPeriod, piranhaCoinPeriod, piranhaMovementSpeed,
        position, orientation);
  }

  /**Prosedur Piranha memakan Guppy.
   * 
   */
  public void eat() {
    hungerPeriod = 15;
    Point p = new Point();
    destination = p;
    hungry = false;
  }
  
  /**Controller Class Piranha.
   * 
   */
  public void piranhaController() {
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
