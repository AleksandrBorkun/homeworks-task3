package epam.homework.task3.bean.entity;

import java.util.LinkedList;
import java.util.List;

public class NoteBook {

//	Note newNote;
	List<Note> notes = null;

	public NoteBook() {
		notes = new LinkedList<Note>();
	}

	public void add(Note newNote) {
		this.notes.add(newNote);
	}

	//
//	public static void setNotes(List<Note> notes) {
//		NoteBook.notes = notes;
//	}
	
	public List<Note> getNotes() {
		return notes;
	}
}
