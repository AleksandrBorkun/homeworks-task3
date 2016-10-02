package epam.homework.task3.command;

import epam.homework.task3.bean.Request;
import epam.homework.task3.bean.Response;
import epam.homework.task3.command.exception.CommandException;

public interface Command {
	
	Response execute(Request request) throws CommandException;
	
	
}
