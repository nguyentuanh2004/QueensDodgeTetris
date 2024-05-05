package tetris;

import java.awt.*;
import tetrisblocks.*;

import javax.swing.*;

public class Tetris {
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;

    public static void setGf(GameForm gf) {
        Tetris.gf = gf;
        Tetris.gf.setVisible(false);
    }

    public static void setSf(StartupForm sf) {
        Tetris.sf = sf;
        Tetris.sf.setVisible(false);
    }

    public static void setLf(LeaderboardForm lf) {
        Tetris.lf = lf;
        Tetris.lf.setVisible(false);
    }

    public static void start() {
        //if (gf == null) {
            //gf = new GameForm();
        //}
        //if (sf == null) {
            //sf = new StartupForm();
        //}
        //if (lf == null) {
            //lf = new LeaderboardForm();
        //}
        //sf.setVisible(true);
        gf.setVisible(true);
        gf.startGame();
    }
    public static void showLeaderboard() {
        //if (sf == null) {
            //sf = new StartupForm();
        //}
        //if (lf == null) {
            //lf = new LeaderboardForm();
        //}
        //sf.setVisible(true);
        lf.setVisible(true);
    }
    public static void showStartup() {
        //if (gf == null) {
            //gf = new GameForm();
        //}
        //if (sf == null) {
            //sf = new StartupForm();
        //}
       //if (lf == null) {
            //lf = new LeaderboardForm();
        //}
        sf.setVisible(true);
    }
    public static void gameOver(int score) {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", new Color(255,105,180));
        UI.put("Panel.background", new Color(255,105,180));
        ImageIcon icon = new ImageIcon("res/gameover/GameOver4.png");
        Image image = icon.getImage();
        Image resizeImg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        String playerName = (String)JOptionPane.showInputDialog(null,"Game Over!!!\nPlease enter your name", "HAHAHAHA", JOptionPane.DEFAULT_OPTION, icon, null, "");

        gf.setVisible(false);
        lf.addPlayer(playerName, score);
    }
    public static void main(String[] args) {
        //EventQueue.invokeLater(new Runnable() {
            //@Override
            //public void run() {
                //gf = new GameForm();
                //sf = new StartupForm();
                //lf = new LeaderboardForm();
                //sf.setVisible(true);
            //}

                //Tetris.sf = new StartupForm();
        //  Tetris.gf = new GameForm();
                //Tetris.lf = new LeaderboardForm();
                //Tetris.sf.setVisible(true);
         //   }
        //});
        //sf = new StartupForm();
        //gf = new GameForm();
        //sf = new StartupForm();
        //lf = new LeaderboardForm();
        //sf.setVisible(true);
    }
}
