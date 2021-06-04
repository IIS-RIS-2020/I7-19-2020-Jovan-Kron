package strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class LogFileTests {
	
	private DrawingFrame frame;
	private DrawingModel model;
	private DrawingController controller;
	private File file;
	private LogFile logFile;

	@Before
	public void setUp() {
		model = new DrawingModel();
        frame = new DrawingFrame();
        frame.getView().setModel(model);
        controller = new DrawingController(model, frame);
        frame.setController(controller);
        logFile = new LogFile(model, frame);
	}
	
	@Test
	public void testLoadFileThatDoesntExist() {
		file = new File("src/test/resources/doesntexist");
		logFile.loadFile(file);
	}
	
	@Test
	public void testLoadFileThatHasNoExtension() {
		file = new File("src/test/resources/has-no-extension");
		logFile.loadFile(file);
	}
	
	@Test
	public void testLoadFileThatHasWrongExtension() {
		file = new File("src/test/resources/has-wrong-extension.json");
		logFile.loadFile(file);
	}
	
	@Test
	public void testLoadFileWithPointOperations() {
		file = new File("src/test/resources/point.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithLineOperations() {
		file = new File("src/test/resources/line.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithCircleOperations() {
		file = new File("src/test/resources/circle.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithRectangleOperations() {
		file = new File("src/test/resources/rectangle.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithDonutOperations() {
		file = new File("src/test/resources/donut.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithHexagonAdapterOperations() {
		file = new File("src/test/resources/hexagon.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
}
