import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Aquarium extends JPanel {
  public static int width = 1000;
  public static int height = 800;
  private int numFish;
  private int numGuppy;
  private int numPiran;
  private int numFood;
  private int numEggs;
  private int numCoin;
  private int numObject;
  private int coin;
  private char input;
  private LinkedList<Guppy> guppy;
  private LinkedList<Piranha> piranha;
  private LinkedList<Coin> coins;
  //private LinkedList<Food> foods;
  static public Snail snail;
  private boolean menu;
  public static JFrame f = new JFrame();
  
  /**
   * run berisi kode untuk mengatur interaksi antar objek dan mengatur input - input .
   */
  
  public static void main(String[] args)  {
    snail = new Snail();
    Aquarium m = new Aquarium();
    f.add(m);
    f.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        //mouse left button click
        if (e.getButton() == MouseEvent.BUTTON1) {
          Point p = new Point(e.getX(),e.getY());
          snail.setSetpoint(p);
          snail.setChase(true);
          System.out.println(e.getX() + " " + e.getY());
        }
      }
    });
    f.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_B) {
          System.out.println("b");
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
          System.out.println("P");
        }
      }
    });
    f.setSize(width,height);
    f.setVisible(true);
    snail.start();
  }
  
  /**
   * Mengatur gambar dari objek.
   * @param g = Graphics.
   */
  public void paint(Graphics g) {
    
    Toolkit t = Toolkit.getDefaultToolkit();
    if (Aquarium.snail.getOrientation() == 'l') {
      g.drawImage(t.getImage("src/img/snailkiri.png"),(int)Aquarium.snail.getPosition().getAbsis(),
        (int)Aquarium.snail.getPosition().getOrdinat(),this);
    } else {
      g.drawImage(t.getImage("src/img/snailkanan.png"),(int)Aquarium.snail.getPosition().getAbsis(),
        (int)Aquarium.snail.getPosition().getOrdinat(),this);
    }
    f.repaint();
//    for (int i = 0; i < ikan.size();i++) {
//      g.drawImage(t.getImage("src/ikankiri.png"), (int)ikan.get(i).getPosition().getAbsis(),
//          (int)ikan.get(i).getPosition().getOrdinat(),this);
//  }
  }
}
