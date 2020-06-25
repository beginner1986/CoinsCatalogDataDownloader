import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	private final String urlTemplate = "https://katalogfischer.pl/?from_1=";
	
	public Parser() {
		// TODO: singleton
	}
	
	public List<Coin> parse() {

		int initNumber = 0;		// it be iterated by 20 in final version
		Document document = openCatalogPage(initNumber);
		Element table = getTable(document);
		Elements tableRows = getTableRows(table);
		List<Coin> result = getCoinsFromTableRows(tableRows);
		
		return result;
	}
	
	private Document openCatalogPage(int initNumber) {
		String url = urlTemplate + Integer.toString(initNumber);
		
		try {
			Document document = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
	                .referrer("http://www.google.com")
	                .ignoreHttpErrors(true)
	                .get();
			System.out.println("INFO: URL opened: " + url);
			
			return document;
		} catch (IOException e) {
			System.out.println("ERROR: URL opening failed: " + url);
			e.printStackTrace();
			
			return null;
		}
	}
	
	// the first table contains coins data
	private Element getTable(Document document) {
		// TODO 
		return null;
	}
	
	// each table row contains single coin data
	private Elements getTableRows(Element table) {
		// TODO
		return null;
	}
	
	private List<Coin> getCoinsFromTableRows(Elements tableRows) {
		// TODO
		return null;
	}
	
	private Coin getCoinFromSingleRow(Element tableRow) {
		// TODO
		return null;
	}
	
}
