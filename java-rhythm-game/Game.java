import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener{
  private boolean isplaying;
  
  private static final int FPS = 25;
  public static final int TILE_SIZE = 50;
  public static final int ROWS = 12;
  public static final int COLUMNS = 18;
  public static final int ARROWSIZE = 30;
  
  private Timer timer;
  private Player player;
  private ArrayList<Arrow> arrows;
  private int frames = 0;
  private int score = 0;
  
  
  
  public Game() {
    isplaying = true;
    setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
    // set the game board background color
    setBackground(new Color(232, 232, 232));
    
    player = new Player();
    
    timer = new Timer(FPS, this);
    arrows = new ArrayList<Arrow>();
    timer.start();
    
  }
  
  public boolean checkPlaying() {
    return isplaying;
  }
  
  
  
  public void EndGame() {
    isplaying = false;
  }
  
  
  
  public void draw(Graphics2D g) // draw must be called by paintComponent of the panel
  {
    for (int c = 0; c < arrows.size(); c++) {
      arrows.get(c).update();
      if (arrows.get(c).gety() > 500) {
        arrows.remove(c);
      }
      if (Math.abs(450 - arrows.get(c).gety()) < 20) {
        for (int j = 0; j < player.getAllPos().length; j++) {
          if (arrows.get(c).getx() == player.getAllPos()[j] && player.getHeldArrows()[j] == true) {
            arrows.remove(c);
          }
        }
      }
      g.fillOval(arrows.get(c).x, arrows.get(c).y, ARROWSIZE, ARROWSIZE);
      
    }
    for (int i = 0; i < player.getAllPos().length; i++) {
      if (player.getHeldArrows()[i] == true) {
        g.setColor(Color.lightGray);
      } else {
        g.setColor(Color.GRAY);
      } 
      g.fillOval(player.getAllPos()[i], 450, ARROWSIZE, ARROWSIZE);
      
    }
    
  }
  
  
  
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    draw(g2);
    //g.drawImage(image, x, y, this); // draw the image at the updated position
    
    
  }
  
  public void createArrow() {
    int rand = (int)(Math.random() * 4) + 1;
     if (rand == 1) {
       arrows.add(new Arrow("LEFT"));
     } else if (rand == 2) {
       arrows.add(new Arrow("UP"));
     } else if (rand == 3) {
       arrows.add(new Arrow("DOWN"));
     } else if (rand == 4) {
       arrows.add(new Arrow("RIGHT"));
     }
  }
  

  

  
  
  
 public void ArrowUpdate() {
    frames++;
    if (frames % 10 == 0) { //If the remainder of the current frame divided by 10 is 0 run the function.
        createArrow();
        frames = 0;
    }
 }
  
  
  
  
  @Override
  public void actionPerformed(ActionEvent e) {
      // this method is called by the timer every DELAY ms.
      // use this space to update the state of your game or animation
     // before the graphics are redrawn.
    ArrowUpdate();
    
    
     

     

    
    

      // prevent the player from disappearing off the board
      //player.tick();
      
        // give the player points for collecting coins
      //collectCoins();

        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
      repaint();
    }
  

  
  
  @Override
  public void keyTyped(KeyEvent e) {
       // this is not used but must be defined as part of the KeyListener interface
  }

  @Override
  public void keyPressed(KeyEvent e) {
      // react to key down events
      player.keyPressed(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
      // react to key up events
    player.keyReleased(e);
  }
  
  
  
}
