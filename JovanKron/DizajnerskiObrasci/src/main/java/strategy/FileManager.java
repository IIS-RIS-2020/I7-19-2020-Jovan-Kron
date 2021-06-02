package strategy;

import java.io.File;

public class FileManager implements AnyFile {

	private AnyFile anyFile;
	
	public FileManager(AnyFile anyFile) {
		this.anyFile = anyFile;
	}
	
	@Override
	public void saveFile(File file) {
		anyFile.saveFile(file);
		
	}

	@Override
	public void loadFile(File file) {
		anyFile.loadFile(file);
		
	}

}
