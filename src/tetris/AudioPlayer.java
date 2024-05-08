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
import javax.sound.sampled.FloatControl;

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
        unmute();
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
    private void setVolume(Clip clip, float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        }
    }
    public void mute() {
        setVolume(clearLineSound, -80.0f);
        setVolume(gameOverSound, -80.0f);
        setVolume(tetrisThemeSound, -80.0f);
    }
    public void unmute() {
        setMaxVolume(clearLineSound);
        setMaxVolume(gameOverSound);
        setMaxVolume(tetrisThemeSound);
    }
    private void setMaxVolume(Clip clip) {
        try {
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                control.setValue(control.getMaximum());
            } else {
                System.out.println("FloatControl.Type.MASTER_GAIN is not supported.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }
    }
}
