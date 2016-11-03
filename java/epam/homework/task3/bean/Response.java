package epam.homework.task3.bean;

import java.util.ArrayList;
import java.util.List;

public class Response {
	private boolean errorStatus;

	private String errorMessage;
	private String resultMessage;
	List<String> findNotes = new ArrayList<>();
	List<String> findNoteByDate = new ArrayList<>();
	List<String> showNotes = new ArrayList<>();

	public List<String> getShowNotes() {
		return showNotes;
	}

	public void setShowNotes(List<String> showNotes) {
		this.showNotes = showNotes;
	}

	public List<String> getFindNoteByDate() {
		return findNoteByDate;
	}

	public void setFindNoteByDate(List<String> findNoteByDate) {
		this.findNoteByDate = findNoteByDate;
	}

	public List<String> getFindNotes() {
		return findNotes;
	}

	public void setFindNotes(List<String> findNotes) {
		this.findNotes = findNotes;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
