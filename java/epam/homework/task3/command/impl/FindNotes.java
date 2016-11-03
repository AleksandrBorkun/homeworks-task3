package epam.homework.task3.command.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import epam.homework.task3.bean.CurrentFileRequest;
import epam.homework.task3.bean.FindNotesRequest;
import epam.homework.task3.bean.Request;
import epam.homework.task3.bean.Response;
import epam.homework.task3.command.Command;
import epam.homework.task3.command.exception.CommandException;

public class FindNotes implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();
		FindNotesRequest req = new FindNotesRequest();
		if (request instanceof FindNotesRequest) {
			req = (FindNotesRequest) request;
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
		// .....
		String line;
		List<String> findNotes = new ArrayList<>();
		int lineNumber = 0;
		String fileName = req.getFileName();
		String keyWord = req.getKeyWords();
		File file = new File(fileName);
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while ((line = br.readLine()) != null) {
				lineNumber++;
				if (line.contains(keyWord)) {
					findNotes.add("Was found on the " + lineNumber + " line: " + line);
				}
			}
			br.close();

			if (lineNumber == 0) {
				response.setErrorMessage(
						"По заданному поиску ничего не найденно. Возможно следует сохранить проект и повторить поиск.\nВы искали: "
								+ keyWord);
				response.setErrorStatus(true);
				return response;

			}

			response.setErrorStatus(false);
			response.setFindNotes(findNotes);
			response.setResultMessage("Поиск завершен успешно");
			return response;

		} catch (IOException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;

		}

	}

}
