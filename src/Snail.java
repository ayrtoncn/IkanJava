

public class Snail extends Thread implements CoinGatherer {
  private boolean running;
  private int orientation;
  private int movementSpeed;
  private Point position;
  private Point setpoint;
  private double snailNow;
  private double snailPrevtime;
  private double snailSecSinceLast;
  private boolean chase;
  private Thread tsnail;
  private String threadName;
  
  /**
   * constructor of snail.
   */
  public Snail() {
    this.running = true;
    this.orientation = 'l';
    this.movementSpeed = 30;
    this.position = new Point(80,660);
    this.setpoint = new Point(0,0);
    this.snailNow = 0;
    this.snailPrevtime = 0;
    this.snailSecSinceLast = 0;
    this.chase = false;
    this.threadName = "Gary";
  }
  
  public void takeCoin() {
    
  }

  public boolean isRunning() {
    return running;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  public int getOrientation() {
    return orientation;
  }

  public void setOrientation(int orientation) {
    this.orientation = orientation;
  }

  public int getMovementSpeed() {
    return movementSpeed;
  }

  public void setMovementSpeed(int movementSpeed) {
    this.movementSpeed = movementSpeed;
  }

  public Point getPosition() {
    return position;
  }

  public void setPosition(Point position) {
    this.position = position;
  }

  public Point getSetpoint() {
    return setpoint;
  }

  public void setSetpoint(Point setpoint) {
    this.setpoint = setpoint;
  }

  public double getSnailNow() {
    return snailNow;
  }

  public void setSnailNow(double snailNow) {
    this.snailNow = snailNow;
  }

  public double getSnailPrevtime() {
    return snailPrevtime;
  }

  public void setSnailPrevtime(double snailPrevtime) {
    this.snailPrevtime = snailPrevtime;
  }

  public double getSnailSecSinceLast() {
    return snailSecSinceLast;
  }

  public void setSnailSecSinceLast(double snailSecSinceLast) {
    this.snailSecSinceLast = snailSecSinceLast;
  }

  public boolean isChase() {
    return chase;
  }

  public void setChase(boolean chase) {
    this.chase = chase;
  }
  
  /**
   * move snail to nearest coin.
   */
  public void move() {
    double dest;
    dest = Math.atan2(0,setpoint.getAbsis() - position.getAbsis());
    if (dest == 0) {
      orientation = 'r';
    } else {
      orientation = 'l';
    }
    snailSecSinceLast /= 1000000000;
    position.setAbsis(position.getAbsis() + movementSpeed * snailSecSinceLast * Math.cos(dest));
  }
  public void run() {
    try {
      snailPrevtime = System.nanoTime();
      chase = false;
      running = true;
      while (running) {
        Thread.sleep(50);
        snailNow = System.nanoTime();
        snailSecSinceLast = snailNow - snailPrevtime;
        snailPrevtime = snailNow;
        if (chase) {
          
          if (!(setpoint.getAbsis() - 10 <= position.getAbsis() && setpoint.getAbsis() + 10 >= position.getAbsis())) {
            move();
          }
        }
      }
        // Let the thread sleep for a while.
        
      
    } catch (InterruptedException e) {
      System.out.println("Thread Snail interrupted.");
    }
    System.out.println("Thread Snail exiting.");
  }
  public void start () {
    if (tsnail == null) {
      tsnail = new Thread (this, threadName);
      tsnail.start ();
    }
  }
}
