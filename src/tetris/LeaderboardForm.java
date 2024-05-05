package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderboardForm extends JFrame {
    private JButton btMainMenu;
    public LeaderboardForm() {
        this.setSize(720, 500);
        Color pink = new Color(255,192,203);
        Color PaleVioletRed = new Color(219,112,147);
        this.getContentPane().setBackground(pink);
        btMainMenu = new JButton("Main Menu");
        btMainMenu.setBackground(PaleVioletRed);
        btMainMenu.setOpaque(true);
        btMainMenu.setBorderPainted(true);
        btMainMenu.setBounds(10, 10, 150, 30);
        btMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.showStartup();
            }
        });

        this.add(btMainMenu);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        /*EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LeaderboardForm().setVisible(true);
            }
        });*/
    }
}
