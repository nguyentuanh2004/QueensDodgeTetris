package main;
import tetris.*;

public class MainClass {

    public static void main(String[] args) {
        Tetris.setSf(new StartupForm());
        Tetris.setGf(new GameForm());
        Tetris.setLf(new LeaderboardForm());
        Tetris.setHf(new HelpForm());
        Tetris.setAudio(new AudioPlayer());
        Tetris.showStartup();
        Tetris.playTetrisTheme();
    }
}
