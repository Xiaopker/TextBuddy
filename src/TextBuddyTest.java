import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

public class TextBuddyTest {

	@Test
	public void testAddToFile() {
		String expected = "added to mytextfile.txt: \"Test line added\"";
		assertEquals("add a test line", expected, TextBuddy.commandInput("add", "mytextfile.txt", "Test line added"));
		expected = "1. Test line added";
		assertEquals("check if test line is added successfully", expected, TextBuddy.commandInput("display", "mytextfile.txt", "display"));
		
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
		
		expected = "added to mytextfile.txt: \"Test line added\"";
		assertEquals("add more words to test command", expected, TextBuddy.commandInput("add", "mytextfile.txt", "Test line added"));
		expected = "1. Test line added\r\n";
		assertEquals("Check whether test line is added", expected, TextBuddy.commandInput("display", "mytextfile.txt", "display"));
		
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
	}

	@Test
	public void testClearFile() {
		TextBuddy.commandInput("add", "mytextfile.txt", "add This is the first test line.");
		TextBuddy.commandInput("add", "mytextfile.txt", "add This is the second test line.");
		TextBuddy.commandInput("add", "mytextfile.txt", "add This is the third test line.");
		
		String expected = "all content deleted from mytextfile.txt";
		assertEquals("clear the file", expected, TextBuddy.commandInput("clear", "mytextfile.txt", "clear"));
		expected = "mytextfile.txt is empty";
		assertEquals("check if file is empty", expected, TextBuddy.commandInput("display", "mytextfile.txt", "display"));
		
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
	}
	
	@Test 
	public void testSortFile() {
		TextBuddy.commandInput("add", "mytextfile.txt", "Alpha test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Delta test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Mike test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Beta test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Oscar test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Hotel test case");
		
		String expected = "mytextfile.txt is sorted";
		assertEquals("sort the file", expected, TextBuddy.commandInput("sort", "mytextfile.txt", "sort"));
		String expected2 = "1. Alpha test case\r\n2. Beta test case\r\n3. Delta test case\r\n4. Hotel test case\r\n5. Mike test case\r\n6. Oscar test case";
		assertEquals("check if contents have seen sorted correctly", expected2, TextBuddy.commandInput("display", "mytextfile.txt", "display"));	
		
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
	}

}
