import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.JOptionPane;

public class PokeMusic {

    public static void PlayMusic(String location){
        try {
            File musicPath = new File(location);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else{
                System.out.println("can't find file");
            }
            
        } 
        catch (Exception e) {
            System.out.println(e); 
        }

    }

    

    }


