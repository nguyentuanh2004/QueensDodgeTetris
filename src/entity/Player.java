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
    //public GameArea ga;
    //private GameForm gf;
    //public KeyboardInputs keyInp;
    public Player() {
        //this.ga = ga;
        //this.gf = gf;
        //this.keyInp = keyInp;
        setDefaultValue();
        getPlayerImage();
        direction = "down";
    }
    public void setDefaultValue() {
        //x = 200;
        //y = 420;
        x = 0; y = 0;
        speed = 4;
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
        y += speed;
    }
    public void moveLeft() {
        direction = "left";
        x -= speed;
    }
    public void moveRight() {
        direction = "right";
        x += speed;
    }
    public void moveUp() {
        direction = "up";
        y -= speed;
    }

    public void draw(Graphics g) {
        //System.out.println(x + " " + y);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g.drawImage(image, x, y, 42, 40, null);
        //g.drawRect(x, y, 50, 50);
    }
    //@Override
    //public void run() {

    //}
}
