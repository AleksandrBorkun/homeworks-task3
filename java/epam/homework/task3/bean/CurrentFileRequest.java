package epam.homework.task3.bean;

public class CurrentFileRequest {

	private static String currentFileName;

	public static String getCurrentFileName() {
		return currentFileName;
	}

	public static void setCurrentFileName(String currentFileName) {
		CurrentFileRequest.currentFileName = currentFileName;
	}
	
}
