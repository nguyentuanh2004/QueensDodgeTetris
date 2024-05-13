package tetris;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameThread extends Thread{
    private GameArea ga;
    private GameForm gf;
    private int score = 0;
    private int level = 1;
    private int scorePerLevel = 3;
    private int pause = 1000;
    private int speedupPerLevel = 100;

    private ScheduledExecutorService executor;
    private boolean gameOver = false;
    public GameThread(GameArea ga, GameForm gf) {
        this.ga = ga;
        this.gf = gf;
        gf.updateScore(score);
        gf.updateLevel(level);
        executor = Executors.newSingleThreadScheduledExecutor();
    }
    @Override
    public void run() {


        ga.startCharacterUpdate();
        executor.scheduleAtFixedRate(this::checkPlayerAndGameOver, 0, 20, TimeUnit.MILLISECONDS);


        while (true) {
            ga.spawnBlock();
            while (ga.moveBlockDown()) {
                //if (!ga.checkPlayer()) break;
                try {

                    Thread.sleep(pause);
                    if (!ga.checkPlayer()) break;
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                    ga.stopCharacterUpdate();
                    return;
                }
                if (gameOver) return;
                //if (!ga.checkPlayer()) break;

            }
            if (gameOver) return;
            if (!ga.checkPlayer() || ga.isBlockOutOfBounds()) {
                if (!gameOver) {
                    gameOver = true;
                    ga.stopCharacterUpdate();
                    executor.shutdown();
                    Tetris.gameOver(score);
                    return;
                }
            }
            /*
            if (ga.isBlockOutOfBounds()) {
                ga.stopCharacterUpdate();
                executor.shutdown();
                Tetris.gameOver(score);
                //break;
                return;
            }

             */
            ga.moveBlockToBackground();
            score += ga.clearLines();
            gf.updateScore(score);

            int lv = score / scorePerLevel + 1;
            if (lv > level) {
                level = lv;
                gf.updateLevel(level);
                pause -= speedupPerLevel;
            }
        }
        //ga.stopCharacterUpdate();
    }
    private void checkPlayerAndGameOver() {
        if (!ga.checkPlayer()) {
            if (!gameOver) {
                gameOver = true;
                Tetris.gameOver(score);
                ga.stopCharacterUpdate();
                executor.shutdown();
            }
        }
    }
}
