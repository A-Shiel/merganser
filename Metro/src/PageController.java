import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PageController implements Initializable {
    public Button startM;
    public Button startT;
    public Button setT;
    public Button setM;
    public TextField mT;
    public TextField tT;
    public ChoiceBox<String> tones;
    public Button pause;
    public Button resume;
    public String sh;
    public Button stop;
    public Label timeE;
     Integer STARTTIME = 60;
    Integer DisplayTime = 60;

    public Timeline timeline= new Timeline();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);


    String[] toneChoices = {"Snare","Bass","Hi-hat","Tom","StarWars"};

    int beats;
    Metronome metronome = new Metronome(60,"Snare");

    Thread thread = new Thread(metronome);
    /*Time t = new Time(60);
    Thread thr = new Thread(t);*/

    public PageController() throws UnsupportedAudioFileException, IOException {
    }
    @FXML
    public void setResume(ActionEvent event) throws UnsupportedAudioFileException, IOException {
        thread.resume();
        timeline.play();
    }
    @FXML
    public void setStop(ActionEvent event) throws InterruptedException, UnsupportedAudioFileException, IOException {
        thread.suspend();
        timeline.pause();

    }


    @FXML
    public void setStart(ActionEvent event) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if (thread.isDaemon() == false) {
            invokeMetronomeTask();
        }
        thread.resume();


    }
    @FXML
    public void setStartT(ActionEvent event){
        if (thread.isDaemon() == false) {
            invokeMetronomeTask();
        }
        thread.resume();

        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(DisplayTime);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(DisplayTime+1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        timeline.setOnFinished((e) -> thread.suspend());
    }
    @FXML
    public void setTimer(ActionEvent event) {
        String timerINPUT = tT.getText();
        int maxTime = Integer.valueOf(timerINPUT);
        DisplayTime=maxTime;
        timeline.stop();
        thread.suspend();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(DisplayTime+1),
                        new KeyValue(timeSeconds, 0)));
        timeSeconds.set(DisplayTime);

    }


    private void invokeMetronomeTask() {
        thread.setDaemon(true);
        thread.start();
        thread.suspend();
    }
    @FXML
    public void setBPm(ActionEvent event){
        String s =mT.getText();
        beats = Integer.valueOf(s);
        System.out.println(s);
        metronome.setBpm(beats);
    }
    @FXML
    public void setTone(ActionEvent event) throws UnsupportedAudioFileException, IOException {
        String s = tones.getValue();
        metronome.setTone(s);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tones.getItems().addAll(toneChoices);
        tones.setValue(toneChoices[0]);
        timeE.textProperty().bind(timeSeconds.asString());


    }
}
