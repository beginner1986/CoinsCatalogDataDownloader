import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

	private static FileManager instance = null;
	private String fileName = "dabatase.sql";
	private BufferedWriter writer;
	
	private FileManager() {
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			System.out.println("INFO: File " + fileName + " opened");
		} catch (IOException e) {
			System.out.println("ERROR: Failed to open file " + fileName);
			e.printStackTrace();
		}
	}
	
	public static FileManager getInstance() {
		if(instance == null)
			instance = new FileManager();
		
		return instance;				
	}
	
	public void write(String content) {
		try {
			System.out.println("INFO: Data saved to the file.");
			writer.write(content);
		} catch (IOException e) {
			System.out.println("ERROR: Failed write data to the file.");
			e.printStackTrace();
		}
	}
	
	public void dispose() {
		try {
			writer.close();
			System.out.println("INFO: File " + fileName + " closed");
		} catch (IOException e) {
			System.out.println("ERROR: Failed to close file " + fileName);
			e.printStackTrace();
		}
	}
}
