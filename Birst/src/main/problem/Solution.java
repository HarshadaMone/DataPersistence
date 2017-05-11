

package main.problem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The program implements Key Value store that can 
 * create, update, delete and fetch the Key-Value pairs it receives 
 * from then standard input.
 * It accepts hierarchical keys.
 * It persists the data between the program runs. 
 * It provides user with CLI options like Clean-cache and File, 
 * to reset the Key-Value store and to read commands 
 * and input through file respectively.
 * It also implements Unit and Function tests using JUnit and TestNG.
 *
 * 
 * @author Harshada
 *
 */


/* Creates options, accepts input through command line and file */
public class Solution {

	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException{
		
		PersistData pd = new PersistData();
		Solution s = new Solution();
		Options option = new Options();
		KeyValueStore kvs = null;
		// Option clear_cache to clear persisted values
		option.addOption("clear_cache",false,"Clear persisted values on startup");
		// Option -file requires file path as the option value
		option.addOption("file",true,"Read from file");
		pd.deserializeHashMap();
		kvs = new KeyValueStore(pd.getKvs());
		BufferedReader br = null;
		boolean flag = true,qflag = true;
		String inputCommand = null;
		CommandLineParser clp = new DefaultParser();
		CommandLine cl = clp.parse(option, args);	
		if(cl.hasOption("clear_cache")) {
			System.out.println("Inside Clear cache");
			kvs.reset();
			pd.clearCache();
		}
		while(qflag){
			
			 if(cl.hasOption("file") && flag){
				String path = cl.getOptionValue("file");
				File file = new File(path);
				br = new BufferedReader(new FileReader(file));
				String userInput;	
				while (( userInput = br.readLine()) != null && qflag) {
					inputCommand = s.performOperation(userInput, kvs);
					if(inputCommand.equals(new String("QUIT")))
						qflag = false;
				} 
				pd.serializeHashMap(kvs.getStore());
				flag = false;
			}else{
				br = new BufferedReader(new InputStreamReader(System.in));
				String userInput = br.readLine();
				inputCommand = s.performOperation(userInput, kvs);
				System.out.println("Input command inside"+inputCommand);
			}
			System.out.println("Round and Command "+inputCommand);
			if(inputCommand.equals(new String("QUIT")))
				qflag = false;
		}
		if(!qflag)
			pd.serializeHashMap(kvs.getStore());
		
	}
	
	public String performOperation(String userInput,KeyValueStore kvs){
		
		String[] input = userInput.split(" ");
		String[] keyVal;
		switch(input[0]){
			
			case "CREATE" : keyVal = input[1].split("=");
							if(keyVal[0]!=null && keyVal[1]!=null)
							kvs.create(keyVal[0], keyVal[1]);
							break;
		
			case "UPDATE" : keyVal = input[1].split("=");
							if(keyVal[0]!=null && keyVal[1]!=null)
							kvs.update(keyVal[0], keyVal[1]);
							break;							
			
			case "GET" : 	kvs.get(input[1]);
							break;
			
			case "DELETE" : kvs.delete(input[1]);
							break;
			
			case "GETALL" : kvs.getAll();
							break;
							
			case "QUIT" : 	
							break;
							
			default : System.out.println("Enter valid input");
		}
		
		System.out.println(input[0]+" Command");
		return input[0];
		
	}
}
