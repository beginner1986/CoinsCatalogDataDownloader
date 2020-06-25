import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	private static Parser instance = null;
	private final String urlTemplate = "https://katalogfischer.pl/?from_1=";

	private Parser() {}
	
	public static Parser getInstance() {
		if(instance == null)
			instance = new Parser();
		
		return instance;
	}
	
	public List<Coin> parse() {

		int initNumber = 0;		// it will be iterated by 20 in final version
		
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
		Elements tables = document.getElementsByTag("table");
		Element table = tables.first();
		
		return table;
	}
	
	// each table row contains single coin data
	private Elements getTableRows(Element table) {
		Elements tableRows = table.getElementsByTag("tr");

		return tableRows;
	}
	
	private List<Coin> getCoinsFromTableRows(Elements tableRows) {
		List<Coin> coins = new ArrayList<Coin>();
		
		for(int i=1; i<tableRows.size(); i+=3) {
			Coin coin = getCoinFromSingleRow(tableRows.get(i));
			coins.add(coin);
		}
		
		return coins;
	}
	
	private Coin getCoinFromSingleRow(Element tableRow) {
		Coin coin = new Coin();
		List<String> attributes = new ArrayList<String>();
		
		Elements tds = tableRow.getElementsByTag("td");
		Elements imgs = tableRow.getElementsByTag("img");
		
		for(Element td : tds) {
			Elements strongs = td.getElementsByTag("strong");
			for(Element strong : strongs) {
				attributes.add(strong.text());
			}
		}
		
		attributes.add(imgs.get(0).attr("src"));
		
		// DEBUG
		for(String s : attributes)
			System.out.print(s + " | ");
		System.out.println();
		
		// TODO: should read empty fields
		
		return coin;
	}
	
}