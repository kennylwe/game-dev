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
  private int rarrowposx;
  private int darrowposx;
  private int uparrowposx;
  private int leftarrowposx;
  private boolean[] holding = new boolean[4];
  private int[] stuff = new int[4];
  
  
  public Player() {

    score = 0;
    rarrowposx = 750;
    darrowposx = 350;
    uparrowposx = 550;
    leftarrowposx = 150;
    
    holding = new boolean[]{false, false, false, false};
    stuff = new int[]{rarrowposx, darrowposx, uparrowposx, leftarrowposx};
      

    
    
  }
  public boolean[] getHeldArrows() {
    return holding;
  }
  
  
  public int[] getAllPos() {
    return stuff;
  }
  
  
  public void keyReleased (KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      
      holding[2] = false;
    }
    else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      
      holding[1] = false;
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      
      holding[0] = false;
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      
      holding[3] = false;
    }
  }
  
  public void keyTyped (KeyEvent e) {
  
  }
  
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      arrow = "UP";
      holding[2] = true;
    }
    else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      arrow = "DOWN";
      holding[1] = true;
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      arrow = "RIGHT";
      holding[0] = true;
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      arrow = "LEFT";
      holding[3] = true;
    }
  }
  
  
  
}
