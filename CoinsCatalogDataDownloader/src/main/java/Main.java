import java.util.List;

public class Main {

	public static void main(String[] args) {
		Parser parser = Parser.getInstance();
		SqlBuilder sqlBuilder = SqlBuilder.getInstance();
		
		List<Coin> coins = parser.parse();
		
		String sql = sqlBuilder.createTable();
		sql += sqlBuilder.buildInsertQuery(coins);
		
		System.out.println(sql);
		System.out.println();
		
		for(Coin coin : coins)
			System.out.println(coin);
	}
	
}
