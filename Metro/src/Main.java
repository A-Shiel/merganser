import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Main extends Application{
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Page.fxml"));//load resources from fxml file
        Scene scene = new Scene(root);//get scene from root
        stage.setTitle("metronome");//set title to grizzbox
        stage.setScene(scene);//set scene
        stage.show();//show stage
        stage.setOnCloseRequest((e) -> {
            System.exit(0);
        });

}
    public static void main(String[] args) throws FileNotFoundException {

        launch(args);
    }
}