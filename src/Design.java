import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


import javax.swing.JFrame;


import javax.swing.JPanel;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.border.EmptyBorder;

public class Design extends JFrame {
    /**
     * Main program.
     * @param args = argumen.
     * @throws IOException = wrong input exception.
     */
  public static void main(String[] args) throws IOException {
    JFrame frame = buildFrame();
    final BufferedImage image = ImageIO.read(new File("src/ikankiri.png"));
    JPanel pane = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 200, 200, null);
        g.drawImage(image, 100, 100, null);
      }
    };
    frame.add(pane);
  }

  /**
   * bulding windows frame.
   * @return frame.
   */
  private static JFrame buildFrame() {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setVisible(true);
    return frame;
  }
}
