import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
		CrawlerImg images = new CrawlerImg("https://desuarchive.org/int/search/text/faces%20of%20%2Fint%2F/", 10, "https://[a-zA-Z0-9._%+-/]+image[a-zA-Z0-9._%+-/]+.(jpg|png)", "faces");
		images.Run(1);
		images.printImageURLs();
		images.fileBuffer.toArray();
		String[] URLs = images.fileBuffer.toArray();
		String dir = "C:\\Users\\zxzx-\\Downloads\\images\\int";
		for(String s : URLs) {
			try {
				
				String filename = s.substring(s.lastIndexOf("/") + 1);
				try(InputStream in = new URL(s).openStream()){
					Files.copy(in, Paths.get(dir + filename));
                    System.out.println("Downloaded: " + filename);
				}
			
			}catch(Exception e) {
				System.out.println("Failed: " + s);
			}
		}
		
		
	}

}
