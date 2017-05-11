package test.problem;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import main.problem.KeyValueStore;
import main.problem.Solution;
//Run using TestNG plug-in in eclispe
public class TestFunctions {
	private Solution s = new Solution();
	KeyValueStore kvs = new KeyValueStore();
	@DataProvider
	public Object[][] inputCommands(){
		return new Object[][]{ {"CREATE harshada=mone","CREATE"},{"GET harshada","GET"},{"GETALL","GETALL"},{"DELETE colonnade.harshada","DELETE"},{"Quit","Quit"}};
	}
	//Check command execution CREATE,UPDATE,DELETE,GET,GETALL,QUIT
	@Test(dataProvider = "inputCommands")
	public void testInputCommands(String input, String expected){
		
		Assert.assertEquals(s.performOperation(input, kvs),expected);
	}
	
}
