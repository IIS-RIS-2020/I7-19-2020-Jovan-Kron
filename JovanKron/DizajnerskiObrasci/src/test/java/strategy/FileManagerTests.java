package strategy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class FileManagerTests {
	
	private FileManager fileManager;
	private AnyFile anyFile;
	private File file;
	
	@Before
	public void setUp() {
		anyFile = mock(AnyFile.class);
		fileManager = new FileManager(anyFile);
		file = new File("");
	}
	
	@Test
	public void testSaveFile() {
		fileManager.saveFile(file);
		verify(anyFile).saveFile(file);
	}
	
	@Test
	public void testLoadFile() {
		fileManager.loadFile(file);
		verify(anyFile).loadFile(file);
	}
}
