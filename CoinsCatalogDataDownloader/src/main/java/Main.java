import java.util.List;

public class Main {

	public static void main(String[] args) {
		Parser parser = Parser.getInstance();
		SqlBuilder sqlBuilder = SqlBuilder.getInstance();
		
		List<Coin> coins = parser.parse();
		
		for(Coin coin : coins)
			System.out.println(coin);
		
		String sql = sqlBuilder.createTable();
		sql += sqlBuilder.buildInsertQuery(coins);
	}
	
}
