package tetris;

import com.mysql.cj.conf.ConnectionUrlParser;
import entity.Player;
import tetrisblocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameArea extends JPanel {
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private TetrisBlock block;
    private TetrisBlock[] blocks;
    public Color[][] background;


    public Player player;
    private ScheduledExecutorService characterExecutor;

    private boolean checkPause = false;

    public boolean isCheckPause() {
        return checkPause;
    }

    public GameArea(JPanel placeHolder, int columns) {
        placeHolder.setVisible(false);
        this.setBounds(placeHolder.getBounds());
        this.setBackground(placeHolder.getBackground());
        this.setBorder(placeHolder.getBorder());
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
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
        player = new Player(this);
    }
    public void changePause() {
        if (checkPause) {
            checkPause = false;
        } else checkPause = true;
    }


    public void initBackgroundArray() {
        checkPause = false;
        background = new Color[gridRows][gridColumns];
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
        if (checkPause) return;



        Random ran = new Random();
        block = blocks[ran.nextInt(blocks.length)];
        block.spawn(gridColumns);
    }
    public boolean isBlockOutOfBounds() {
        if (block == null) return false;
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
        if (!checkPause) block.moveDown();
        //if (!checkPlayer()) return false;
        repaint();


        return true;
    }

    ///
//    public void updateee() {
//        block.moveDown();
//        repaint();
//    }



    public void moveBlockRight() {
        if (checkPause) return;


        if (block == null) return;
        if (!checkRight()) return;
        block.moveRight();

        repaint();
//        if (!checkPlayer()) {
//            return;
//        }
    }
    public void moveBlockLeft() {
        if (checkPause) return;


        if (block == null) return;
        if (!checkLeft()) return;
        block.moveLeft();
//        if (!checkPlayer()) return;
        repaint();
    }
    public void dropBlock() {
        if (checkPause) return;


        if (block == null) return;
        while (checkBottom()) {
            if (!checkPlayer()) return;
            block.moveDown();
        }
        repaint();
    }
    public void rotateBlock() {
        if (checkPause) return;


        if (block == null) return;
        block.rotate();
        if (block.getLeftEdge() < 0) block.setX(0);
        if (block.getRightEdge() >= gridColumns) block.setX(gridColumns - block.getWidth());
        if (block.getBottomEdge() >= gridRows) block.setY(gridRows - block.getHeight());
        while (!checkOverlap()) block.setY(block.getY() - 1);
        repaint();
    }
    public boolean checkOverlap() {
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
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
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
    public boolean checkPlayer() {
        if (block == null) return false;
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int col = 0; col < w; col++) {
            for (int row = h - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY();
                    if (y < 0) break;
                    int ax = x * gridCellSize;
                    int bx = (x + 1) * gridCellSize;
                    int ay = y * gridCellSize;
                    int by = (y + 1) * gridCellSize;
                    if (ax >= player.x + 30 || bx <= player.x) {

                    } else {
                        if (ay >= player.y + 30 || by <= player.y) {

                        } else {
                            return false;
                        }
                    }
                    break;
                }
            }
        }
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY();
                    if (y < 0) break;
                    int ax = x * gridCellSize;
                    int bx = (x + 1) * gridCellSize;
                    int ay = y * gridCellSize;
                    int by = (y + 1) * gridCellSize;
                    if (ax >= player.x + 30 || bx <= player.x) {

                    } else {
                        if (ay >= player.y + 30 || by <= player.y) {

                        } else {
                            return false;
                        }
                    }
                    break;
                }
            }
        }
        for (int row = 0; row < h; row++) {
            for (int col = w - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY();
                    int ax = x * gridCellSize;
                    int bx = (x + 1) * gridCellSize;
                    int ay = y * gridCellSize;
                    int by = (y + 1) * gridCellSize;
                    if (ax >= player.x + 30 || bx <= player.x) {

                    } else {
                        if (ay >= player.y + 30 || by <= player.y) {

                        } else {
                            return false;
                        }
                    }
                    break;
                }
            }
        }

        //kiem tra nhan vat co bi nhot hay khong???
        // dùng bfs kiểm tra có thể đi đến một trong số các ô trên cùng hay không
        int[] moveX = {0, 0, 1, -1};
        int[] moveY = {1, -1, 0, 0};
        boolean[][] checkagain = new boolean[gridRows][gridColumns];
        for (int i = 0; i < gridRows; i++) for (int j = 0; j < gridColumns; j++) checkagain[i][j] = false;
        class pair{
            public int x, y;
            pair() {

            }
            pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        Queue<pair> queue = new LinkedList<>();
        pair curr = new pair(player.y / gridCellSize, player.x / gridCellSize);
        if (curr.x == 0) return true;
        //System.out.println(curr.x + " " + curr.y);
        checkagain[curr.x][curr.y] = true;
        queue.add(curr);
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.x == 0) return true;
            for (int i = 0; i < moveX.length; i++) {
                pair next = new pair(curr.x + moveX[i], curr.y + moveY[i]);
                if (next.x >= 0 && next.x < gridRows && next.y >= 0 && next.y < gridColumns && !checkagain[next.x][next.y] && background[next.x][next.y] == null) {
                    queue.add(next);
                    checkagain[next.x][next.y] = true;
                }
            }
        }
        boolean bl = true;
        for (int i = 0; i < gridColumns; i++) if (checkagain[0][i] == true) bl = false;
        if (bl) return false;




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
        if (linesCleared >= 1) {
            Tetris.playClear();
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
        if (block == null) return;
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



    //public void updatee() {
    //    player.updateCharacter();
    ///}
    public void drawChar(Graphics g) {
        //updatee();
        player.draw(g);
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
        drawChar(g);
        drawBackground(g);
        drawBlock(g);

    }



    public void startCharacterUpdate() {
        characterExecutor = Executors.newSingleThreadScheduledExecutor();
        characterExecutor.scheduleAtFixedRate(this::updateee, 0, 20, TimeUnit.MILLISECONDS);
    }
    public void updateee() {
        repaint();
        //checkPlayer();
    }
    public void stopCharacterUpdate() {
        // Implement stop logic if needed
        if (characterExecutor != null && !characterExecutor.isShutdown()) {
            characterExecutor.shutdown();
        }
    }
}

