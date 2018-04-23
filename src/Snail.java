

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
  private Thread threadSnail;
  private String threadName;
  
  /**
   * constructor of snail.
   */
  public Snail() {
    this.running = true;
    this.orientation = 'l';
    this.movementSpeed = 50;
    this.position = new Point(80,Aquarium.height - 140);
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
  
  /**
   * find nearest coin on floor or nearest coin from bottom.
   */
  public void searchFood() {
    Point pmin = new Point();
    Point pminFloor = new Point();
    double min = 999999999;
    double minFloor = 999999999;
    int idx = -1;
    int idxFloor = -1;
    chase = false;
    for (int numCoin = 0; numCoin < Aquarium.coins.getAmount(); numCoin++) {
      double temp = Aquarium.height - Aquarium.coins.get(numCoin).getPosition().getOrdinat();
      double tempFloor = Math.sqrt(Math.pow((position.getAbsis()
          - Aquarium.coins.get(numCoin).getPosition().getAbsis()),2));
      if (Aquarium.coins.get(numCoin).getPosition().getOrdinat() >= Aquarium.height - 100) {
        if (minFloor > tempFloor) {
          minFloor = tempFloor;
          idxFloor = numCoin;
          pminFloor = Aquarium.coins.get(numCoin).getPosition();
        }
      } else {
        if (min > temp) {
          min = temp;
          idx = numCoin;
          pmin = Aquarium.coins.get(numCoin).getPosition();
        }
      }
    }
    if (idxFloor != -1) {
      chase = true;
      setpoint = pminFloor;
      if (position.getAbsis() - 50
          <= Aquarium.coins.get(idxFloor).getPosition().getAbsis()
          && position.getAbsis() + 50
          >= Aquarium.coins.get(idxFloor).getPosition().getAbsis()
          && position.getOrdinat() + 50
          >= Aquarium.coins.get(idxFloor).getPosition().getOrdinat()
          && position.getOrdinat() - 50
          <= Aquarium.coins.get(idxFloor).getPosition().getOrdinat()) {
        eat(Aquarium.coins.get(idxFloor));
      }
    } else if (idx != -1) {
      chase = true;
      setpoint = pmin;
      if (this.getPosition().getAbsis() + 50 >= Aquarium.coins.get(idx).getPosition().getAbsis()
          && this.getPosition().getAbsis() - 50
          <= Aquarium.coins.get(idx).getPosition().getAbsis()
          && this.getPosition().getOrdinat() + 50
          >= Aquarium.coins.get(idx).getPosition().getOrdinat()
          && this.getPosition().getOrdinat() - 50
          <= Aquarium.coins.get(idx).getPosition().getOrdinat()) {
        eat(Aquarium.coins.get(idx));
      }
    }
  }
  
  /**
   * Prosedur Guppy makan Food.
   */
  public void eat(Coin c) {
    Aquarium.coins.get(Aquarium.coins.find(c)).stop();
    Aquarium.coins.del(Aquarium.coins.find(c));
    Aquarium.coin += c.getValue();
  }
  
  /**
   * run thread.
   */
  public void run() {
    try {
      snailPrevtime = System.nanoTime();
      chase = false;
      running = true;
      while (running) {
        Thread.sleep(1);
        snailNow = System.nanoTime();
        snailSecSinceLast = snailNow - snailPrevtime;
        snailPrevtime = snailNow;
        searchFood();
        if (chase) {
          if (!(setpoint.getAbsis() - 10 <= position.getAbsis()
              && setpoint.getAbsis() + 10 >= position.getAbsis())) {
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
  
  /**
   * start thread.
   */
  public void start() {
    if (threadSnail == null) {
      threadSnail = new Thread(this, threadName);
      threadSnail.start();
    }
  }
}
