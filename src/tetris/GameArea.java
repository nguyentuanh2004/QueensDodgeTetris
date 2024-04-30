package tetris;

import tetrisblocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameArea extends JPanel {
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private TetrisBlock block;
    private TetrisBlock[] blocks;
    private Color[][] background;
    public GameArea(JPanel placeHolder, int columns) {
        placeHolder.setVisible(false);
        this.setBounds(placeHolder.getBounds());
        this.setBackground(placeHolder.getBackground());
        this.setBorder(placeHolder.getBorder());
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        background = new Color[gridRows][gridColumns];
        blocks = new TetrisBlock[] {
                new IShape(),
                new JShape(),
                new LShape(),
                new OShape(),
                new SShape(),
                new TShape(),
                new ZShape()
        };
        //background[1][1] = Color.CYAN;
        //spawnBlock();
    }
    public void drawBackground(Graphics g) {
        Color color;
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridColumns; c++) {
                color = background[r][c];
                if (color != null) {
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }
    public void drawGridSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.PINK);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    public void spawnBlock() {
        Random ran = new Random();
        block = blocks[ran.nextInt(blocks.length)];
        block.spawn(gridColumns);
    }
    public boolean isBlockOutOfBounds() {
        if (block.getY() < 0) {
            block = null;
            return true;
        }
        return false;
    }
    public boolean moveBlockDown() {
        if (!checkBottom()) {
            //moveBlockToBackground();
            //clearLines();
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }
    public void moveBlockRight() {
        if (block == null) return;
        if (!checkRight()) return;
        block.moveRight();
        repaint();
    }
    public void moveBlockLeft() {
        if (block == null) return;
        if (!checkLeft()) return;
        block.moveLeft();
        repaint();
    }
    public void dropBlock() {
        if (block == null) return;
        while (checkBottom()) {
            block.moveDown();
        }
        repaint();
    }
    public void rotateBlock() {
        if (block == null) return;
        block.rotate();
        if (block.getLeftEdge() < 0) block.setX(0);
        if (block.getRightEdge() >= gridColumns) block.setX(gridColumns - block.getWidth());
        if (block.getBottomEdge() >= gridRows) block.setY(gridRows - block.getHeight());
        repaint();
    }
    public  boolean checkBottom() {
        if (block.getBottomEdge() == gridRows) {
            return false;
        }

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int col = 0; col < w; col++) {
            for (int row = h - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    public boolean checkLeft() {
        if (block.getLeftEdge() == 0) return false;
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    public boolean checkRight() {
        if (block.getRightEdge() == gridColumns) return false;
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int row = 0; row < h; row++) {
            for (int col = w - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    public int clearLines() {
        boolean lineFilled;
        int linesCleared = 0;
        for (int r = gridRows - 1; r >= 0; r--) {
            lineFilled = true;
            for (int c = 0; c < gridColumns; c++) {
                if (background[r][c] == null) {
                    lineFilled = false;
                    break;
                }
            }
            if (lineFilled) {
                linesCleared++;
                clearLine(r);
                shiftDown(r);
                clearLine(0);
                repaint();
                r++;
            }
        }
        return linesCleared;
    }
    public void clearLine(int r) {
        for (int i = 0; i < gridColumns; i++) {
            background[r][i] = null;
        }
    }
    public void shiftDown(int r) {
        for (int i = r; i > 0; i--) {
            for (int j = 0; j < gridColumns; j++) {
                background[i][j] = background[i - 1][j];
            }
        }
    }
    public void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        int xp = block.getX();
        int yp = block.getY();
        Color color = block.getColor();
        //Color color = Color.BLACK;
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (shape[r][c] == 1) {
                    background[r + yp][c + xp] = color;
                }
            }
        }
    }
    public void drawBlock(Graphics g) {
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.fillRect(0, 0, 50, 50);
        /*
        for (int y = 0; y < gridRows; y++) {
            for (int x = 0; x < gridColumns; x++) {
                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);

            }
        }
        */
        drawBackground(g);
        drawBlock(g);

    }
}
