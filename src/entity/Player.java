package entity;

import inputs.KeyboardInputs;
import tetris.GameArea;
import tetris.GameForm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player{
    public int x, y;
    public int speed;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction;
    public int playerCounter;
    public int playerNum;
    public boolean mousePressed;
    //public Color[][] background;
    private int gridCellSize = 30;
    private int playerSize = 40;
    public GameArea ga;
    //private GameForm gf;
    //public KeyboardInputs keyInp;
    public Player(GameArea ga) {
        //this.ga = ga;
        //this.gf = gf;
        //this.keyInp = keyInp;
        this.ga = ga;
        setDefaultValue();
        getPlayerImage();
        direction = "down";
    }
    public void setDefaultValue() {
        //x = 200;
        //y = 420;
        x = 0; y = 410;
        speed = 4;
        playerCounter = 0;
        playerNum = 1;
    }
    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/dog_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/dog_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/dog_up_3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/dog_up_4.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/dog_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/dog_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/dog_down_3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/dog_down_4.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/dog_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/dog_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/dog_left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/dog_left_4.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/dog_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/dog_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/dog_right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/dog_right_4.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public void updateCharacter() {
        if (keyInp.isDownPressed()) {
            direction = "down";
            moveDown();
        } else if (keyInp.isLeftPressed()) {
            direction = "left";
            moveLeft();
        } else if (keyInp.isRightPressed()) {
            direction = "right";
            moveRight();
        } else if (keyInp.isUpPressed()) {
            direction = "up";
            moveUp();
        }
    }

     */
    public void moveDown() {
        direction = "down";
        //y += speed;
        //y = Math.min(y, 450 - playerSize);

        int col1 = x / gridCellSize;
        int col2 = (x + gridCellSize - 1) / gridCellSize;
        int row = (y + speed + gridCellSize - 1) / gridCellSize;
        if (row < ga.background.length && row >= 0 && (ga.background[row][col1] != null || ga.background[row][col2] != null)) {

        } else {
            y += speed;
            y = Math.min(y, 450 - playerSize);
        }
        //System.out.println(x + " " + y);

        playerCounter++;
        if (playerCounter > 3) {
            if (playerNum == 1) {
                playerNum = 2;
            } else if (playerNum == 2) {
                playerNum = 3;
            } else if (playerNum == 3) {
                playerNum = 4;
            } else {
                playerNum = 1;
            }
        }
        if (playerCounter > 3) playerCounter = 0;
        //playerCounter = 0;
    }
    public void moveLeft() {
        direction = "left";
//        x -= speed;
//        x = Math.max(0, x);
        int col = (x - speed) / gridCellSize;
        int row1 = (y + gridCellSize - 1) / gridCellSize;
        int row2 = y / gridCellSize;
        if (col < ga.background[0].length && col >= 0 && (ga.background[row1][col] != null || ga.background[row2][col] != null)) {

        } else {
            x -= speed;
            x = Math.max(0, x);
        }
        playerCounter++;
        if (playerCounter > 3) {
            if (playerNum == 1) {
                playerNum = 2;
            } else if (playerNum == 2) {
                playerNum = 3;
            } else if (playerNum == 3) {
                playerNum = 4;
            } else {
                playerNum = 1;
            }
            playerCounter = 0;
        }
    }
    public void moveRight() {
        direction = "right";
//        x += speed;
//        x = Math.min(300 - playerSize, x);
        int col = (x + speed + gridCellSize - 1) / gridCellSize;
        int row1 = (y + gridCellSize - 1) / gridCellSize;
        int row2 = y / gridCellSize;
        if (col < ga.background[0].length && col >= 0 && (ga.background[row1][col] != null || ga.background[row2][col] != null)) {

        } else {
            x += speed;
            x = Math.min(300 - playerSize, x);
        }
        playerCounter++;
        if (playerCounter > 3) {
            if (playerNum == 1) {
                playerNum = 2;
            } else if (playerNum == 2) {
                playerNum = 3;
            } else if (playerNum == 3) {
                playerNum = 4;
            } else {
                playerNum = 1;
            }
            playerCounter = 0;
        }
    }
    public void moveUp() {
        direction = "up";
//        y -= speed;
//        y = Math.max(0, y);
        int col1 = x / gridCellSize;
        int col2 = (x + gridCellSize - 1) / gridCellSize;
        int row = (y - speed) / gridCellSize;
        if (row < ga.background.length && row >= 0 && (ga.background[row][col1] != null || ga.background[row][col2] != null)) {

        } else {
            y -= speed;
            y = Math.max(0, y);
        }
        playerCounter++;
        if (playerCounter > 3) {
            if (playerNum == 1) {
                playerNum = 2;
            } else if (playerNum == 2) {
                playerNum = 3;
            } else if (playerNum == 3) {
                playerNum = 4;
            } else {
                playerNum = 1;
            }
            playerCounter = 0;
        }
    }

    public void draw(Graphics g) {
        //System.out.println(x + " " + y);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (playerNum == 1) {
                    image = up1;
                }
                if (playerNum == 2) {
                    image = up2;
                }
                if (playerNum == 3) {
                    image = up3;
                }
                if (playerNum == 4) {
                    image = up4;
                }
                break;
            case "down":
                if (playerNum == 1) {
                    image = down1;
                }
                if (playerNum == 2) {
                    image = down2;
                }
                if (playerNum == 3) {
                    image = down3;
                }
                if (playerNum == 4) {
                    image = down4;
                }
                break;
            case "left":
                if (playerNum == 1) {
                    image = left1;
                }
                if (playerNum == 2) {
                    image = left2;
                }
                if (playerNum == 3) {
                    image = left3;
                }
                if (playerNum == 4) {
                    image = left4;
                }
                break;
            case "right":
                if (playerNum == 1) {
                    image = right1;
                }
                if (playerNum == 2) {
                    image = right2;
                }
                if (playerNum == 3) {
                    image = right3;
                }
                if (playerNum == 4) {
                    image = right4;
                }
                break;
        }
        g.drawImage(image, x, y, playerSize, playerSize, null);
        //g.drawRect(x, y, 50, 50);
    }
    //@Override
    //public void run() {

    //}
}
