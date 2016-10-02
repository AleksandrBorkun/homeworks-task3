package epam.homework.task3.command.impl;

import epam.homework.task3.bean.AddNoteRequest;
import epam.homework.task3.bean.CurrentFileRequest;
import epam.homework.task3.bean.Request;
import epam.homework.task3.bean.Response;
import epam.homework.task3.bean.entity.Note;
import epam.homework.task3.bean.entity.NoteBook;
import epam.homework.task3.command.Command;
import epam.homework.task3.command.exception.CommandException;
import epam.homework.task3.source.NoteBookProvider;

public class AddNewNote implements Command {

	public Response execute(Request request) throws CommandException {

		AddNoteRequest req = new AddNoteRequest();
		Response response = new Response();

		if (request instanceof AddNoteRequest) {
			req = (AddNoteRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		// Check connection to file
		if (CurrentFileRequest.getCurrentFileName() != null) {
			req.setFileName(CurrentFileRequest.getCurrentFileName());
		} else {
			response.setErrorStatus(true);
			response.setErrorMessage("Sorry! But first you need to load or create a NOTEBOOK file!");
			return response;
		}
		// getting note and add it to NoteBook
		String note = req.getNote();

		Note newNote = new Note(note);
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		noteBook.add(newNote);

		response.setErrorStatus(false);
		response.setResultMessage("All OK!");

		return response;
	}

}
