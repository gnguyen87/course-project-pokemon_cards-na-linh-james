import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class PokeMusic {


    /**
     * Set number of attempts based on difficulty level
     * Sources: 
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
            } else {
                System.out.println("Can't find file");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


