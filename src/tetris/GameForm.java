package tetris;

import entity.Player;
import inputs.KeyboardInputs;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameForm extends JFrame {
    private JPanel gameAreaPlaceHolder;
    private JLabel scoreDisplay, levelDisplay, cherryblossom, blossom1, blossom2;

    private GameArea ga;
    private GameThread gt;
    //
    //private KeyboardInputs keyInp;
    //private Player player;



    private JButton btMainMenu, btUp, btDown, btLeft, btRight, btPauseGame;
    public GameForm() {
        //initComponents();
        //keyInp = new KeyboardInputs();


        gameAreaPlaceHolder = new JPanel();
        gameAreaPlaceHolder.setBounds(200, 10, 300, 450);
        gameAreaPlaceHolder.setBackground(Color.PINK);
        gameAreaPlaceHolder.setBorder(new LineBorder(Color.BLACK));
        ga = new GameArea(gameAreaPlaceHolder, 10);
        this.setSize(720, 500);
        Color pink = new Color(255,192,203);
        this.getContentPane().setBackground(pink);
        this.add(ga);
        scoreDisplay = new JLabel("Score: 0");
        levelDisplay = new JLabel("Level: 1");
        scoreDisplay.setBounds(520, 10, 180, 45);
        levelDisplay.setBounds(520, 60, 180, 45);
        this.add(scoreDisplay);
        this.add(levelDisplay);

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
                gt.interrupt();
            }
        });

        btMainMenu.setFocusable(false);


        icon = new ImageIcon("res/gamebutton/LeftButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(49, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btLeft = new JButton(icon);
        btLeft.setOpaque(false);
        btLeft.setContentAreaFilled(false);
        btLeft.setBorderPainted(false);
        btLeft.setBounds(520, 350, 49, 50);
        btLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.player.moveLeft();
            }
        });
        btLeft.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ga.player.mousePressed = true;
                new Thread() {
                    public void run() {
                        while (ga.player.mousePressed) {
                            if (!ga.checkPlayer()) ga.player.mousePressed = false;
                            ga.player.moveLeft();
                            try {
                                Thread.sleep(20); // Dừng 100 milliseconds trước khi tiếp tục
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                }.start();
            }

            public void mouseReleased(MouseEvent e) {
                ga.player.mousePressed = false;
            }

        });
        btLeft.setFocusable(false);

        icon = new ImageIcon("res/gamebutton/RightButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(49, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btRight = new JButton(icon);
        btRight.setOpaque(false);
        btRight.setContentAreaFilled(false);
        btRight.setBorderPainted(false);
        btRight.setBounds(620, 350, 49, 50);
        btRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.player.moveRight();
            }
        });
        btRight.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ga.player.mousePressed = true;
                new Thread() {
                    public void run() {
                        while (ga.player.mousePressed) {
                            if (!ga.checkPlayer()) ga.player.mousePressed = false;
                            ga.player.moveRight();
                            try {
                                Thread.sleep(20); // Dừng 100 milliseconds trước khi tiếp tục
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                }.start();
            }

            public void mouseReleased(MouseEvent e) {
                ga.player.mousePressed = false;
            }

        });
        btRight.setFocusable(false);


        icon = new ImageIcon("res/gamebutton/UpButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(49, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btUp = new JButton(icon);
        btUp.setOpaque(false);
        btUp.setContentAreaFilled(false);
        btUp.setBorderPainted(false);
        btUp.setBounds(570, 320, 49, 50);
        btUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.player.moveUp();
            }
        });
        btUp.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ga.player.mousePressed = true;
                new Thread() {
                    public void run() {
                        while (ga.player.mousePressed) {
                            if (!ga.checkPlayer()) ga.player.mousePressed = false;
                            ga.player.moveUp();
                            try {
                                Thread.sleep(20); // Dừng 100 milliseconds trước khi tiếp tục
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                }.start();
            }

            public void mouseReleased(MouseEvent e) {
                ga.player.mousePressed = false;
            }

        });
        btUp.setFocusable(false);

        icon = new ImageIcon("res/gamebutton/DownButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(49, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btDown = new JButton(icon);
        btDown.setOpaque(false);
        btDown.setContentAreaFilled(false);
        btDown.setBorderPainted(false);
        btDown.setBounds(570, 380, 49, 50);
        btDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.player.moveDown();
            }
        });
        btDown.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ga.player.mousePressed = true;
                new Thread() {
                    public void run() {
                        while (ga.player.mousePressed) {
                            if (!ga.checkPlayer()) ga.player.mousePressed = false;
                            ga.player.moveDown();
                            try {
                                Thread.sleep(20); // Dừng 100 milliseconds trước khi tiếp tục
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                }.start();
            }

            public void mouseReleased(MouseEvent e) {
                ga.player.mousePressed = false;
            }

        });
        btDown.setFocusable(false);

        icon = new ImageIcon("res/gamebutton/PauseGameButton.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(193, 70, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        btPauseGame = new JButton(icon);
        btPauseGame.setOpaque(false);
        btPauseGame.setContentAreaFilled(false);
        btPauseGame.setBorderPainted(false);
        btPauseGame.setBounds(510, 110, 193, 70);
        btPauseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.changePause();
            }
        });
        btPauseGame.setFocusable(false);

        icon = new ImageIcon("res/background/blossom1.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(200, 154, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        blossom1 = new JLabel(icon);
        blossom1.setOpaque(false);
        blossom1.setBounds(0, 50, 200, 154);



        icon = new ImageIcon("res/background/blossom2.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(200, 177, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        blossom2 = new JLabel(icon);
        blossom2.setOpaque(false);
        blossom2.setBounds(0, 300, 200, 177);



        icon = new ImageIcon("res/background/cherryblossom2.png");
        image = icon.getImage();
        resizeImg = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImg);
        cherryblossom = new JLabel(icon);
        cherryblossom.setOpaque(false);
        cherryblossom.setBounds(500, 180, 200, 100);



        this.add(btMainMenu);
        this.add(btLeft);
        this.add(btRight);
        this.add(btUp);
        this.add(btDown);
        this.add(btPauseGame);
        this.add(cherryblossom);
        this.add(blossom1);
        this.add(blossom2);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setVisible(true);
        //startGame();
        initControls();
        //this.addKeyListener(keyInp);



        //ga.player.keyInp = this.keyInp;
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
        ga.initBackgroundArray();
        ga.player.setDefaultValue();
        gt = new GameThread(ga, this);
        gt.start();
        //player = new Player(ga, this, keyInp);


    }

    public void updateScore(int score) {
        scoreDisplay.setText("Score: " + score);
    }
    public void updateLevel(int level) {
        levelDisplay.setText("Level: " + level);
    }
    public static void main(String[] args) {
        //GameForm gameForm = new GameForm();

    }
}
