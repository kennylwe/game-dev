import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;


public class Player implements KeyListener{
  private int health;
  private String arrow;
  private boolean clickable;
  private boolean clicked;
  private int score;
  
  public Player() {

    score = 0;

    
    
  }
  
  public void keyReleased (KeyEvent e) {
    
  }
  
  public void keyTyped (KeyEvent e) {
  
  }
  
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      arrow = "UP";
    }
    else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      arrow = "DOWN";
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      arrow = "RIGHT";
      System.out.println("right");
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      arrow = "LEFT";
      System.out.println("left");
    }
  }
  
  
  
}
