package point;

public class Point {
  private double absis;
  private double ordinat;
  
  public Point() {
    absis = -99;
    ordinat = -99;
  }
  
  public Point(double absis,double ordinat) {
    this.absis = absis;
    this.ordinat = ordinat;
  }
  
  public double getOrdinat() {
    return ordinat;
  }
  
  public void setOrdinat(double ordinat) {
    this.ordinat = ordinat;
  }
  
  public double getAbsis() {
    return absis;
  }
  
  public void setAbsis(double absis) {
    this.absis = absis;
  }
  
  public double getDistance(Point point) {
    return Math.sqrt((absis - point.absis) * (absis - point.absis)
        + (ordinat - point.ordinat) * (ordinat - point.ordinat));
  }
}
