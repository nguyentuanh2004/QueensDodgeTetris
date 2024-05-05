package main;
import tetris.GameForm;
import tetris.LeaderboardForm;
import tetris.StartupForm;
import tetris.Tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {

    public static void main(String[] args) {
        Tetris.setSf(new StartupForm());
        Tetris.setGf(new GameForm());
        Tetris.setLf(new LeaderboardForm());
        Tetris.showStartup();
    }
}
