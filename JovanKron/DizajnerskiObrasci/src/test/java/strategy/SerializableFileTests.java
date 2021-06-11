package strategy;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.*;
import geometry.*;
import mvc.*;

public class SerializableFileTests {
	private DrawingModel model;
	private File file;
	private SerializableFile serializableFile;

	@Before
	public void setUp() {
		model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
        frame.getView().setModel(model);
        DrawingController controller = new DrawingController(model, frame);
        frame.setController(controller);
        serializableFile = new SerializableFile(model, frame);
	}
	
	@Test
	public void testSaveFileThatDoesExist() {
		file = new File("src/test/resources/files/serializableFiles/all-shapes-drawn");
		serializableFile.saveFile(file);
	}
	
	@Test
	public void testSaveFileThatDoesntExist() {
		file = new File("src/test/resources/files/serializableFiles/test");
		serializableFile.saveFile(file);
		file = new File("src/test/resources/files/serializableFiles/test.ser");
		file.delete();
	}
	
	@Test
	public void testLoadFileThatDoesntExist() {
		file = new File("src/test/resources/doesntexist");
		serializableFile.loadFile(file);
	}
	
	@Test
	public void testLoadFileThatHasNoExtension() {
		file = new File("src/test/resources/files/has-no-extension");
		serializableFile.loadFile(file);
	}
	
	@Test
	public void testLoadFileThatHasWrongExtension() {
		file = new File("src/test/resources/files/has-wrong-extension.json");
		serializableFile.loadFile(file);
	}
	
	@Test
	public void testLoadFile() {
		file = new File("src/test/resources/files/serializableFiles/all-shapes-drawn.ser");
		serializableFile.loadFile(file);
		assertEquals(6, model.getShapes().size());
		assertTrue(model.get(0) instanceof Point);
		assertTrue(model.get(1) instanceof Line);
		assertTrue(model.get(2) instanceof Donut);
		assertTrue(model.get(3) instanceof Circle);
		assertTrue(model.get(4) instanceof Rectangle);
		assertTrue(model.get(5) instanceof HexagonAdapter);
	}
	
}
