import java.util.ArrayList;
import java.util.List;

//implements the behavior of a set, where elements cant appear twice
//additional feature of "score" which is the number of attempts to add an element
public class Set {
	
	List<Container> list;
	
	public Set() {
		list = new ArrayList<Container>();
	}
	
	public Set(String in) {
		list = new ArrayList<Container>();
		this.add(in);
	}
	
	public Set(List<String> in) {
		list = new ArrayList<Container>();
		for(String s: in)
			this.add(s);
	}
	
	
	//main working logic of the Set class
	public boolean add(String in) {
		for(Container c : list) {
			if(c.equal(in)) {
				c.Score();
				return false;
			}
		}
		list.add(new Container(in));
		return true;
	}
	
	//iterates over a list using the main add() method and returns an array of booleans representing the
	//successful and unsuccessful attempts to add an element
	public boolean[] add(List<String> in) {
		boolean[] temp = new boolean[in.size()];
		int nB = 0;
		for(String s : in)
			temp[nB++] = add(s);
		
		return temp;
	}
	
	public String toString() {
		String buffer = "";
		for(Container c : this.list)
			buffer += (c.data + " - " + c.score + "\n");
		return buffer;
	}
	
	public String[] toArray() {
		String[] strings = new String[list.size()];
		int nStrings = 0;
		
		for(Container c : list) {
			strings[nStrings++] = c.data;
		}
		
		return strings;
	}
	
	//returns an Array of the String data inside of (list<Container>).data
	public Container[] ArrayOfContainers() {
		Container[] containers = new Container[list.size()];
		int nContainers = 0;
		
		for(Container c : list) {
			containers[nContainers++] = c;
		}
		
		return containers;
	}
	
	//returns an ArrayList of the String data inside of (list<Container>).data
	public List<String> toList(){
		List<String> temp = new ArrayList<String>();
		
		for(Container c : list)
			temp.add(c.data);
		
		return temp;
	}
	
	//returns an ArrayList of all the Container.data Strings with their Container.score being 1, indicating unvisited URLs
	//for the crawler
	public List<String> unvisited(){
		List<String> temp = new ArrayList<String>();
		
		for(Container c : list)
			if(c.score == 1)
				temp.add(c.data);
		
		return temp;
	}
	
	public int size() {
		return list.size();
	}
	
	//returns a SORTED array of Containers, based on the score value
	public Container[] sortedArray() {
		Container[] temp = this.ArrayOfContainers();
		
		 for (int i = 0; i < temp.length - 1; i++) {
	            for (int j = 0; j < temp.length - i - 1; j++) {
	                if (temp[j].score < temp[j + 1].score) {
	                    Container swapper = temp[j];
	                    temp[j] = temp[j + 1];
	                    temp[j + 1] = swapper;
	                }
	            }
	        }
		 return temp;
	}
	
	public boolean allVisited() {
		for(Container c : list)
			if(c.score == 1)
				return false;
		return true;
	}
	
}
