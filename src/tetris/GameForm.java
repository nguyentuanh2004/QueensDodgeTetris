package tetris;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameForm extends JFrame {
    private JPanel gameAreaPlaceHolder;
    private JLabel scoreDisplay, levelDisplay;
    private GameArea ga;
    public GameForm() {
        //initComponents();
        gameAreaPlaceHolder = new JPanel();
        gameAreaPlaceHolder.setBounds(200, 10, 300, 450);
        gameAreaPlaceHolder.setBackground(Color.PINK);
        gameAreaPlaceHolder.setBorder(new LineBorder(Color.BLACK));
        ga = new GameArea(gameAreaPlaceHolder, 10);
        this.setSize(720, 500);
        this.add(ga);
        scoreDisplay = new JLabel("Score: 0");
        levelDisplay = new JLabel("Level: 1");
        scoreDisplay.setBounds(520, 10, 150, 45);
        levelDisplay.setBounds(520, 60, 150, 45);
        this.add(scoreDisplay);
        this.add(levelDisplay);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        initControls();
        startGame();
    }
    public void initControls() {
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropBlock();
            }
        });
    }
    public void startGame() {
        new GameThread(ga, this).start();
    }

    public void updateScore(int score) {
        scoreDisplay.setText("Score: " + score);
    }
    public void updateLevel(int level) {
        levelDisplay.setText("Level: " + level);
    }
    public static void main(String[] args) {
        GameForm gameForm = new GameForm();

    }
}
