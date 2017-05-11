package test.problem;
import main.problem.*;

import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;
public class TestClass extends TestCase {

	private Solution s = new Solution();
	
	
	@Test
	public void testStdInput() throws IOException {	
	      
		  KeyValueStore kvs = new KeyValueStore();
		  // Test case to check against different inputs
		  String[] input = {"CREATE harshada=mone","CREATE parvez=patel","GET harshada","CREATE colonnade/harshada=mone","CREATE colonnade/ankur=soni","CREATE colonnade.priyanka=bongale","GETALL","GET colonnade","DELETE colonnade.harshada","Quit"};
		  for(int i=0;i<input.length-1;i++){
		  System.out.println("Inside testSTdInput for input  "+ input[i]);    
		  String[] expected = input[i].split(" ");
	      assertEquals(expected[0], s.performOperation(input[i], kvs));
		  }
		  
		  
		  
	}
	
	@Test
	public void testKeyValStore(){
		  KeyValueStore kvs = new KeyValueStore();
		  // Test create, update, Delete for different inputs
		  assertEquals("Test Create",new Boolean(true),kvs.create("Harshada", "Mone"));
		  assertEquals("Test Duplicate",new Boolean(false),kvs.create("Harshada+M", "one"));
		  assertEquals("Test Delete for key that does not exist in store",false, kvs.delete("harsha"));
		  assertEquals("Test Delete",true, kvs.delete("Harshada"));
		  assertEquals("Test Update for key that does not exist in store",false, kvs.update("Harshada", "Mone"));
		  kvs.create("Harshada", "Mone");
		  assertEquals("Test Update",true, kvs.update("Harshada", "Mone"));
	}
	
	
}
