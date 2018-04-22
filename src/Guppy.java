
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Guppy extends Fish implements Runnable {
  private int growthLevel;
  private static double guppyCoinPeriod = 5;
  private static double guppyHungerPeriod = 15;
  private static int guppyMovementSpeed = 80;
  private static int guppyPrice = 10;
  private Thread threadGuppy;
  private String threadName;

  /**Constructor Guppy.
   * 
   * @param position = posisi Guppy
   * @param orientation = orientasi arah Guppy
   */
  public Guppy(Point position, char orientation) {
    super("Guppy", guppyPrice, guppyHungerPeriod, guppyCoinPeriod, guppyMovementSpeed,
        position, orientation);
    growthLevel = 0;
    threadName = name;
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
    chase = false;
    hungry = false;
    growthLevel++;
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
      hungerPeriod -= secSinceLast;
      secSinceLast = now - prevtime;
      coinPeriod -= secSinceLast;
      prevtime = now;
      if (hungerPeriod <= 10 && hungerPeriod >= 0) {
        hungry = true;
      } else if (hungerPeriod < 0) {
        name = "die";
      }
      dropCoin = coinPeriod <= 0;
      move();
    }
    position.setAbsis(-100);
    position.setOrdinat(-100);
  }
  
  /**
   * start thread.
   */
  public void start() {
    if (threadGuppy == null) {
      threadGuppy = new Thread(this, threadName);
      threadGuppy.start();
    }
  }
}
