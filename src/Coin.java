

public class Coin {
  private boolean running;
  private int value;
  private int movementSpeed;
  private Point position;
  private int radius;
  private double coinNow;
  private double coinPrevTime;
  private double coinSecSinceLast;
  
  /**
   * Constructor for class Coin.
   * @param value = value of coin.
   * @param movementSpeed = movement speed (down) of coin.
   * @param position = initial coordinate of coin.
   */
  public Coin(int value, int movementSpeed, Point position) {
    this.value = value;
    this.movementSpeed = movementSpeed;
    this.position = position;
  }
  
  public boolean isRunning() {
    return running;
  }
  
  public int getValue() {
    return value;
  }
  
  public int getMovementSpeed() {
    return movementSpeed;
  }
  
  public Point getPosition() {
    return position;
  }
  
  public int getRadius() {
    return radius;
  }
  
  public double getCoinNow() {
    return coinNow;
  }
  
  public double getCoinPrevTime() {
    return coinPrevTime;
  }
  
  public double getCoinSecSinceLast() {
    return coinSecSinceLast;
  }
  
  public void setRunning(boolean running) {
    this.running = running;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public void setMovementSpeed(int movementSpeed) {
    this.movementSpeed = movementSpeed;
  }

  public void setPosition(Point position) {
    this.position = position;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public void setCoinNow(double coinNow) {
    this.coinNow = coinNow;
  }

  public void setCoinPrevTime(double coinPrevTime) {
    this.coinPrevTime = coinPrevTime;
  }

  public void setCoinSecSinceLast(double coinSecSinceLast) {
    this.coinSecSinceLast = coinSecSinceLast;
  }
  
}
