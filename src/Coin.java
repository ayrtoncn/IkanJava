

public class Coin implements Runnable {
  private boolean running;
  private int value;
  private int movementSpeed;
  private Point position;
  private int radius;
  private double coinNow;
  private double coinPrevTime;
  private double coinSecSinceLast;
  private Thread threadCoin;
  private String threadName;
  
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
    threadName = "Coins";
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
  
  public void stop() {
    running = false;
  }
  
  /**
   * run thread.
   */
  public void run() {
    try {
      coinPrevTime = System.nanoTime();
      running = true;
      while (running) {
        Thread.sleep(50);
        coinNow = System.nanoTime();
        coinSecSinceLast = coinNow - coinPrevTime;
        coinPrevTime = coinNow;
        if (position.getOrdinat() >= Aquarium.height - 100) {
          running = false;
        } else {
          position.setOrdinat(position.getOrdinat()
            + movementSpeed * coinSecSinceLast / 1000000000);
        }
      }
      // Let the thread sleep for a while.
      
    } catch (InterruptedException e) {
      System.out.println("Thread Coin interrupted.");
    }
    System.out.println("Thread Coin exiting.");
  }
  
  /**
   * start thread.
   */
  public void start() {
    if (threadCoin == null) {
      threadCoin = new Thread(this, threadName);
      threadCoin.start();
    }
  }
}
