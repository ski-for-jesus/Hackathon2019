package hackathon2019;
import java.io.*;
import java.util.*;

public class Mode {
	
	String name;
	String modeFileName;
	List<Process> processList;
	
	public Mode(String modeName) {
		
		this.name = modeName;
		this.modeFileName = modeName + "_mode_file";
	
	}
	
	public boolean load() {
		
		Scanner modeFile;
		
		try {
			
		modeFile = new Scanner(new File(this.modeFileName));
		
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
	
	public boolean newProcess(String newName, String filePath) {
		
		Process newProcess = new Process(newName, filePath);
		this.processList.add(newProcess);
		boolean outcome = appendModeFile(newName, filePath);
		
		return outcome;
		
	}
	
	private boolean appendModeFile(String newName, String filePath) {
		
		try(FileWriter fw = new FileWriter(this.modeFileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)) {
			
			out.printf("%s,%s\n", newName, filePath);
			out.close();
			
		} catch(IOException ex) {
			
			return false;
			
		}
		
		return true;
		
	}
	
	public boolean remove(String newName) {
		
		return true;
		
	}
	
	
	
}