package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpForm extends JFrame {
    private JButton btMainMenu;
    private JLabel helpLabel;
    public HelpForm() {
        this.setSize(720, 500);
        Color pink = new Color(255,192,203);
        this.getContentPane().setBackground(pink);
        ImageIcon icon = new ImageIcon("res/gamebutton/MainMenuButton.png");
        Image image = icon.getImage();
        Image resizeImg = image.getScaledInstance(150, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btMainMenu = new JButton(icon);
        btMainMenu.setOpaque(false);
        btMainMenu.setContentAreaFilled(false);
        btMainMenu.setBorderPainted(false);
        btMainMenu.setBounds(10, 10, 150, 30);
        btMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Tetris.showStartup();
            }
        });
        icon = new ImageIcon("res/background/help.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(675, 350, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        helpLabel = new JLabel(icon);
        helpLabel.setOpaque(false);
        helpLabel.setBounds(22, 75, 675, 350);
        this.add(btMainMenu);
        this.add(helpLabel);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
