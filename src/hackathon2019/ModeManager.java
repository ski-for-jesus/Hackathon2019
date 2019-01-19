package hackathon2019;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ModeManager {
	
	List<Mode> modes;
	
	public ModeManager() throws FileNotFoundException, UnsupportedEncodingException {
		
		this.modes = createPrevModes();
	}
	
	public List<Mode> createPrevModes() throws FileNotFoundException, UnsupportedEncodingException {
		
		List<Mode> modes = new ArrayList<Mode>();
		File curr = new File(".");
		File[] fileList = curr.listFiles();
		for(File f : fileList) {
			if(f.isDirectory()) continue;
			else if(f.isFile()) {
				if(f.getName().endsWith("_mode_file")) {
					Mode temp = new Mode(f.getName(), true);
					modes.add(temp);
				}
			}
		}
		return modes;
	}
	
	public void addMode(String name) throws FileNotFoundException, UnsupportedEncodingException {
		
		Mode temp = new Mode(name, false);
		modes.add(temp);
	}
	
	public void removeMode(String name) {
		
		
	}
}