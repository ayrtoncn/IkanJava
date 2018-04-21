
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import javax.swing.border.EmptyBorder;

public class Design extends JPanel {
  public static ArrayList<Guppy> ikan = new ArrayList<>();
  
  /**
   * Main program.
   * @param args = argumen.
   * @throws IOException = wrong input exception.
   */
  public static void main(String[] args)  {
    
    Guppy f1 = new Guppy(new Point(10,10),'l');
    Guppy f2 = new Guppy(new Point(30,30),'l');
    Guppy f3 = new Guppy(new Point(60,30),'l');
    ikan.add(f1);
    ikan.add(f2);
    Design m = new Design();
    JFrame f = new JFrame();
    f.add(m);
    f.setSize(400,400);
    f.setVisible(true);
    Point p = new Point(60,200);
    ikan.get(1).setPosition(p);
    ikan.get(1).setPrevtime(System.nanoTime());
    System.out.println(ikan.get(1).getPosition().getAbsis());
    System.out.println(ikan.get(1).getPosition().getOrdinat());
    for (int i = 1;i < 10000;i++) {
      try {
        Thread.sleep(10);
        ikan.get(1).setNow(System.nanoTime());
        ikan.get(1).setSecSinceLast(ikan.get(1).getNow() - ikan.get(1).getPrevtime());
        ikan.get(1).setPrevtime(ikan.get(1).getNow());
        ikan.get(1).move();
        System.out.println(ikan.get(1).getPosition().getAbsis());
        System.out.println(ikan.get(1).getPosition().getOrdinat());
        f.repaint();
      } catch (InterruptedException e) {
        System.out.println("Error");
      }
    }
    
  }
  
  /**
   * paint object .
   * @param g = graphics class.
   */
  public void paint(Graphics g) {

    Toolkit t = Toolkit.getDefaultToolkit();
    for (int i = 0; i < ikan.size();i++) {
      g.drawImage(t.getImage("src/ikankiri.png"), (int)ikan.get(i).getPosition().getAbsis(),
          (int)ikan.get(i).getPosition().getOrdinat(),this);
    }
  }

}

