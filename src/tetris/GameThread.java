package tetris;

public class GameThread extends Thread{
    private GameArea ga;
    private GameForm gf;
    private int score = 0;
    private int level = 1;
    private int scorePerLevel = 3;
    private int pause = 1000;
    private int speedupPerLevel = 100;
    public GameThread(GameArea ga, GameForm gf) {
        this.ga = ga;
        this.gf = gf;
        gf.updateScore(score);
        gf.updateLevel(level);
    }
    @Override
    public void run() {


        ga.startCharacterUpdate();


        while (true) {
            ga.spawnBlock();
            while (ga.moveBlockDown()) {
                if (!ga.checkPlayer()) break;
                try {

                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                    ga.stopCharacterUpdate();
                    return;
                }


            }
            if (ga.isBlockOutOfBounds() || !ga.checkPlayer()) {
                ga.stopCharacterUpdate();
                Tetris.gameOver(score);
                break;
            }
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
        ga.stopCharacterUpdate();
    }
}
