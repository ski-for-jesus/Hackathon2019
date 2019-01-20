package hackathon2019;
import java.io.IOException;

public class Process {
	
	String name;
	String filePath;
	boolean browser;
	
	public Process(String name, String filePath, boolean browser) {
		
		this.name = name;
		this.filePath = filePath;
		this.browser = browser;
		
	}
	
	public boolean execute() {
		
		if(browser) {
			
		} else {
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec(this.filePath);
			} catch(IOException ex) {
				return false;
			}
		}
		return true;
	}
}