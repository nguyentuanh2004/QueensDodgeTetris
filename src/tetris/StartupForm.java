package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupForm extends JFrame {
    private JButton btStart;
    private JButton btLeaderboard;
    private JButton btQuit;
    private JButton btHelp;
    private JButton btSound;
    private JLabel nameLabel;
    private boolean mute = false;
    public StartupForm() {
        this.setSize(720, 500);
        Color pink = new Color(255,192,203);
        this.getContentPane().setBackground(pink);

        ImageIcon icon = new ImageIcon("res/gamebutton/StartGameButton.png");
        Image image = icon.getImage();
        Image resizeImg = image.getScaledInstance(220, 45, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);

        btStart = new JButton(icon);
        btStart.setOpaque(false);
        btStart.setContentAreaFilled(false);
        btStart.setBorderPainted(false);

        btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.start();
            }
        });


        icon = new ImageIcon("res/gamebutton/LeaderboardButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(220, 45, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btLeaderboard = new JButton(icon);
        btLeaderboard.setOpaque(false);
        btLeaderboard.setContentAreaFilled(false);
        btLeaderboard.setBorderPainted(false);

        btLeaderboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.showLeaderboard();
            }
        });

        icon = new ImageIcon("res/gamebutton/QuitButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(220, 45, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btQuit = new JButton(icon);
        btQuit.setOpaque(false);
        btQuit.setContentAreaFilled(false);
        btQuit.setBorderPainted(false);

        btQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        icon = new ImageIcon("res/gamebutton/HelpButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(220, 45, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btHelp = new JButton(icon);
        btHelp.setOpaque(false);
        btHelp.setContentAreaFilled(false);
        btHelp.setBorderPainted(false);

        btHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //System.exit(0);
                setVisible(false);
                Tetris.showHelp();
            }
        });

        icon = new ImageIcon("res/gamebutton/SoundButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(49, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btSound = new JButton(icon);
        btSound.setOpaque(false);
        btSound.setContentAreaFilled(false);
        btSound.setBorderPainted(false);

        btSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!mute) {
                    mute = true;
                    Tetris.muteSound();
                } else {
                    mute = false;
                    Tetris.unmuteSound();
                }
            }
        });


        icon = new ImageIcon("res/background/gamename.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(448, 250, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        nameLabel = new JLabel(icon);
        nameLabel.setOpaque(false);
        nameLabel.setBounds(130, 0, 448, 250);

        btStart.setBounds(250, 250, 220, 45);
        btLeaderboard.setBounds(250, 300, 220, 45);
        btHelp.setBounds(250, 350, 220, 45);
        btQuit.setBounds(250, 400, 220, 45);
        btSound.setBounds(640, 400, 49, 50);
        this.add(btStart);
        this.add(btLeaderboard);
        this.add(btQuit);
        this.add(btHelp);
        this.add(btSound);
        this.add(nameLabel);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setVisible(true);
    }

    public static void main(String[] args) {
        //new StartupForm();
        /*EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartupForm().setVisible(true);
            }
        });*/
        //Tetris.setGf(new GameForm());
        //Tetris.setSf(new StartupForm());
        //Tetris.setLf(new LeaderboardForm());
        //Tetris.showStartup();
    }
}
