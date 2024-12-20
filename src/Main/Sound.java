package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip; //allow to use sounds
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getClassLoader().getResource("sounds/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getClassLoader().getResource("sounds/coin.wav");
        soundURL[2] = getClass().getClassLoader().getResource("sounds/powerup.wav");
        soundURL[3] = getClass().getClassLoader().getResource("sounds/unlock.wav");
        soundURL[4] = getClass().getClassLoader().getResource("sounds/fanfare.wav");
        soundURL[5] = getClass().getClassLoader().getResource("sounds/hitmonster.wav");
        soundURL[6] = getClass().getClassLoader().getResource("sounds/receivedamage.wav");
        soundURL[7] = getClass().getClassLoader().getResource("sounds/coin.wav");

    }

    public void setFile(int i){

            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
            }
            catch (Exception e){}
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
