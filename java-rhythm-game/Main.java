import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main{
  
  
  private static void initGame() {
    JFrame window = new JFrame("RhythmGame");
    
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Realgame game = new Realgame();
    
    window.add(game);
    window.addComponentListener(game);
    
    window.setResizable(false);
    // fit the window size around the components (just our jpanel).
    // pack() should be called after setResizable() to avoid issues on some platforms
    window.pack();
    // open window in the center of the screen
    window.setLocationRelativeTo(null);
    // display the window
    window.setVisible(true);
    
  }
  
  
  public static void main(String[] args) {
    
    SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGame();
            }
        });
    }
        
        
        
        
        
 }
  
  
