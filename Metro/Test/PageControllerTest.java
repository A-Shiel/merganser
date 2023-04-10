import javafx.animation.Animation;
import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.Assert.*;

public class PageControllerTest {

    @Test
    public void testSetResume() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        PageController controller = new PageController();
        controller.setStart(null);
        controller.setStop(null);

        controller.setResume(null);
        assertTrue(controller.timeline!=null);
        assertTrue(controller.thread.isAlive());

    }

    @Test
    public void testSetStop() throws UnsupportedAudioFileException, IOException, InterruptedException {
        PageController controller = new PageController();
        controller.setStop(null);
        assertFalse(controller.thread.isAlive());
        assertFalse(controller.timeline.getStatus() == Animation.Status.PAUSED);

    }

    @Test
    public void testSetStart() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        PageController controller = new PageController();
        controller.setStart(null);
        assertTrue(controller.thread.isAlive());

    }

    @Test
    public void testSetStartT() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        PageController controller = new PageController();
        controller.setStart(null);

        assertTrue(controller.thread.isAlive());
        assertTrue(controller.timeline!=null);


    }

    @Test
    public void testSetTimer() throws UnsupportedAudioFileException, IOException {
        PageController controller = new PageController();

        assertEquals(60,controller.DisplayTime.intValue());
    }

    @Test
    public void testSetBPm() throws UnsupportedAudioFileException, IOException {
        PageController controller = new PageController();

        assertEquals(60,controller.metronome.bpm);
    }

    @Test
    public void setTone() throws UnsupportedAudioFileException, IOException {
        PageController controller = new PageController();

        assertEquals("Snare.wav", controller.metronome.soundName);



    }
}