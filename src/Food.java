


public class Food {
  private boolean running;
  private String name;
  private int movementSpeed;
  private Point position;
  private double foodNow;
  private double foodPrevTime;
  private double foodSecSinceLast;
  
  /**
   * Constructor for class Food.
   * @param absis = x-coordinate of food.
   */
  public Food(double absis) {
    name = "";
    movementSpeed = 20;
    position.setAbsis(absis);
    position.setOrdinat(0);
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
    running = false;
  }

}
