import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

final class Aquarium extends JPanel {
  public static int width = 1000;
  public static int height = 800;
  public static int coin;
  private char input;
  public static LinkedList<Guppy> guppy;
  public static LinkedList<Piranha> piranha;
  public static LinkedList<Coin> coins;
  public static LinkedList<Food> foods;
  private Snail snail;
  private boolean menu;
  public static JFrame f = new JFrame();
  
  public Aquarium() {
    coin = 100;
    snail = new Snail();
    guppy = new LinkedList<>();
    piranha =  new LinkedList<>();
    foods = new LinkedList<>();
    coins = new LinkedList<>();
  }
  /**
   * run berisi kode untuk mengatur interaksi antar objek dan mengatur input - input .
   */
  
  public void run() {
    f.add(this);
    f.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (e.getButton() == MouseEvent.BUTTON1) {
          boolean cointake = false;
          for (int numCoin = 0; numCoin < coins.getAmount(); numCoin++) {
            if (coins.get(numCoin).getPosition().getAbsis() <= e.getX() + 50
                && coins.get(numCoin).getPosition().getAbsis() >= e.getX() - 50
                && coins.get(numCoin).getPosition().getOrdinat() >= e.getY() - 50
                && coins.get(numCoin).getPosition().getOrdinat() <= e.getY() + 50) {
              coin += coins.get(numCoin).getValue();
              coins.get(numCoin).stop();
              coins.del(coins.find(coins.get(numCoin)));
              cointake = true;
              break;
            }
          }
          if (!cointake) {
            coin = coin - 5;
            foods.add(new Food(e.getX()));
            foods.get(foods.getAmount() - 1).start();
          }
        }
      }
    });

    f.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_G) {
          coin = coin - 10;
          guppy.add(new Guppy(new Point(50,100),'l'));
          guppy.get(guppy.getAmount() - 1).start();
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
          coin = coin - 20;
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
    g.setFont(new Font("TimesRoman",Font.PLAIN, 30));
    g.drawImage(t.getImage("src/img/bg1.jpg"),0, 0,this);
    String text = "";
    text = text + coin;
    g.drawString(text,50,50);
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
        if (guppy.get(numGup).getGrowthLevel() <= 3) {
          link = "src/img/ikankiri1.png";
        } else if (guppy.get(numGup).getGrowthLevel() <= 6) {
          link = "src/img/ikankiri2.png";
        } else {
          link = "src/img/ikankiri3.png";
        }
        g.drawImage(t.getImage(link), (int)guppy.get(numGup).getPosition().getAbsis(),
            (int)guppy.get(numGup).getPosition().getOrdinat(),this);
      } else {
        String link;
        if (guppy.get(numGup).getGrowthLevel() <= 3) {
          link = "src/img/ikankanan1.png";
        } else if (guppy.get(numGup).getGrowthLevel() <= 6) {
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
    for (int numFood = 0; numFood < foods.getAmount(); numFood++) {
      g.drawImage(t.getImage("src/img/pelet.png"),
          (int) foods.get(numFood).getPosition().getAbsis(),
          (int) foods.get(numFood).getPosition().getOrdinat(), this);
    }
    for (int numCoin = 0; numCoin < coins.getAmount(); numCoin++) {
      g.drawImage(t.getImage("src/img/coin.png"),
          (int) coins.get(numCoin).getPosition().getAbsis(),
          (int) coins.get(numCoin).getPosition().getOrdinat(), this);
    }
    f.repaint();
  }
  

}
