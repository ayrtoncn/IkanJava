package Fish;
import Coin.CoinProducer;
import Point.Point;
import Aquarium.Aquarium;
import java.util.Date;
import java.util.Random;


public abstract class Fish implements CoinProducer {
  protected String name;
  protected boolean running;
  protected int price;
  protected boolean hungry;
  protected boolean dropCoin;
  protected double hungerPeriod;
  protected double coinPeriod;
  protected int movementSpeed;
  protected Point position;
  protected Point setpoint;
  protected char orientation;
  protected double delay;
  protected double start;
  protected double direction;
  protected double now;
  protected double prevtime;
  protected double secSinceLast;
  protected boolean chase;
  /**
   * Constructor of fish.
   * @param name = name of fish.
   * @param price = price of fish.
   * @param hungerPeriod = hunger period of fish.
   * @param coinPeriod = coin period of fish.
   * @param movementSpeed = movement speed of fish.
   * @param position = position of fish.
   * @param orientation = orientation of fish l / r.
   */
  
  public Fish(String name, int price, double hungerPeriod,
              double coinPeriod, int movementSpeed, Point position, char orientation) {
    this.name = name;
    this.price = price;
    this.hungry = false;
    this.dropCoin = false;
    this.hungerPeriod = hungerPeriod;
    this.coinPeriod = coinPeriod;
    this.movementSpeed = movementSpeed;
    this.position = position;
    this.setpoint = new Point(0,0);
    this.orientation = orientation;
    this.delay = 0;
    this.start = 0;
    this.direction = 0;
    this.now = 0;
    this.prevtime = 0;
    this.secSinceLast = 0;
    this.chase = false;
  }
  
  public String getName() {
    return name;
  }
  
  public int getPrice() {
    return price;
  }
  
  public boolean isHungry() {
    return hungry;
  }
  
  public boolean getDropCoin() {
    return dropCoin;
  }
  
  public double getHungerPeriod() {
    return hungerPeriod;
  }
  
  public double getCoinPeriod() {
    return coinPeriod;
  }
  
  public int getMovementSpeed() {
    return movementSpeed;
  }
  
  public Point getPosition() {
    return position;
  }
  
  public Point getsetpoint() {
    return setpoint;
  }
  
  public char getOrientation() {
    return orientation;
  }
  
  public double getDelay() {
    return delay;
  }
  
  public double getDirection() {
    return direction;
  }
  
  public void setDirection(double direction) {
    this.direction = direction;
  }
  
  public double getStart() {
    return start;
  }
  
  public double getNow() {
    return now;
  }
  
  public double getPrevtime() {
    return prevtime;
  }
  
  public double getSecSinceLast() {
    return secSinceLast;
  }
  
  public boolean isChase() {
    return chase;
  }
  
  public void setChase(boolean chase) {
    this.chase = chase;
  }
  
  public void setSecSinceLast(double secSinceLast) {
    this.secSinceLast = secSinceLast;
  }
  
  public void setPrevtime(double prevtime) {
    this.prevtime = prevtime;
  }
  
  public void setNow(double now) {
    this.now = now;
  }
  
  public void setStart(double start) {
    this.start = start;
  }
  
  public void setDelay(double delay) {
    this.delay = delay;
  }
  
  public void setOrientation(char orientation) {
    this.orientation = orientation;
  }
  
  public void setsetpoint(Point setpoint) {
    this.setpoint = setpoint;
  }
  
  public void setPosition(Point position) {
    this.position = position;
  }
  
  public void setMovementSpeed(int movementSpeed) {
    this.movementSpeed = movementSpeed;
  }
  
  public void setCoinPeriod(double coinPeriod) {
    this.coinPeriod = coinPeriod;
  }
  
  public void setHungerPeriod(double hungerPeriod) {
    this.hungerPeriod = hungerPeriod;
  }
  
  public void setDropCoin(boolean dropCoin) {
    this.dropCoin = dropCoin;
  }
  
  public void setHungry(boolean hungry) {
    this.hungry = hungry;
  }
  
  public void setPrice(int price) {
    this.price = price;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public boolean isRunning() {
    return running;
  }

  /**
   * move fish according to his/her setpoint point.
   *
   */
  
  public void move() {
    if (!chase) {
      if ((direction <= 3.14 && direction >= (3.14 / 2))
          || (direction >= -3.14 && direction <= -(3.14 / 2))) {
        orientation = 'l';
      } else {
        orientation = 'r';
      }
      if (position.getAbsis() <= 140) {
        direction = 0;
        start = System.nanoTime();
      } else if (position.getAbsis() >= Aquarium.width - 140) {
        direction = 3.14;
        start = System.nanoTime();
      } else if (position.getOrdinat() >= Aquarium.height - 140) {
        direction = -3.14 / 2;
        start = System.nanoTime();
      } else if (position.getOrdinat() <= 140) {
        direction = 3.14 / 2;
        start = System.nanoTime();
      }
      if ((System.nanoTime() - start) <=  delay) {
        
        secSinceLast /= 1000000000;
        position.setAbsis(position.getAbsis() + movementSpeed * secSinceLast * Math.cos(direction));
        position.setOrdinat(position.getOrdinat() + movementSpeed
            * secSinceLast * Math.sin(direction));
      } else {
        start = System.nanoTime();
        Random rand = new Random();
        delay = 1 + (3 * rand.nextDouble()) * 1000000000;
        direction = rand.nextInt(180) / 180.0;
        if (rand.nextBoolean()) {
          direction = direction * -1;
        }
      }
    } else {
      double dest;
      secSinceLast /= 1000000000;
      dest = Math.atan2(
        setpoint.getOrdinat() - position.getOrdinat(),setpoint.getAbsis() - position.getAbsis());
      if ((dest <= 3.14 && dest >= (3.14 / 2))
          || (dest >= -3.14 && dest <= -(3.14 / 2))) {
        orientation = 'l';
      } else {
        orientation = 'r';
      }
      position.setAbsis(position.getAbsis() + movementSpeed * secSinceLast * Math.cos(dest));
      position.setOrdinat(position.getOrdinat() + movementSpeed
          * secSinceLast * Math.sin(dest));
    }
  }
  
  
  //abstract public void dropCoin() ;
}
