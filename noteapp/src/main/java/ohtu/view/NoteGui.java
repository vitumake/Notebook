package ohtu.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import ohtu.model.Note;

public class NoteGui extends Application {
    
    private static Dialog<String> noteDialog = new Dialog<>();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/note_view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void openNote(Note note) {
        noteDialog.setTitle(note.getTitle());
        noteDialog.setContentText(note.getContent());
        noteDialog.getDialogPane().getButtonTypes().clear();
        noteDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        noteDialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(NoteGui.class);
    }
}
