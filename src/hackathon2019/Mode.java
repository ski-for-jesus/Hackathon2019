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
		
		this.processList = new ArrayList<Process>();
		
		while(modeFile.hasNextLine()) {
			
			String line = modeFile.nextLine();
			String[] currProcess = line.split(",");
			Process curr = new Process(currProcess[0], currProcess[1]);
			processList.add(curr);
			
		}
		
		return true;
		
	}
	
}