package epam.homework.task3.bean.entity;

import epam.homework.task3.date.FullDate;

public class Note {
	
	FullDate date = new FullDate();
	private String note;

	public Note(String note) {

		this.note = note;

	}

	public String getNote() {
		return date.getFullDate() + ": " + note;
	}
}
