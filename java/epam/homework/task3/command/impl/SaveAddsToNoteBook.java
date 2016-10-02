package epam.homework.task3.command.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import epam.homework.task3.bean.CurrentFileRequest;
import epam.homework.task3.bean.Request;
import epam.homework.task3.bean.Response;
import epam.homework.task3.bean.SaveNotesRequest;
import epam.homework.task3.bean.entity.Note;
import epam.homework.task3.command.Command;
import epam.homework.task3.command.exception.CommandException;
import epam.homework.task3.source.NoteBookProvider;

public class SaveAddsToNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		SaveNotesRequest req = new SaveNotesRequest();
		Response response = new Response();
		if (request instanceof SaveNotesRequest) {
			req = (SaveNotesRequest) request;
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
		// ....

		String filePath = req.getFileName();
		List<Note> notes = NoteBookProvider.getInstance().getNoteBook().getNotes();

		for (Note ad : notes) {
			PrintWriter save;
			try {
				File fileName = new File(filePath);
				save = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
				save.println(ad.getNote());
				save.close();
				response.setErrorStatus(false);
				response.setResultMessage("Notes is saved to file: \'" + filePath + "\'");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return response;
	}

}
