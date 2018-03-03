package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {


    @FXML
    private TextField tf_source;


    @FXML
    private TextArea ta_dest_text;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tag Generator");

        Scene scene = new Scene(root, 640, 285);
        primaryStage.setScene(scene);
        primaryStage.show();

        Controller controller = loader.<Controller>getController();
        controller.initListeners();

        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (keyComb1.match(event)) {
                controller.handlerCopyToClipboard();
            }
        });


    }


    public static void main(String[] args) {
        launch(args);
    }
}
