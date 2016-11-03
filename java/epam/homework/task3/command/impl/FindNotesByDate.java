package epam.homework.task3.command.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import epam.homework.task3.bean.CurrentFileRequest;
import epam.homework.task3.bean.FindNotesByDateRequest;
import epam.homework.task3.bean.Request;
import epam.homework.task3.bean.Response;
import epam.homework.task3.command.Command;
import epam.homework.task3.command.exception.CommandException;
import epam.homework.task3.date.DateChekFormat;

public class FindNotesByDate implements Command {

	public Response execute(Request request) throws CommandException {
		Response response = new Response();
		FindNotesByDateRequest req = null;

		if (request instanceof FindNotesByDateRequest) {
			req = (FindNotesByDateRequest) request;
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
		// ...

		List<String> findNoteByDate = new ArrayList<>();
		String line;
		int lineNumber = 0;
		String fileName = req.getFileName();
		String keyWord = req.getSearchDate();
		File file = new File(fileName);
		BufferedReader br;

		// Checking searching date format, and then make a search
		if (DateChekFormat.isValid(keyWord)) {
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				while ((line = br.readLine()) != null) {
					lineNumber++;
					if (line.contains(keyWord)) {
						findNoteByDate.add("Was found on the " + lineNumber + " line: " + line);
					}
				}
				br.close();
				if (lineNumber == 0) {
					response.setErrorStatus(true);
					response.setErrorMessage(
							"По заданному поиску ничего не найденно. Возможно следует сохранить проект и повторить поиск. \nВы искали по дате: "
									+ keyWord);
					return response;

				}
				response.setErrorStatus(false);
				response.setFindNoteByDate(findNoteByDate);
				response.setResultMessage("Поиск завершен успешно");

			} catch (IOException e) {
				response.setErrorStatus(true);
				response.setErrorMessage(e.getMessage());
				return response;

			}
		} else {
			response.setErrorStatus(true);
			response.setErrorMessage(
					"WRONG FORMAT" + keyWord + "\nPlease write date in correct format. \'yyyy-MM-dd\' ");
			return response;
		}

		return response;
	}

}
