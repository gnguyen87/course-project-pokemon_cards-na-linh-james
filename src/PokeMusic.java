import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Authors: Na Nguyen, James Hernandez, Linh Nguyen
 * Add background music when user starts playing the game.
 */
public class PokeMusic {

    /**
     * Loops over pokemon music from 1997. 
     * Sources: (<https://downloads.khinsider.com/game-soundtracks/album/pokemon-game-boy-pok-mon-sound-complete-set-play-cd>),
     *          (<https://www.youtube.com/watch?v=wJO_cq5XeSA>)
     */
    public static void PlayMusic(String location) {
        try {
            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            // If the music stops, restart it
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                });

                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
            } 
            else {
                System.out.println("Can't find file");
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}


