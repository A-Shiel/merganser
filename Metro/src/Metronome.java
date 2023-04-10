import javafx.concurrent.Task;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class Metronome extends Task<Integer> {
    //TimerUS timer = new TimerUS();

    Timer t = new Timer();
    int bpm;
    boolean running;
    int loopDelay = 1000;

    int numOfClicks = 0;
    String soundName = "Snare.wav";
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());


    public Metronome(int b,String s) throws UnsupportedAudioFileException, IOException {
        this.running=false;
        this.numOfClicks=0;
        this.bpm=b;
        this.loopDelay = 1000 / (bpm / 60);
        s+=".wav";
        this.soundName=s;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(s).getAbsoluteFile());


    }
    public int getBPM() {
        return bpm;
    }
    public void setBpm(int bpm) {
        this.bpm = bpm;
        this.loopDelay = 1000 / (bpm / 60);
    }
    public void setTone(String s) throws UnsupportedAudioFileException, IOException {
        s+=".wav";
        this.soundName=s;

        System.out.println(s);



    }
    @Override
    protected Integer call() throws Exception {

        System.out.println("Clicks at " + bpm + " per second.");

        while (numOfClicks<9999) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            numOfClicks++;
            System.out.println("Click number: " + numOfClicks);

            Thread.sleep(loopDelay);
    }
        return numOfClicks;
}
}

