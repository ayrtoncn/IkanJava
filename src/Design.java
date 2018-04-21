
import java.io.IOException;

import java.awt.*;
import javax.swing.JFrame;
import java.lang.*;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

public class Design extends Canvas {
  public static ArrayList<Guppy> ikan= new ArrayList<Guppy>();
  /**
   * Main program.
   * @param args = argumen.
   * @throws IOException = wrong input exception.
   */
  public static void main(String[] args) throws IOException {
    Point p = new Point(60,200);
    Guppy f1 = new Guppy(new Point(10,10),'l');
    Guppy f2 = new Guppy(new Point(30,30),'l');
    Guppy f3 = new Guppy(new Point(60,30),'l');
    ikan.add(f1);
    ikan.add(f2);
    Design m=new Design();
    JFrame f=new JFrame();
    f.add(m);
    f.setSize(400,400);
    f.setVisible(true);
    try {

      //sleep 5 seconds
      Thread.sleep(100);
      ikan.get(1).setPosition(p);
      Thread.sleep(3000);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ikan.add(f3);
    f.repaint();
  }

  /**
   * bulding windows frame.
   * @return frame.
   */

  public void paint(Graphics g) {

    Toolkit t=Toolkit.getDefaultToolkit();
    for(int i =0; i < ikan.size();i++){
      g.drawImage(t.getImage("src/ikankiri.png"), (int)ikan.get(i).getPosition().getAbsis(),(int)ikan.get(i).getPosition().getOrdinat(),this);
    }


  }
}

