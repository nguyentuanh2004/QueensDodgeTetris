package tetris;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameThread extends Thread{
    private GameArea ga;
    private GameForm gf;
    private double score = 0;
    private int level = 1;
    private int scorePerLevel = 30;
    private int pause = 1000;
    private int speedupPerLevel = 100;

    private ScheduledExecutorService executor;
    private boolean gameOver = false;
    private boolean invisible = false;
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
        executor.scheduleAtFixedRate(this::checkPlayerr, 0, 20, TimeUnit.MILLISECONDS);




        while (true) {
            ga.spawnBlock();
            if (gameOver) return;


            while (ga.moveBlockDown()) {
                //if (!ga.checkPlayer()) break;
                try {

                    Thread.sleep(pause);


                    //if (!ga.checkPlayer()) break;
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                    ga.stopCharacterUpdate();
                    return;
                }
                if (gameOver) return;
                //if (!ga.checkPlayer()) break;

            }
            if (gameOver) return;
            /*
            if (!ga.checkPlayer() || ga.isBlockOutOfBounds()) {
                if (!gameOver) {
                    gameOver = true;
                    ga.stopCharacterUpdate();
                    //executor.shutdown();
                    Tetris.gameOver(score);
                    return;
                }
            }

             */

            if (ga.isBlockOutOfBounds()) {
                ga.stopCharacterUpdate();
                //executor.shutdown();
                Tetris.gameOver(score);
                //break;
                return;
            }


            ga.moveBlockToBackground();
            if (invisible) {
                score += 0.4;
                invisible = false;
            }
            score += ga.clearLines();
            gf.updateScore(score);

            int lv = (int)score / scorePerLevel + 1;
            if (lv > level) {
                level = lv;
                gf.updateLevel(level);
                pause -= speedupPerLevel;
            }
        }
        //ga.stopCharacterUpdate();
    }
    private void checkPlayerr() {
        if (!ga.checkPlayer()) {
            ga.setnull();
            invisible = true;


            /*
            if (!gameOver) {
                gameOver = true;
                Tetris.gameOver(score);
                ga.stopCharacterUpdate();
                executor.shutdown();
            }

             */
        }
    }
}
