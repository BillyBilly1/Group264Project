package app;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Sounds {

    public static void playTone() {
        int duration = 250;
        int frequency = 6000;
        byte[] buf = new byte[duration];
        AudioFormat audioFormat = new AudioFormat(44100, 8, 1, true, false);

        for (int i = 0; i < buf.length; i++) {
            double angle = i / (44100.0 / frequency) * 2.0 * Math.PI;
            buf[i] = (byte)(Math.sin(angle) * 127.0);
        }

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(audioFormat, buf, 0, buf.length);
            clip.start();
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
