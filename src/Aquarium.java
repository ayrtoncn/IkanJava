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
  private Snail snail;
  private boolean menu;
  public static JFrame f = new JFrame();
  
  /**
   * run berisi kode untuk mengatur interaksi antar objek dan mengatur input - input .
   */
  
  public void run()  {
    snail = new Snail();
    guppy = new LinkedList<>();
    piranha =  new LinkedList<>();
    f.add(this);
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
        if (e.getKeyCode() == KeyEvent.VK_G) {
          guppy.add(new Guppy(new Point(50,100),'l'));
          guppy.get(guppy.getAmount() - 1).start();
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
          piranha.add(new Piranha(new Point(50,100),'l'));
          piranha.get(piranha.getAmount() - 1).start();
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
    g.drawImage(t.getImage("src/img/bg1.jpg"),0, 0,this);
    if (snail.getOrientation() == 'l') {
      g.drawImage(t.getImage("src/img/snailkiri.png"),(int)snail.getPosition().getAbsis(),
          (int)snail.getPosition().getOrdinat(),this);
    } else {
      g.drawImage(t.getImage("src/img/snailkanan.png"),(int)snail.getPosition().getAbsis(),
          (int)snail.getPosition().getOrdinat(),this);
    }
    for (int numGup = 0;numGup < guppy.getAmount();numGup++) {
      if (guppy.get(numGup).getOrientation() == 'l') {
        String link;
        if (guppy.get(numGup).getGrowthLevel() < 3) {
          link = "src/img/ikankiri1.png";
        } else if (guppy.get(numGup).getGrowthLevel() < 6) {
          link = "src/img/ikankiri2.png";
        } else {
          link = "src/img/ikankiri3.png";
        }
        g.drawImage(t.getImage(link), (int)guppy.get(numGup).getPosition().getAbsis(),
            (int)guppy.get(numGup).getPosition().getOrdinat(),this);
      } else {
        String link;
        if (guppy.get(numGup).getGrowthLevel() < 3) {
          link = "src/img/ikankanan1.png";
        } else if (guppy.get(numGup).getGrowthLevel() < 6) {
          link = "src/img/ikankanan2.png";
        } else {
          link = "src/img/ikankanan3.png";
        }
        g.drawImage(t.getImage(link), (int)guppy.get(numGup).getPosition().getAbsis(),
            (int)guppy.get(numGup).getPosition().getOrdinat(),this);
      }
    }
    for (int numPin = 0;numPin < piranha.getAmount();numPin++) {
      if (piranha.get(numPin).getOrientation() == 'l') {
        g.drawImage(t.getImage("src/img/piranhakiri.png"),
            (int) piranha.get(numPin).getPosition().getAbsis(),
            (int) piranha.get(numPin).getPosition().getOrdinat(), this);
      } else {
        g.drawImage(t.getImage("src/img/piranhakanan.png"),
            (int) piranha.get(numPin).getPosition().getAbsis(),
            (int) piranha.get(numPin).getPosition().getOrdinat(), this);
      }
    }
    f.repaint();
  }
}
