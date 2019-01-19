package hackathon2019;
import java.io.*;
import java.util.*;

public class Mode {
	
	String name;
	List<Process> processList;
	
	public Mode(String modeName) {
		
		this.name = modeName;
	
	}
	
	public boolean load() {
		
		Scanner modeFile;
		
		try {
			
		modeFile = new Scanner(new File(name + "_mode_file"));
		
		} catch (Exception FileNotFoundException) {
			
			return false;
			
		}
		
		while(modeFile.hasNextLine()) {
			
			
			
		}
		
		return true;
		
	}
	
}