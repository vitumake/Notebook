package ohtu.model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NoteFileHandler {
    
    public static void writeData(Notebook notes) {

        try {
            FileOutputStream fileOut = new FileOutputStream("notebook.data");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for(Note n : notes.getNotes()) {
                out.writeObject(n);
            }
            out.close();
            fileOut.close();
            System.out.println("Saved notes to notebook.data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Note> readData() {
        boolean eof = false;
        Note temp;
        ArrayList<Note> notes = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("notebook.data");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (Note) in.readObject();
            while (!eof) {
                try {
                    notes.add(temp);
                    temp = (Note) in.readObject();
                } catch (EOFException e) {
                    eof = true;
                }
            }
            in.close();
            fileIn.close();
            System.out.println("Loaded notes from notebook.ser");
        } catch (FileNotFoundException e) {
            System.out.println("Notedata file was not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }
}
