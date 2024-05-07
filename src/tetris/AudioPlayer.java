package tetris;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioPlayer {
    private String soundsFolder = "tetrissounds" + File.separator;
    private String clearLinePath = soundsFolder + "clear.wav";
    private String gameOverPath = soundsFolder + "gameover.wav";
    private String tetrisThemePath = soundsFolder + "tetristheme.wav";
    private Clip clearLineSound, gameOverSound, tetrisThemeSound;
    public AudioPlayer() {
        try {
            clearLineSound = AudioSystem.getClip();
            gameOverSound = AudioSystem.getClip();
            tetrisThemeSound = AudioSystem.getClip();
            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));
            gameOverSound.open(AudioSystem.getAudioInputStream(new File(gameOverPath).getAbsoluteFile()));
            tetrisThemeSound.open(AudioSystem.getAudioInputStream(new File(tetrisThemePath).getAbsoluteFile()));
        } catch (LineUnavailableException e) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, e);
            //the program can't play the audio file
        } catch (UnsupportedAudioFileException e) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, e);
            //the file can not be found
        } catch (IOException e) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void playClearLine() {
        clearLineSound.setFramePosition(0);
        clearLineSound.start();
    }
    public void playGameOver() {
        gameOverSound.setFramePosition(0);
        gameOverSound.start();
    }
    public void playTetrisTheme() {
        tetrisThemeSound.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
