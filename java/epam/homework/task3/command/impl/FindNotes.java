package epam.homework.task3.command.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

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
					System.out.println("Was found on the " + lineNumber + " line: " + line);
				}
			}
			br.close();
			if (lineNumber == 0)
				System.out.println("По заданному поиску ничего не найденно. Возможно следует сохранить проект и повторить поиск.\nВы искали: " + keyWord);
			response.setErrorStatus(false);
			response.setResultMessage("Поиск завершен успешно");

		} catch (FileNotFoundException e1) {
			response.setErrorStatus(true);
			response.setErrorMessage("файл с таким именем: " + fileName + ". Попробуйте еще.");

		} catch (IOException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("чтото пошло не так... имя файла " + fileName + ". Попробуйте еще.");

		}

		return response;
	}

}
