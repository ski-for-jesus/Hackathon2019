package hackathon2019;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Process {
	
	String name;
	String filePath;
	boolean browser;
	boolean checked = false;
	
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
			checkOS();
			System.out.println(this.filePath);
			try {
				
				rt.exec(this.filePath);
			} catch(IOException ex) {
				return false;
			}
		}
		return true;
	}
	
	public void checkOS() {
		if(checked) return;
		String os = System.getProperty("os.name").toLowerCase();
		if(os.indexOf("mac") >= 0) {
			System.out.println("THIS IS A MAC");
			String[] temp = this.filePath.split("/");
			String appName = temp[temp.length - 1];
			int index = appName.indexOf('.');
			appName = appName.substring(0, index);
			this.filePath += "/Contents/MacOS/" + appName;
			checked = true;
		}
	}
}