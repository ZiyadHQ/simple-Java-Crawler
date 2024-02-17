import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

//this Crawler is designed to pull images from a URL, it uses the same crawling process as the parent class, with aditional
//tools to find image URLs and store their image data onto this device
//TODO: compare the performance and utility of a java and python implementation for the saveToFile() method
public class CrawlerImg extends Crawler{

	public CrawlerImg(String URL, int cycle, String parser, String site) {
		super(URL, cycle, parser, site);
		}
	
	public void printImageURLs() {
		System.out.println(this.fileBuffer);
		
		}
		
	
	public void Cycle() {
	}
	
	
}
