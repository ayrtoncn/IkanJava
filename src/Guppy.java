
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
  
  /**
   * create new coin on guppy position.
   * @param value =  value of coins.
   */
  public void dropCoin(int value) {
    coinPeriod = guppyCoinPeriod;
    Coin temp = new Coin(value,50,new Point(position.getAbsis(),position.getOrdinat() + 20));
    temp.start();
    Aquarium.coins.add(temp);
  }
  
  /**
   * search nearest food.
   */
  public void searchFood() {
    Point pmin = new Point();
    double min = 999999999;
    int idx = -1;
    chase = false;
    for (int numFood = 0; numFood < Aquarium.foods.getAmount(); numFood++) {
      double temp = position.getDistance(Aquarium.foods.get(numFood).getPosition());
      if (min > temp) {
        min = temp;
        idx = numFood;
        pmin = Aquarium.foods.get(numFood).getPosition();
      }
    }
    if (min != 999999999) {
      chase = true;
      setpoint = pmin;
      if (this.getPosition().getAbsis() + 50
          >= Aquarium.foods.get(idx).getPosition().getAbsis()
          && this.getPosition().getAbsis() - 50
          <= Aquarium.foods.get(idx).getPosition().getAbsis()
          && this.getPosition().getOrdinat() + 50
          >= Aquarium.foods.get(idx).getPosition().getOrdinat()
          && this.getPosition().getOrdinat() - 50
          <= Aquarium.foods.get(idx).getPosition().getOrdinat()) {
        eat(Aquarium.foods.get(idx));
      }
    }
  }
  
  /**
   * Prosedur Guppy makan Food.
   */
  public synchronized void eat(Food f) {
    hungerPeriod = guppyHungerPeriod;
    chase = false;
    hungry = false;
    growthLevel++;
    int foodIndex = Aquarium.foods.find(f);
    try {
      Aquarium.foods.get(foodIndex).stop();
    } catch (Exception e) {
      System.out.println("Eat exception");
    }
  }
  
  public  void stop() {
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
        System.out.println("Thread Guppy interrupted.");
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
        Aquarium.guppy.del(Aquarium.guppy.find(this));
        running = false;
      }

      if (coinPeriod <= 0) {
        int value;
        if (growthLevel <= 3) {
          value = 10;
        } else if (growthLevel <= 6) {
          value = 20;
        } else {
          value = 30;
        }
        dropCoin(value);
      }
      move();
    }
    System.out.println("Thread Guppy exiting.");
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
