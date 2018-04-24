
public class Food implements Runnable {
  private boolean running;
  private String name;
  private int movementSpeed;
  private Point position;
  private double foodNow;
  private double foodPrevTime;
  private double foodSecSinceLast;
  private Thread threadFood;
  private String threadName;
  
  /**
   * Constructor for class Food.
   * @param absis = x-coordinate of food.
   */
  public Food(double absis) {
    this.name = "Food";
    this.movementSpeed = 50;
    this.position = new Point(absis,0);
    this.threadName = name;
  }
  
  public boolean isRunning() {
    return running;
  }
  
  public String getName() {
    return name;
  }
  
  public int getMovementSpeed() {
    return movementSpeed;
  }
  
  public Point getPosition() {
    return position;
  }
  
  public double getFoodNow() {
    return foodNow;
  }
  
  public double getFoodPrevTime() {
    return foodPrevTime;
  }
  
  public double getFoodSecSinceLast() {
    return foodSecSinceLast;
  }
  
  public void setRunning(boolean running) {
    this.running = running;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setMovementSpeed(int movementSpeed) {
    this.movementSpeed = movementSpeed;
  }

  public void setPosition(Point position) {
    this.position = position;
  }

  public void setFoodNow(double foodNow) {
    this.foodNow = foodNow;
  }

  public void setFoodPrevTime(double foodPrevTime) {
    this.foodPrevTime = foodPrevTime;
  }

  public void setFoodSecSinceLast(double foodSecSinceLast) {
    this.foodSecSinceLast = foodSecSinceLast;
  }
  
  public void stop() {
    Aquarium.foods.del(Aquarium.foods.find(this));
    running = false;
  }
  
  /**
   * run thread.
   */
  public void run() {
    try {
      foodPrevTime = System.nanoTime();
      running = true;
      while (running) {
        Thread.sleep(1);
        foodNow = System.nanoTime();
        foodSecSinceLast = foodNow - foodPrevTime;
        foodPrevTime = foodNow;
        
        if (position.getOrdinat() >= Aquarium.height - 100) {
          stop();
        } else {
          position.setOrdinat(position.getOrdinat()
              + movementSpeed * foodSecSinceLast / 1000000000);
        }
      }
      // Let the thread sleep for a while.
      
      
    } catch (InterruptedException e) {
      System.out.println("Thread Food interrupted.");
    }
    System.out.println("Thread Food exiting.");
  }
  
  /**
   * start thread.
   */
  public void start() {
    if (threadFood == null) {
      threadFood = new Thread(this, threadName);
      threadFood.start();
    }
  }
}
