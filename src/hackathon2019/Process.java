package hackathon2019;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
			System.out.println(this.filePath);
			try {
				Desktop.getDesktop().browse(new URI(this.filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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