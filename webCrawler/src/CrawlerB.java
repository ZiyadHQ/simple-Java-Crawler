import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//TODO: implements the tools to open and write onto files, also create them when appropriate
//TODO: more utilities for easier tuning of the crawler, easier to run for scripts for external
//programs, also option to use runtime args?
public class CrawlerB extends Crawler{
	
	String fileName;
	File textFile;
	
	//a parameter that adjusts how deep the parser searches within a root URL
	int searchDepth;
	
	public CrawlerB(String URL, int cycle, String parser, String site) {
		super(URL, cycle, parser, site);
		searchDepth = 5;
	}
	
	public void setDepth(int in) {
		searchDepth = in;
	}
	
	public void setFile(String fileName) {
		this.fileName = fileName;
	}
	
	public void Run() {
		for(int i=0;i<searchDepth;i++) {
			this.Run(searchDepth);
		}
	}
	
	
	//Stores the contents of fileBuffer onto the file with the name fileName
	public void storeToFile() {
	    textFile = null; // Ensure the variable is declared outside the try-catch block	    
	    try {
	        textFile = new File(fileName);
	        if (textFile.createNewFile()) {
	        } else {
	        }
	    } catch(Exception e) {
	        e.printStackTrace(); // It's good practice to handle exceptions, not ignore them.
	    }
	    
	    FileWriter writer = null;
	    try {
	        writer = new FileWriter(fileName, false); // false to overwrite. true to append.
	        String finalString = "";
	        
	        // Assuming fileBuffer is a Collection of Strings
	        for(String s : fileBuffer.toArray()) // Assuming fileBuffer is declared and initialized elsewhere
	            finalString += (s) + "\n";
	        
	        System.out.println(finalString);
	        
	        writer.write(finalString);
	        writer.close(); // Close the writer to flush and finalize the file writing
	        System.out.println("Written!");
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (writer != null) {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	

}
