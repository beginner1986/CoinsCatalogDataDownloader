import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Parser parser = Parser.getInstance();
		SqlBuilder sqlBuilder = SqlBuilder.getInstance();
		FileManager fileManager = FileManager.getInstance();
		
		String sql = sqlBuilder.createTable();
		fileManager.write(sql);
		
		for(int i=0; i<=3500; i+=20) {
			List<Coin> coins = parser.parse(i);
			sql = sqlBuilder.buildInsertQuery(coins);
			fileManager.write(sql);
			
			sleep();
		}
		
		fileManager.dispose();
	}
	
	private static void sleep() {
		try {
			Random random = new Random();
			int sleepTime = 5 + random.nextInt(10);
			System.out.println("INFO: Sleeptime: " + sleepTime);
			Thread.sleep(sleepTime * 1000);
		} catch (InterruptedException e) {
			System.out.println("ERROR: Slleping thread error.");
			e.printStackTrace();
		}
	}
	
}
