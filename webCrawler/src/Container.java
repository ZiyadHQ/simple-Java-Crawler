
public class Container {
	String data;
	int score;
	
	public Container(String in) {
		data = in;
		score = 1;
	}
	
	public void Score() {
		score++;
	}
	
	public boolean equal(Container that) {
		return (this.data.equals(that.data));
	}
	
	public boolean equal(String in) {
		return (this.data.equals(in));
	}
	
	public String toString() {
		return( (this.data + " " + this.score) );
	}
	
}
