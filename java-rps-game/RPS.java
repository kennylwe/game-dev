import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RPS extends Game {

    //attributes
    //random #generator
    private Random rng;
    private int requiredWins;
    private int maxLosses;
    private String aiMove;
    private int wins;
    private int losses;

    //constructor (initialize obj), doesn't return creates obj only
    public RPS(Random rng, int requiredWins, int maxLosses) {
        //rng used to pick AI's moves
        this.rng = rng;
        // constructor 'constructs RPS object to control difficulty of game.
        this.rng = rng;
        this.requiredWins = requiredWins;
        this.maxLosses = maxLosses;
        // TODO: Finish Later
        //construct new game object
    }
    //methods
    //sets up all variables to prep for a new round of RPS
    protected String prepToPlay(){
        this.wins = 0;
        this.losses = 0;
        return "Enter Rock, Paper, or scissors." +
                "Beat me" +requiredWins+
                "times before I win" + maxLosses +
                "times";
    }
    //game is over when you've won enough rounds or lost too many rounds
    protected boolean isOver() {
        if(this.losses >= maxLosses || this.wins >= requiredWins){
            if(this.wins >= requiredWins) {
                addWin();
            }
            else{
                addLoss();
            }
            return true; //game over
        }
        return false; //game not over
    }
    public static void main(String[] args) throws IOException {
        // load image here
//        File sourceimage = new File("imgs/rok.jpeg");
//        Image img = ImageIO.read(sourceimage);


//        String imagePath = "cake.png";
//        BufferedImage rok = ImageIO.read(new File(imagePath));
//        JLabel picLabel = new JLabel(new ImageIcon(rok));
//        JPanel jPanel = new JPanel();
//        jPanel.add(picLabel);
//        JFrame f = new JFrame();
//        f.setSize(new Dimension(rok.getWidth(), rok.getHeight()));
//        f.add(jPanel);
//        f.setVisible(true);
//
        var frame = new JFrame();
        var icon = new ImageIcon("cake.png");
        var label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
