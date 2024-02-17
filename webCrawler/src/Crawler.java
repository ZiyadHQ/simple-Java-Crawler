import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//the Crawler Class has the basic building blocks to a crawler, you should implement your own Crawler that
//extends this Class, as to better fit your needs instead of relying on the basic internal functions
//TODO: add (public/private) to variables of all classes in this project
public class Crawler {
	String rootURL;
	Set setOfURLs;
	Set fileBuffer;
	
	//the regex string to parse for in each HTML and then store in a buffer to print later on to a file(must be implemented by the user in Cycle())
	String parserRegex;
	
	//is an important part of this program since its unstable, each time "add()" is called "cycle" will decrement
	//and once it reaches zero it will call the "Cycle()" function, users are encouraged to change 
	//the implementation of "Cycle()"
	//"cycle" stores the current cycle number and "cycleSize" keeps the size of the cycle to re-initialize cycle each time
	int cycle;
	int cycleSize;
	
	//may be deleted as its a temporary buffer that can be implemented on the method level
	List<String> toVisit;
	
	//an option to decide which main website to browse for, if not added then it defaults to the empty string
	String siteToBrowse;
	
	//Error buffer List(instead of printing every error as it happens, store them in a buffer for the user to decide what to do with)
	List<String> Error;
	
	//the string that will be used to start the crawling process
	public Crawler(String URL, int cycle, String parser) {
		rootURL = URL;
		setOfURLs = new Set(URL);
		toVisit = setOfURLs.toList();
		siteToBrowse = "";
		this.cycle = cycle; cycleSize = cycle;
		Error = new ArrayList<String>();
		parserRegex = parser;
		fileBuffer = new Set();
		
		//KickStarts the process by trying to load all the possible branch URLs
		Browse(rootURL);
	}
	//constructor that also assigns a main site to browse(helps in avoiding browsing unrelated sites and going out of bounds*)
	//*where the crawler's scope expands so much that it becomes exponentially slower to browse
	public Crawler(String URL, int cycle,String parser, String site) {
		rootURL = URL;
		setOfURLs = new Set(URL);
		toVisit = setOfURLs.toList();
		siteToBrowse = site;
		this.cycle = cycle; cycleSize = cycle;
		Error = new ArrayList<String>();
		parserRegex = parser;
		fileBuffer = new Set();
		
		//KickStarts the process by trying to load all the possible branch URLs
		Browse(rootURL);
	}
	
	//returns the size of setOfURLs
	public int getSize() {
		return setOfURLs.size();
	}
	
	//returns a sorted(descending) array of Containers
	public Container[] Sorted() {
		return (setOfURLs.sortedArray());
	}
	
	//Runs the crawler for (depth) times, in this case it defaults to 3 times
	public void Run() {
		toVisit = setOfURLs.unvisited();
		int depth = 3;
		for(String s : toVisit) {
			Browse(s);
			if(depth-- == 0)
				return;
		}
		
	}
	
	//Run method with the option to chose depth (chose -1 for browsing the entire list)
	//TODO: make a method that recursively browses the setOfURLs list, it uses the special case of Run(1)
	//TODO: make a method that exhausts the setOfURLs recursively
	public void Run(int ndepth) {
		toVisit = setOfURLs.unvisited();
		int depth = ndepth;
		
		if(ndepth == -1) {
			for(String s : toVisit)
				Browse(s);
		}else {
			for(String s : toVisit)
				Browse(s);
				if(depth-- == 0)
					return;
			}
	}
	
	//fills the setOfURLs with the URLs found in the given webPage
	public void Browse(String URL) {
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {//e.printStackTrace();
			Error.add(e.getMessage());
			return;
			}

		String input = doc.toString();
		Pattern parser = Pattern.compile(parserRegex);
		Matcher parse = parser.matcher(input);
		Pattern regex = Pattern.compile("https:[a-zA-Z0-9._%+-/]*" + siteToBrowse + "[a-zA-Z0-9._%+-/]+");
		Matcher match = regex.matcher(input);
		while(match.find()) {
			setOfURLs.add(match.group());
			if(cycle-- == 0) {
				cycle = cycleSize;
				Cycle();
			}
		}
		
		while(parse.find()) {
			fileBuffer.add(parse.group());
		}
		
	}
	
	public String toString() {
		String temp = "";
		temp += (rootURL + " - foundURLs: " + setOfURLs.size() + "\n");
		temp += setOfURLs.toString();
		return temp;
	}
	
	//if all the links within the setOfURLs have been visited, then return true, signaling that there is nothing more to browse
	public boolean isExhausted() {
		return setOfURLs.allVisited();
	}
	
	public void Cycle() {
		//System.out.println("Cycle()");
		
	}
	
}
