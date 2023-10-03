package ohtu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notebook {
    private List<Note> notes = new ArrayList<Note>();

    public List<Note> getNotes() {
        return notes;
    }

    public void add(Note note) {
        notes.add(note);
    }

    public String[] getTitles() {
        return notes.stream().map(n->n.getTitle()).collect(Collectors.toList()).toArray(new String[notes.size()]);
    }

    public Note find(int index) {
        return notes.get(index);
    }
}
