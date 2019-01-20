package hackathon2019;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class Mode {
	
	String name;
	String modeFileName;
	List<Process> processList;
	
	public Mode(String modeName, boolean prevMode) throws FileNotFoundException, UnsupportedEncodingException {
		
		if(prevMode) {
			this.modeFileName = modeName;
			this.name = modeName.replace("_mode_file", "");
			load();
		} else {
		this.name = modeName;
		this.modeFileName = modeName + "_mode_file";
		PrintWriter out = new PrintWriter(this.modeFileName, "UTF-8");
		}
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
			Process curr = new Process(currProcess[0], currProcess[1], Boolean.valueOf(currProcess[2]));
			processList.add(curr);
			
		}
		
		modeFile.close();
		return true;
		
	}
	
	public boolean add(String newName, String filePath, boolean browser) {
		
		Process newProcess = new Process(newName, filePath, browser);
		this.processList.add(newProcess);
		boolean outcome = appendModeFile(newName, filePath, browser);
		
		return outcome;
		
	}
	
	private boolean appendModeFile(String newName, String filePath, boolean browser) {
		
		try(FileWriter fw = new FileWriter(this.modeFileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)) {
			
			out.printf("%s,%s,%s\n", newName, filePath, Boolean.toString(browser));
			out.close();
			
		} catch(IOException ex) {
			
			return false;
			
		}
		
		return true;
		
	}
	
	public boolean remove(String name) {
		
		List<String> lines = new ArrayList<String>();
		
		try {
			
			File f = new File(this.modeFileName);
			lines = Files.readAllLines(f.toPath(), Charset.defaultCharset());
			Files.write(f.toPath(), removeLine(name, lines));
			
		} catch(IOException ex) {
			
			return false;
			
		}
		
		return true;
		
	}
	
	private List<String> removeLine(String name, List<String> lines) {
		
		List<String> newLines = new ArrayList<String>();
		for(String line : lines) {
			
			String[] process = line.split(",");
			if(process[0].equals(name)) continue;
			else newLines.add(line);
			
		}
		
		return newLines;
	}
	
	public boolean execute() {
		
		boolean result = true;
		for(Process curr : this.processList) {
			if(!curr.execute()) result = false;
		}
		return result;
	}
	
}