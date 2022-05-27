package codinglegend.io;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Sound class for easily playing audio files supported by the default java libraries (AIFC, AIFF, AU, SND and WAVE) */
public class Sound {
    private Clip clip;

    /** Load an audio file from the filepath */
    public Sound(String filepath) {
        this(new File(filepath));
    }

    /** Load an audio file for playing */
    public Sound(File audioFile) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException e){
            System.out.println("The file type of "+audioFile.getName()+" is unsupported!");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("An error occurred while trying to load "+audioFile.getName());
            e.printStackTrace();
        }
    }

    /** Plays the audio file */
    public void play(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    /** Continuously loop the playing of the audio file*/
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /** Loop the audio file a specific number of times
     * @param count - the number of times to loop the file
     */
    public void loop(int count){
        clip.loop(count);
    }
}
