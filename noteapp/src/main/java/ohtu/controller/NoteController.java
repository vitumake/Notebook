package ohtu.controller;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ohtu.view.NoteGui;
import ohtu.model.Notebook;
import ohtu.model.Note;
import ohtu.model.NoteFileHandler;

public class NoteController {
    private static Notebook noteList = new Notebook();

    @FXML
    private TextField titleField;

    @FXML
    private TextArea textField;

    @FXML
    private ListView<String> listView;

    @FXML
    private void submitNote() {
        if(titleField.getText().isEmpty() || textField.getText().isEmpty()) {
            return;
        }
        Note note = new Note(titleField.getText(), textField.getText(), System.currentTimeMillis());
        noteList.add(note);
        new Thread(() -> NoteFileHandler.writeData(noteList)).start(); // Sometimes had problems with this, so I added a thread
        updateListView();
        System.out.println(noteList.getTitles());
        titleField.clear();
        textField.clear();
    }

    @FXML
    private void openNote() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if(index >= 0) {
            NoteGui.openNote(noteList.find(index));
        }
    }

    @FXML
    void initialize() {
        NoteFileHandler.readData().forEach(note -> {
            noteList.add(note);
        });
        updateListView();
        System.out.println("Loaded notes: " + Arrays.toString(noteList.getTitles()));
    }

    private void updateListView() {
        if(listView!=null) { 
            listView.setItems(FXCollections.observableArrayList(noteList.getTitles()));
            System.out.println("listView updated");
        } else System.out.println("listView is null");
    }
    
    public void edit(int index, String title, String content, long timestamp) {
        noteList.getNotes().get(index).setTitle(title);
        noteList.getNotes().get(index).setContent(content);
        noteList.getNotes().get(index).setTimestamp(timestamp);
    }
    
    public static void main(String[] args) {
        NoteGui.launch(NoteGui.class);
    }
}
