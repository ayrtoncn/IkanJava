import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

final class Aquarium extends JPanel {
  public static int width = 1000;
  public static int height = 800;
  public static int coin;
  public static LinkedList<Guppy> guppy;
  public static LinkedList<Piranha> piranha;
  public static LinkedList<Coin> coins;
  public static LinkedList<Food> foods;
  private Snail snail;
  private boolean menu;
  private int egg;
  private boolean running;
  private boolean win;
  private boolean lose;
  public static int eggPrice = 100;
  public static JFrame f = new JFrame();
  private Timer timer;
  
  public Aquarium() {
    coin = 100;
    menu = true;
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
            if (!menu && coins.get(numCoin).getPosition().getAbsis() <= e.getX() + 50
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
          if (!menu && !cointake && (coin - 5) >= 0) {
            coin = coin - 5;
            foods.add(new Food(e.getX()));
            foods.get(foods.getAmount() - 1).start();
          }
          if (menu && width / 2 <= e.getX() + 50
              && width / 2 >= e.getX() - 50
              && height / 2 >= e.getY() - 50
              && height / 2 <= e.getY() + 50) {
            menu = false;
          }
        }
      }
    });
    
    f.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (!menu && e.getKeyCode() == KeyEvent.VK_G && (coin - 10) >= 0) {
          coin = coin - 10;
          Random rand = new Random();
          guppy.add(new Guppy(new Point(rand.nextInt(width - 280)
              + 140,rand.nextInt(height - 100) + 50),'l'));
          guppy.get(guppy.getAmount() - 1).start();
        } else if (!menu && e.getKeyCode() == KeyEvent.VK_P && (coin - 20) >= 0) {
          coin -= 20;
          Random rand = new Random();
          piranha.add(new Piranha(new Point(rand.nextInt(width - 300)
              + 250,rand.nextInt(height - 100) + 50),'l'));
          piranha.get(piranha.getAmount() - 1).start();
        } else if (!menu && e.getKeyCode() == KeyEvent.VK_ENTER && (coin - eggPrice) >= 0) {
          coin -= eggPrice;
          egg++;
        } else if (e.getKeyCode() == KeyEvent.VK_X) {
          System.exit(0);
        }
      }
    });
    f.setSize(width,height);
    f.setVisible(true);
    snail.start();
    running = true;
    while (running) {
      if (egg == 3) {
        win  = true;
        running = false;
      }
      if ((coin - 10 < 0) && (coins.getAmount() == 0)
          && (guppy.getAmount() + piranha.getAmount() == 0)) {
        running = false;
        lose = true;
      }
    }
    
  }
  
  /**
   * Mengatur gambar dari objek.
   * @param g = Graphics.
   */
  public synchronized void paint(Graphics g) {
    
    Toolkit t = Toolkit.getDefaultToolkit();
    g.setFont(new Font("TimesRoman",Font.PLAIN, 30));
    if (menu) {
      g.drawImage(t.getImage("src/img/bg1.jpg"),0, 0,this);
      g.drawImage(t.getImage("src/img/start.png"),(width - 140) / 2, (height - 140) / 2,this);
      String current = new String();
      try {
        current = new java.io.File( "." ).getCanonicalPath();
      } catch (Exception e) {
        System.out.println("Current dir:" + current);
      }
    } else {
      if (win) {
        g.drawImage(t.getImage("src/img/bg1.jpg"),0, 0,this);
        g.drawString("WIN",50,50);
      } else if (lose) {
        g.drawImage(t.getImage("src/img/bg1.jpg"),0, 0,this);
        g.drawString("LOSE",50,50);
      } else {
        
        g.drawImage(t.getImage("src/img/bg1.jpg"),0, 0,this);
        String text = "";
        text = text + coin;
        g.drawString(text,50,50);
        text = "";
        text += egg;
        g.drawString(text, 500,50);
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
              if (guppy.get(numGup).isHungry()) {
                link = "src/img/ikankiri1laper.png";
              } else {
                link = "src/img/ikankiri1.png";
              }
            } else if (guppy.get(numGup).getGrowthLevel() <= 6) {
              if (guppy.get(numGup).isHungry()) {
                link = "src/img/ikankiri2laper.png";
              } else {
                link = "src/img/ikankiri2.png";
              }
            } else {
              if (guppy.get(numGup).isHungry()) {
                link = "src/img/ikankiri3laper.png";
              } else {
                link = "src/img/ikankiri3.png";
              }
            }
            g.drawImage(t.getImage(link), (int)guppy.get(numGup).getPosition().getAbsis(),
                (int)guppy.get(numGup).getPosition().getOrdinat(),this);
          } else {
            String link;
            if (guppy.get(numGup).getGrowthLevel() <= 3) {
              if (guppy.get(numGup).isHungry()) {
                link = "src/img/ikankanan1laper.png";
              } else {
                link = "src/img/ikankanan1.png";
              }
            } else if (guppy.get(numGup).getGrowthLevel() <= 6) {
              if (guppy.get(numGup).isHungry()) {
                link = "src/img/ikankanan2laper.png";
              } else {
                link = "src/img/ikankanan2.png";
              }
            } else {
              if (guppy.get(numGup).isHungry()) {
                link = "src/img/ikankanan3laper.png";
              } else {
                link = "src/img/ikankanan3.png";
              }
            }
            g.drawImage(t.getImage(link), (int)guppy.get(numGup).getPosition().getAbsis(),
                (int)guppy.get(numGup).getPosition().getOrdinat(),this);
          }
        }
        for (int numPin = 0;numPin < piranha.getAmount();numPin++) {
          if (piranha.get(numPin).getOrientation() == 'l') {
            if (!piranha.get(numPin).isHungry()) {
              g.drawImage(t.getImage("src/img/piranhakiri.png"),
                  (int) piranha.get(numPin).getPosition().getAbsis(),
                  (int) piranha.get(numPin).getPosition().getOrdinat(), this);
            } else {
              g.drawImage(t.getImage("src/img/piranhakirilaper.png"),
                  (int) piranha.get(numPin).getPosition().getAbsis(),
                  (int) piranha.get(numPin).getPosition().getOrdinat(), this);
            }
          } else {
            if (!piranha.get(numPin).isHungry()) {
              g.drawImage(t.getImage("src/img/piranhakanan.png"),
                  (int) piranha.get(numPin).getPosition().getAbsis(),
                  (int) piranha.get(numPin).getPosition().getOrdinat(), this);
            } else {
              g.drawImage(t.getImage("src/img/piranhakananlaper.png"),
                  (int) piranha.get(numPin).getPosition().getAbsis(),
                  (int) piranha.get(numPin).getPosition().getOrdinat(), this);
            }
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
      }
    }
    
    f.repaint();
  }
  

}
