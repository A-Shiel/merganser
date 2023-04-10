import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.Assert.*;

public class MetronomeTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testGetBPM() {
    }
    @org.junit.Test
    public void testConstructor() throws UnsupportedAudioFileException, IOException {
        Metronome metronome = new Metronome(120, "Snare");
        assertEquals(120, metronome.getBPM());
        assertEquals("Snare.wav", metronome.soundName);

    }

    @org.junit.Test
    public void testSetBpm() throws UnsupportedAudioFileException, IOException {
        Metronome metronome = new Metronome(120, "Snare");
        metronome.setBpm(60);
        assertEquals(60, metronome.getBPM());
    }

    @org.junit.Test
    public void testSetTone() throws UnsupportedAudioFileException, IOException {
        Metronome metronome = new Metronome(120, "Snare");
        metronome.setTone("Kick");
        assertEquals("Kick.wav", metronome.soundName);
    }

    @org.junit.Test
    public void testCall() throws Exception {
        Metronome metronome = new Metronome(120, "Snare");
        assertEquals("Snare.wav",metronome.soundName);
        assertEquals(120,metronome.bpm);
        assertEquals(metronome.numOfClicks,0);
        metronome.numOfClicks=9998;
        metronome.call();
        assertEquals(9999, metronome.call().intValue());


    }
}