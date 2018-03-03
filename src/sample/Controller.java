package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private Pane pane_root;


    @FXML
    private TextArea ta_dest_text;

    @FXML
    private Button btn_copy_to_clipboard;

    @FXML
    private CheckBox chbx_ru;

    @FXML
    private CheckBox chbx_digets;

    @FXML
    private CheckBox chbx_last_part_hyphen;

    @FXML
    private CheckBox chbx_last_part_sp;

    @FXML
    private CheckBox chbx_dirty_letters;

    @FXML
    private TextField tf_source;

    @FXML
    void handleOnSourceTextChanged(ActionEvent event) {

    }


    void handlerCopyToClipboard() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(ta_dest_text.getText());
        clipboard.setContent(content);
    }

    @FXML
    void handlerCopyToClipboard(ActionEvent event) {
        handlerCopyToClipboard();
    }


    private void changeDestText(String s){
        App app = new App();
        String resultString = app.concatResult(s,
                chbx_ru.isSelected(),
                chbx_digets.isSelected(),
                chbx_last_part_hyphen.isSelected(),
                chbx_last_part_sp.isSelected(),
                chbx_dirty_letters.isSelected()
        );
        ta_dest_text.clear();
        ta_dest_text.appendText(resultString);
    }


    public void initListeners() {
        tf_source.textProperty().addListener((observable, oldValue, newValue) -> changeDestText(newValue));
    }

    public void handleChbx(ActionEvent event) {
        changeDestText(tf_source.getText());
    }
}
