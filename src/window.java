
import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class window extends JFrame {

  public static BufferedImage image; 
  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          window frame = new window();
          frame.setVisible(true);
          frame.getContentPane().add(new window());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public window() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 800, 600);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
  }
  
  /**
   * run window.
   */
  public void run() {
    try {
      window frame = new window();
      frame.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  public void draw(Graphics g) {
    Image image = Toolkit.getDefaultToolkit().getImage("ikankiri.png");
    g.drawImage(image, 10, 10, this);
  }
  
  public void paintComponent(Graphics g) { 
    g.drawImage(image, 0, 0, null); 
    repaint(); 
  } 
}
