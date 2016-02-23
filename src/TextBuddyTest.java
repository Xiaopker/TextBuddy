import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {

	@Test
	public void testAddToFile() {
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
		
		String expected = "added to mytextfile.txt: \"Test line added\"";
		assertEquals("add a test line", expected, TextBuddy.commandInput("add", "mytextfile.txt", "Test line added"));
		expected = "1. Test line added\r\n";
		assertEquals("check if test line is added successfully", expected, TextBuddy.commandInput("display", "mytextfile.txt", "display"));
		
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
		
		expected = "added to mytextfile.txt: \"Test line added add adds adding\"";
		assertEquals("add more words to test command", expected, TextBuddy.commandInput("add", "mytextfile.txt", "Test line added add adds adding"));
		expected = "1. Test line added add adds adding\r\n";
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
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
		
		TextBuddy.commandInput("add", "mytextfile.txt", "Alpha test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Delta test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Mike test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Beta test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Oscar test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "Hotel test case");
		
		String expected = "mytextfile.txt is sorted alphabetically";
		assertEquals("sort the file", expected, TextBuddy.commandInput("sort", "mytextfile.txt", "sort"));
		String expected2 = "1. Alpha test case\r\n2. Beta test case\r\n3. Delta test case\r\n4. Hotel test case\r\n5. Mike test case\r\n6. Oscar test case\r\n";
		assertEquals("check if contents have seen sorted correctly", expected2, TextBuddy.commandInput("display", "mytextfile.txt", "display"));	
		
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
	}
	
	@Test
	public void testSearchFromFile() {
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
		
		TextBuddy.commandInput("add", "mytextfile.txt", "alpha test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "delta test cases");
		TextBuddy.commandInput("add", "mytextfile.txt", "mike test case");
		TextBuddy.commandInput("add", "mytextfile.txt", "alpha test cases");
		TextBuddy.commandInput("add", "mytextfile.txt", "delta tests case");
		TextBuddy.commandInput("add", "mytextfile.txt", "hotel tests case");
		

		String expected = "1. alpha test case\r\n\r\n2. alpha test cases\r\n\r\n";
		assertEquals("check if lines containing same words is searched", expected, TextBuddy.commandInput("search", "mytextfile.txt", "alpha"));
		
		String expected2 = "1. delta test cases\r\n\r\n2. alpha test cases\r\n\r\n";
		assertEquals("check if lines containing same words is searched", expected2, TextBuddy.commandInput("search", "mytextfile.txt", "cases"));
		
		String expected3 = "1. alpha test case\r\n\r\n2. mike test case\r\n\r\n3. delta tests case\r\n\r\n4. hotel tests case\r\n\r\n";
		assertEquals("check if line containing same words is searched", expected3, TextBuddy.commandInput("search", "mytextfile.txt", "case"));
		
		TextBuddy.commandInput("clear", "mytextfile.txt", "clear");
	}
}

