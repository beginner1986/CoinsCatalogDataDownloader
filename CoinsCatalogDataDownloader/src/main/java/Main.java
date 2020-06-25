import java.util.List;

public class Main {

	public static void main(String[] args) {
		Parser parser = Parser.getInstance();
		
		List<Coin> coins = parser.parse();
		
		for(Coin coin : coins)
			System.out.println(coin);
	}
	
}
