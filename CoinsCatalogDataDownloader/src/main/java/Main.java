import java.util.List;

public class Main {

	public static void main(String[] args) {
		Parser parser = Parser.getInstance();
		SqlBuilder sqlBuilder = SqlBuilder.getInstance();
		FileManager fileManager = FileManager.getInstance();
		
		String sql = sqlBuilder.createTable();
		fileManager.write(sql);
		
		List<Coin> coins = parser.parse();
		
		sql = sqlBuilder.buildInsertQuery(coins);
		fileManager.write(sql);

		fileManager.dispose();
	}
	
}
