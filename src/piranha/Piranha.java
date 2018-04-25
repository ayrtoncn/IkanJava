package piranha;
import java.util.Random;
import fish.Fish;
import point.Point;
import aquarium.Aquarium;
import coin.Coin;

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
    super("piranha", piranhaPrice, piranhaHungryPeriod, piranhaCoinPeriod, piranhaMovementSpeed,
            position, orientation);
    threadName = name;
  }

  /**
   * Prosedur piranha memakan guppy.
   */
  public synchronized void eat(Fish g) {
    hungerPeriod = piranhaHungryPeriod;
    chase = false;
    hungry = false;
    int level;
    try {
      Aquarium.guppy.get(Aquarium.guppy.find(g)).stop();
      Aquarium.guppy.del(Aquarium.guppy.find(g));
      if (g.getGrowthLevel() <= 3) {
        level = 2;
      } else if (g.getGrowthLevel() <= 6) {
        level = 3;
      } else {
        level = 4;
      }
      dropCoin(g.getPrice() * level);
    } catch (Exception e) {
      System.out.println("out");
    }
  }

  /**
   * search nearest guppy.
   */
  public void searchFood() {
    Point pmin = new Point();
    double min = 999999999;
    int idx = -1;
    chase = false;
    try {
      for (int numGup = 0; numGup < Aquarium.guppy.getAmount(); numGup++) {
        double temp = position.getDistance(Aquarium.guppy.get(numGup).getPosition());
        if (min > temp) {
          min = temp;
          idx = numGup;
          pmin = Aquarium.guppy.get(numGup).getPosition();
        }
      }
      if (idx != -1) {
        chase = true;
        setpoint = pmin;
        if (this.getPosition().getAbsis() + 50
                >= Aquarium.guppy.get(idx).getPosition().getAbsis()
                && this.getPosition().getAbsis() - 50
                <= Aquarium.guppy.get(idx).getPosition().getAbsis()
                && this.getPosition().getOrdinat() + 50
                >= Aquarium.guppy.get(idx).getPosition().getOrdinat()
                && this.getPosition().getOrdinat() - 50
                <= Aquarium.guppy.get(idx).getPosition().getOrdinat()) {
          eat(Aquarium.guppy.get(idx));
        }
      }
    } catch (Exception e) {
      System.out.println("out");
    }
  }

  public int getGrowthLevel() {
    return 1;
  }

  public void stop() {
    running = false;
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
    running = true;
    while (running) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.out.println("Thread piranha interrupted.");
      }
      now = System.nanoTime();
      secSinceLast = now - prevtime;
      hungerPeriod -= secSinceLast / 1000000000;
      coinPeriod -= secSinceLast / 1000000000;
      prevtime = now;
      if (hungerPeriod <= 10 && hungerPeriod >= 0) {
        hungry = true;
        searchFood();
      } else if (hungerPeriod < 0) {
        stop();
        Aquarium.piranha.del(Aquarium.piranha.find(this));
      }

      move();
    }
    System.out.println("Thread piranha exiting.");
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

  /**
   * create new coin on piraha position after eating guppy.
   * @param value = guppy.growthLevel * guppy.price.
   */
  public void dropCoin(int value) {
    Coin temp = new Coin(value,50,new Point(position.getAbsis(),position.getOrdinat() + 20));
    temp.start();
    Aquarium.coins.add(temp);
  }
}
