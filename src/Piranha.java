
import java.util.Date;
import java.util.Random;

public class Piranha extends Fish implements  Runnable {
  private static int piranhaCoinPeriod = 5;
  private static int piranhaHungryPeriod = 15;
  private static int piranhaMovementSpeed = 100;
  private static int piranhaPrice = 20;
  private Thread threadPiranha;
  private String threadName;
  
  /**
   * constructor for piranha.
   * @param position = piranha position for the first time.
   * @param orientation = orientation of piranha for the first time.
   */
  public Piranha(Point position, char orientation) {
    super("Piranha", piranhaPrice, piranhaHungryPeriod, piranhaCoinPeriod, piranhaMovementSpeed,
        position, orientation);
    threadName = name;
  }

  /**
   * Prosedur Piranha memakan Guppy.
   */
  public void eat() {
    hungerPeriod = 15;
    chase = false;
    hungry = false;
  }
  
  /**
   * run thread.
   */
  public void run() {
    prevtime = System.nanoTime();
    Random rand = new Random();
    delay =  1 + rand.nextDouble() * 3 * 1000000000;
    direction = rand.nextInt(180) / 180.0;
    if (rand.nextBoolean()) {
      direction = direction * -1;
    }
    start = System.nanoTime();
    boolean running = true;
    while (running) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        System.out.println("Thread Guppy interrupted.");
      }
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
  
  /**
   * start thread.
   */
  public void start() {
    if (threadPiranha == null) {
      threadPiranha = new Thread(this, threadName);
      threadPiranha.start();
    }
  }
}
