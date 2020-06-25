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
	
	public List<Coin> parse(int initNumber) {
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
			System.out.println("\nINFO: URL opened: " + url);
			
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
		List<String> attributes = new ArrayList<String>();
		
		attributes.add(tableRow.getElementsByTag("h3").get(0).text());
		
		Elements tds = tableRow.getElementsByTag("td");
		Elements imgs = tableRow.getElementsByTag("img");
		
		for(Element td : tds) {
			Elements divs = td.getElementsByTag("div");
			
			for(Element div : divs) {
				if(div.hasClass("col-md-2 col-sm-6 col-xs-12") || div.hasClass("comments")) {
					attributes.add(div.text());
				}
			}
		}
		
		if(imgs.isEmpty())
			attributes.add("");
		else
			attributes.add(imgs.get(0).attr("src"));

		return new Coin(attributes);
	}
	
}
