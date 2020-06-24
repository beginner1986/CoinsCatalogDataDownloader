import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {

	private final String urlTemplate = "https://katalogfischer.pl/?from_1=";
	private Document document;
	
	public Parser() {
		String url = urlTemplate + "0";
		
		try {
			document = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
	                .referrer("http://www.google.com")
	                .ignoreHttpErrors(true)
	                .get();
			System.out.println("INFO: URL opened: " + url);
			
		} catch (IOException e) {
			System.out.println("ERROR: URL opening failed: " + url);
			e.printStackTrace();
		}
	}
	
}
