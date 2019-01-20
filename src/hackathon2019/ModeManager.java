package hackathon2019;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeManager {
	
	private Map<String, Mode> modes;
	
	public ModeManager() throws FileNotFoundException, UnsupportedEncodingException {
		
		this.modes = createPrevModes();
	}
	
	public Map<String, Mode> createPrevModes() throws FileNotFoundException, UnsupportedEncodingException {
		
		Map<String, Mode> modes = new HashMap<String, Mode>();
		File curr = new File(".");
		File[] fileList = curr.listFiles();
		for(File f : fileList) {
			if(f.isDirectory()) continue;
			else if(f.isFile()) {
				if(f.getName().endsWith("_mode_file")) {
					Mode temp = new Mode(f.getName(), true);
					modes.put(f.getName().replace("_mode_file", ""), temp);
				}
			}
		}
		return modes;
	}
	
	public boolean addMode(String name) throws FileNotFoundException, UnsupportedEncodingException {
		
		if(modes.containsKey(name)) {
			return false;
		}
		Mode temp = new Mode(name, false);
		modes.put(name, temp);
		return true;
	}
	
	public boolean removeMode(String name) {
		
		if(!modes.containsKey(name)) {
			return false;
		}
		File file = new File(modes.get(name).modeFileName);
		if(!file.delete()) {
			return false;
		}
		modes.remove(name);
		return true;
	}
	
	public List<String> getNames() {
		
		List<String> names = new ArrayList<String>();
		for(String name : this.modes.keySet()) {
			names.add(name);
		}
		return names;
	}
	
	public boolean execute(String modeName) {
		
		Mode chosenMode = modes.get(modeName);
		return chosenMode.execute();
		
	}
	
	public Mode getMode(String modeName) {
		
		if(!modes.containsKey(modeName)) return null;
		return modes.get(modeName);
	}
}