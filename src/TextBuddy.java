import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TextBuddy {
	
	private static final String MESSAGE_ADD_TO_FILE = "added to %s: \"%s\"";
	private static final String MESSAGE_CLEAR = "all content deleted from %s";
	private static final String MESSAGE_EMPTY = "%s is empty";
	private static final String MESSAGE_LINE_DELETED = "deleted from %s: \"%s\"";
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use";
	private static final String MESSAGE_SORTED = "%s is sorted";
	private static final String MESSAGE_SEARCHED = "Searching text file...";
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		welcomeSentence(args[0]);
		firstDisplayed(args[0]);
	}
	
	// Print according to the name of the file specified along with the welcome message
	private static void welcomeSentence(String file) {
		System.out.print("\n");
		System.out.println(String.format(MESSAGE_WELCOME, file));
		System.out.print("\n");
	}
	
	private static String getOnlyCommand(String command) {
		return command.split("\\s+")[0];
	}
	
	private static String getOnlyInput(String command) {
		return command.replace(getOnlyCommand(command), "");
	}
	
	// Print the first page for input when program loads
	public static void firstDisplayed(String file) {
		while (true) {
			System.out.print("command: ");			
			String command = sc.nextLine();
			String partialCommandcommand = getOnlyCommand(command);
			String partialCommandInput = getOnlyInput(command);
//			String command = sc.next();			
			commandInput(partialCommandcommand, file, partialCommandInput);
		}
	}
	
	// List of commands for command input
	public static String commandInput(String command, String file, String input) {
		String returnValue = "";
		if (command.equals("add")) {
			// Add line input by user to file if keyword "add (input line)" is typed
			returnValue = addToFile(file, input);
		} else if (command.equals("clear")) {
			// Clear all lines on file if keyword "clear" is typed
			returnValue = clearFile(file);
		} else if (command.equals("display")) {
			// Display all lines in file if keyword "display" is typed
			returnValue = displayFile(file);
		} else if (command.equals("delete")) {
			// Delete line specified by user input if keyword "delete (line number in file)" typed
			deleteFromFile(file, input);
		} else if (command.equals("exit")) {
			// Terminating the program upon keyword "exit" typed
			System.exit(0);
		}
		return returnValue;
		
	}
	
	//To add
	public static String addToFile(String file, String input) {	
		try {
			FileWriter fileW = new FileWriter(file, true);
			BufferedWriter buffW = new BufferedWriter(fileW);
	//		String inputData = sc.nextLine();
	//		inputData = inputData.trim();
			input = input.trim();
			
			buffW.write(input);
			buffW.newLine();
			buffW.close();
			
			System.out.print("\n");
			System.out.println(String.format(MESSAGE_ADD_TO_FILE, file, input));
			System.out.print("\n");	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return String.format(MESSAGE_ADD_TO_FILE, file, input);
	}
	
	public static String clearFile(String file) {
		try {
			FileWriter fileW = new FileWriter(file);
			
			fileW.write("");
			fileW.flush();
			fileW.close();
			
			System.out.print("\n");
			System.out.println(String.format(MESSAGE_CLEAR, file));
			System.out.print("\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return String.format(MESSAGE_CLEAR, file);
	}
	
	public static String displayFile(String file) {
		try {
			
			File originalFile = new File(file);
			
			if (originalFile.length() == 0) {
				System.out.print("\n");
				System.out.println(String.format(MESSAGE_EMPTY, file));
				System.out.print("\n");
			} else {				
				FileReader fileR = new FileReader(file);
				BufferedReader buffR = new BufferedReader(fileR);
				String output = "";
				String line;
				int lineNo = 0;
				
				while ((line = buffR.readLine())!= null) {
					lineNo += 1;
					System.out.print("\n");
					System.out.println(lineNo + ". " + line);
					output += lineNo + ". " + line;
				}
				System.out.print("\n");
				buffR.close();
				return output;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return String.format(MESSAGE_EMPTY, file);
	}
	
	private static void deleteFromFile(String file, String input) {
		try {
			File originalFile = new File(file);
			File tempFile = new File("tempFile.txt");
			String line;
			String lineMessage = "";
			int lineNo = 0;
			int lineNoScan = Integer.parseInt(input.trim());
			
			FileReader fileR = new FileReader(file);
			BufferedReader buffR = new BufferedReader(fileR);
			
			FileWriter fileW = new FileWriter(tempFile);
			BufferedWriter buffW = new BufferedWriter(fileW);
			
			while ((line = buffR.readLine()) != null) {
				lineNo += 1;
				if (lineNo != lineNoScan) {
					buffW.write(line);
					buffW.newLine();
				} else {
					lineMessage = line;
				}			
			}
			
			System.out.print("\n");
			System.out.println(String.format(MESSAGE_LINE_DELETED, file, lineMessage));
			System.out.print("\n");

			buffW.close();
			buffR.close();
			
			originalFile.delete();
			tempFile.renameTo(originalFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}