package tetris;

import java.awt.*;
import java.util.Random;

public class TetrisBlock {
    private int[][] shape;
    private int[][][] shapes;
    private int currentRotation;
    private Color color;
    private int x, y;
    private Color[] availableColors = {Color.CYAN, Color.ORANGE, Color.WHITE};
    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        initShapes();
        //x = 3; y = 2;
    }
    public void initShapes() {
        shapes = new int[4][][];
        for (int i = 0; i < 4; i++) {
            int r = shape[0].length;
            int c = shape.length;
            shapes[i] = new int[r][c];
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    shapes[i][y][x] = shape[c - 1 - x][y];
                }
            }
            shape = shapes[i];
        }
    }
    public void spawn(int gridWidth) {
        Random ran = new Random();
        currentRotation = ran.nextInt(shapes.length);
        shape = shapes[currentRotation];
        y = 0 - getHeight();
        x = (gridWidth - getWidth()) / 2;
        color = availableColors[ran.nextInt(availableColors.length)];
    }
    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }
    public int getHeight() {return shape.length;}
    public int getWidth() {return shape[0].length;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void moveDown() {y++;}
    public void moveLeft() {x--;}
    public void moveRight() {x++;}
    public void rotate() {
        ++currentRotation;
        currentRotation %= 4;
        shape = shapes[currentRotation];
    }
    public int getBottomEdge() {
        return y + getHeight();
    }
    public int getLeftEdge() {
        return x;
    }
    public int getRightEdge() {
        return x + getWidth();
    }
}
