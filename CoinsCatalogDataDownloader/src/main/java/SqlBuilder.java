import java.util.List;

public class SqlBuilder {

	private static SqlBuilder instance = null;

	private SqlBuilder() {}
	
	public static SqlBuilder getInstance() {
		if(instance == null)
			instance = new SqlBuilder();
		
		return instance;
	}
	
	public String createTable() {
		StringBuilder sb = new StringBuilder();

		sb.append("CREATE TABLE coins (")
			.append("id INT AUTO_INCREMENT PRIMARY KEY,")
			.append("name VARCHAR(255),")
			.append("code VARCHAR(255),")
			.append("year VARCHAR(31),")
			.append("diameter VARCHAR(31),")
			.append("shape VARCHAR(255),")
			.append("inscription VARCHAR(255),")
			.append("circulation VARCHAR(255),")
			.append("metal VARCHAR(255),")
			.append("fineness VARCHAR(255),")
			.append("weight VARCHAR(255,")
			.append("stamp VARCHAR(255),")
			.append("border VARCHAR(255,")
			.append("comment VARCHAR(255),")
			.append("photo VARCHAR(255)")
			.append(");\n\n");
		
		System.out.println("INFO: SQL table prepared.");
		
		return sb.toString();
	}
	
	public String buildInsertQuery(List<Coin> coins) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("INSERT INTO coins (name, code, year, diameter, shape, "
				+ "inscription, circulation, metal, fineness, weight, stamp, "
				+ "border, comment, photo) VALUES\n");
		
		for(Coin coin : coins) {
			sb.append("\t")
				.append(insertSingleCoin(coin))
				.append(",\n");
		}
		
		sb.setLength(sb.length() - 2);
		sb.append(";\n");
		
		System.out.println("INFO: SQL query prepared.");
		
		return sb.toString();
	}
	
	private String insertSingleCoin(Coin coin) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("('").append(coin.getName()).append("', ")
			.append("'").append(coin.getCode()).append("', ")
			.append("'").append(coin.getYear()).append("', ")
			.append("'").append(coin.getDiameter()).append("', ")
			.append("'").append(coin.getShape()).append("', ")
			.append("'").append(coin.getInscription()).append("', ")
			.append("'").append(coin.getCirculation()).append("', ")
			.append("'").append(coin.getMetal()).append("', ")
			.append("'").append(coin.getFineness()).append("', ")
			.append("'").append(coin.getWeight()).append("', ")
			.append("'").append(coin.getStamp()).append("', ")
			.append("'").append(coin.getBorder()).append("', ")
			.append("'").append(coin.getComment()).append("', ")
			.append("'").append(coin.getPhoto()).append("')");
		
		return sb.toString();
	}
	
}
