/*
 * Author : Bhagi Baghel
 * Date : 30.11.2022
 * Created with : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayerService {
    Clip clip;
    public void play(String songPath) {
        //1. create a File object to represent the song file
        File songsFile = new File(songPath);
        //2. create an object of AudioInputStream class
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songsFile);
            //3. Get a clip object from the AudioSystem Class
            clip = AudioSystem.getClip();
            //4. Open the clip and load the audio input stream
            clip.open(audioInputStream);
            //5.  To set a loop for the Audio file
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            //6. Start the clip to play Song
            clip.start();
            //7. Get the exact length of the song in micro-sec
            long songLength = clip.getMicrosecondLength() / 1000L;
            //8. Pause the current thread for the time the song is playing
            Thread.sleep(songLength);
            //9. Stop the clip from playing
            clip.stop();
            clip.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            exception.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
            clip.stop();
            clip.close();

    }
    public static void main(String[] args) {
        MusicPlayerService musicPlayer = new MusicPlayerService();
        System.out.println("Playing song....");
        musicPlayer.play("src/main/resources/Songs/Rolex BGM.wav");
        musicPlayer.stop();
        System.out.println("The End");
    }


}
