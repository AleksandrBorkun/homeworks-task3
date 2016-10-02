package epam.homework.task3.source;

import epam.homework.task3.bean.entity.NoteBook;

public class NoteBookProvider {
	
	private static final NoteBookProvider instance = new NoteBookProvider();
	
	private NoteBook noteBook;
	
	private NoteBookProvider(){
		noteBook = new NoteBook();
	}
	
	
	public static NoteBookProvider getInstance(){
		return instance;
	}
	
	
	public NoteBook getNoteBook(){
		return noteBook;
	}

}
