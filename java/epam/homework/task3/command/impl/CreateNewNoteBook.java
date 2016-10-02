package epam.homework.task3.command.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import epam.homework.task3.bean.CreateNewNoteBookRequest;
import epam.homework.task3.bean.Request;
import epam.homework.task3.bean.Response;
import epam.homework.task3.command.Command;
import epam.homework.task3.command.exception.CommandException;
import epam.homework.task3.date.FullDate;

public class CreateNewNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();
		CreateNewNoteBookRequest req = new CreateNewNoteBookRequest();
		FullDate date = new FullDate();

		if (request instanceof CreateNewNoteBookRequest) {
			req = (CreateNewNoteBookRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		File newFile = new File(req.getCreate());

		try {
			FileWriter addText = new FileWriter(newFile);
			addText.write("You just generating a new file with name: <" + req.getCreate() + "> at: "
					+ date.getFullDate() + "\n\n");
			addText.close();
		} catch (IOException e) {

			response.setErrorStatus(true);
			response.setErrorMessage("I think that something wrong with file name.\n"
					+ "Please try to create new file again with another name.");

		}

		response.setErrorStatus(false);
		response.setResultMessage("File was created!");

		return response;
	}

}
