package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupForm extends JFrame {
    private JButton btStart;
    private JButton btLeaderboard;
    private JButton btQuit;
    public StartupForm() {
        this.setSize(720, 500);
        Color pink = new Color(255,192,203);
        Color PaleVioletRed = new Color(219,112,147);
        this.getContentPane().setBackground(pink);
        btStart = new JButton("Start Game");
        btStart.setFont(new Font("Dialog", Font.PLAIN, 20));
        btStart.setBackground(PaleVioletRed);
        btStart.setOpaque(true);
        btStart.setBorderPainted(true);

        btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.start();
            }
        });

        //btStart.setForeground(Color.WHITE);
        btLeaderboard = new JButton("Leaderboard");
        btLeaderboard.setFont(new Font("Dialog", Font.PLAIN, 20));
        btLeaderboard.setBackground(PaleVioletRed);
        btLeaderboard.setOpaque(true);
        btLeaderboard.setBorderPainted(true);

        btLeaderboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.showLeaderboard();
            }
        });

        //btLeaderboard.setForeground(Color.WHITE);
        btQuit = new JButton("Quit");
        btQuit.setFont(new Font("Dialog", Font.PLAIN, 20));
        btQuit.setBackground(PaleVioletRed);
        btQuit.setOpaque(true);
        btQuit.setBorderPainted(true);

        btQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //btQuit.setForeground(Color.WHITE);
        btStart.setBounds(180, 250, 360, 45);
        btLeaderboard.setBounds(180, 300, 360, 45);
        btQuit.setBounds(180, 350, 360, 45);
        this.add(btStart);
        this.add(btLeaderboard);
        this.add(btQuit);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
