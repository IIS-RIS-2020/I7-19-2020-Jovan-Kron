package strategy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.io.File;
import org.junit.*;

import dialogs.DlgCommandsConfirmedMock;
import dialogs.OptionPane;
import geometry.*;
import mvc.*;

public class LogFileTests {
	private DrawingModel model;
	private File file;
	private LogFile logFile;
	private OptionPane optionPane;
	private DrawingFrame frame;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
        frame.getView().setModel(model);
        DrawingController controller = new DrawingController(model, frame);
        frame.setController(controller);
        logFile = new LogFile(model, frame);
        optionPane = mock(OptionPane.class);
        logFile.setOptionPane(optionPane);
        logFile.setDlgCommandsAnswer(new DlgCommandsConfirmedMock());
	}
	
	@Test
	public void testSaveFileThatDoesExist() {
		file = new File("src/test/resources/files/logFiles/addShape/add-point");
		logFile.saveFile(file);
		verify(optionPane).showMessageDialog(frame, "File with same name already exists");
	}
	
	@Test
	public void testSaveFileThatDoesntExist() {
		file = new File("src/test/resources/files/logFiles/addShape/test");
		logFile.saveFile(file);
		file = new File("src/test/resources/files/logFiles/addShape/test.log");
		assertTrue(file.delete());
		verify(optionPane).showMessageDialog(frame, "The log file was saved successfully");
	}
	
	@Test
	public void testLoadFileThatDoesntExist() {
		file = new File("src/test/resources/doesntexist");
		logFile.loadFile(file);
		verify(optionPane).showMessageDialog(frame, "File does not exist");
	}
	
	@Test
	public void testLoadFileThatHasNoExtension() {
		file = new File("src/test/resources/files/has-no-extension");
		logFile.loadFile(file);
		verify(optionPane).showMessageDialog(frame, "File can't be loaded");
	}
	
	@Test
	public void testLoadFileThatHasWrongExtension() {
		file = new File("src/test/resources/files/has-wrong-extension.json");
		logFile.loadFile(file);
		verify(optionPane).showMessageDialog(frame, "File has to be of type log");
	}
	
	@Test
	public void testLoadFileWithAddPointOperation() {
		file = new File("src/test/resources/files/logFiles/addShape/add-point.log");
		logFile.loadFile(file);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Point);
		assertEquals(801, ((Point) model.get(0)).getX());
		assertEquals(205, ((Point) model.get(0)).getY());
		assertEquals(Color.BLACK, ((Point) model.get(0)).getEdgeColor());
		assertFalse(model.get(0).isSelected());
	}
	
	@Test
	public void testLoadFileWithAllPointOperationsWithoutPositioning() {
		file = new File("src/test/resources/files/logFiles/allShapeCommandsButPositioning/point.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithAddLineOperation() {
		file = new File("src/test/resources/files/logFiles/addShape/add-line.log");
		logFile.loadFile(file);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Line);
		assertEquals(592, ((Line) model.get(0)).getStartPoint().getX());
		assertEquals(409, ((Line) model.get(0)).getStartPoint().getY());
		assertEquals(898, ((Line) model.get(0)).getEndPoint().getX());
		assertEquals(196, ((Line) model.get(0)).getEndPoint().getY());
		assertEquals(Color.BLACK, ((Line) model.get(0)).getEdgeColor());
		assertFalse(model.get(0).isSelected());
	}
	
	@Test
	public void testLoadFileWithAllLineOperationsWithoutPositioning() {
		file = new File("src/test/resources/files/logFiles/allShapeCommandsButPositioning/line.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithAddCircleOperation() {
		file = new File("src/test/resources/files/logFiles/addShape/add-circle.log");
		logFile.loadFile(file);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Circle);
		assertEquals(871, ((Circle) model.get(0)).getCenter().getX());
		assertEquals(271, ((Circle) model.get(0)).getCenter().getY());
		assertEquals(Color.BLACK, ((Circle) model.get(0)).getEdgeColor());
		assertEquals(Color.YELLOW, ((Circle) model.get(0)).getFillColor());
		assertFalse(model.get(0).isSelected());
	}
	
	@Test
	public void testLoadFileWithAllCircleOperationsWithoutPositioning() {
		file = new File("src/test/resources/files/logFiles/allShapeCommandsButPositioning/circle.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithAddRectangleOperation() {
		file = new File("src/test/resources/files/logFiles/addShape/add-rectangle.log");
		logFile.loadFile(file);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Rectangle);
		assertEquals(681, ((Rectangle) model.get(0)).getUpperLeftPoint().getX());
		assertEquals(177, ((Rectangle) model.get(0)).getUpperLeftPoint().getY());
		assertEquals(Color.BLACK, ((Rectangle) model.get(0)).getEdgeColor());
		assertEquals(Color.YELLOW, ((Rectangle) model.get(0)).getFillColor());
		assertFalse(model.get(0).isSelected());
	}
	
	@Test
	public void testLoadFileWithAllRectangleOperationsWithoutPositioning() {
		file = new File("src/test/resources/files/logFiles/allShapeCommandsButPositioning/rectangle.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithAddDonutOperation() {
		file = new File("src/test/resources/files/logFiles/addShape/add-donut.log");
		logFile.loadFile(file);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Donut);
		assertEquals(752, ((Donut) model.get(0)).getCenter().getX());
		assertEquals(306, ((Donut) model.get(0)).getCenter().getY());
		assertEquals(Color.BLACK, ((Donut) model.get(0)).getEdgeColor());
		assertEquals(Color.YELLOW, ((Donut) model.get(0)).getFillColor());
		assertFalse(model.get(0).isSelected());
	}
	
	@Test
	public void testLoadFileWithAllDonutOperationsWithoutPositioning() {
		file = new File("src/test/resources/files/logFiles/allShapeCommandsButPositioning/donut.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileWithAddHexagonAdapterOperation() {
		file = new File("src/test/resources/files/logFiles/addShape/add-hexagon.log");
		logFile.loadFile(file);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof HexagonAdapter);
		assertEquals(800, ((HexagonAdapter) model.get(0)).getX());
		assertEquals(339, ((HexagonAdapter) model.get(0)).getY());
		assertEquals(Color.BLACK, ((HexagonAdapter) model.get(0)).getEdgeColor());
		assertEquals(Color.YELLOW, ((HexagonAdapter) model.get(0)).getFillColor());
		assertFalse(model.get(0).isSelected());
	}
	
	@Test
	public void testLoadFileWithAllHexagonAdapterOperationsWithoutPositioning() {
		file = new File("src/test/resources/files/logFiles/allShapeCommandsButPositioning/hexagon.log");
		logFile.loadFile(file);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileRemoveTwoShapesUndoRedoAll() {
		file = new File("src/test/resources/files/logFiles/undoRedoRemove/remove-two-shapes-undo-redo-all.log");
		logFile.loadFile(file);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Line);
		assertEquals(502, ((Line) model.get(0)).getStartPoint().getX());
		assertEquals(432, ((Line) model.get(0)).getStartPoint().getY());
		assertEquals(798, ((Line) model.get(0)).getEndPoint().getX());
		assertEquals(184, ((Line) model.get(0)).getEndPoint().getY());
		assertEquals(Color.BLACK, ((Line) model.get(0)).getEdgeColor());
		assertFalse(model.get(0).isSelected());
	}
	
	@Test
	public void testLoadFileRemoveTwoShapesUndoRedoRemove() {
		file = new File("src/test/resources/files/logFiles/undoRedoRemove/remove-two-shapes-undo-redo-remove.log");
		logFile.loadFile(file);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Line);
		assertEquals(517, ((Line) model.get(0)).getStartPoint().getX());
		assertEquals(462, ((Line) model.get(0)).getStartPoint().getY());
		assertEquals(725, ((Line) model.get(0)).getEndPoint().getX());
		assertEquals(248, ((Line) model.get(0)).getEndPoint().getY());
		assertEquals(Color.BLACK, ((Line) model.get(0)).getEdgeColor());
		assertFalse(model.get(0).isSelected());
	}
	
	@Test
	public void testLoadFileRemoveTwoShapesUndoRemove() {
		file = new File("src/test/resources/files/logFiles/undoRedoRemove/remove-two-shapes-undo.log");
		logFile.loadFile(file);
		assertEquals(3, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileRedoRemoveFollowedByAdd() {
		file = new File("src/test/resources/files/logFiles/undoRedoRemove/redo-remove-followed-by-add.log");
		logFile.loadFile(file);
		assertEquals(2, model.getShapes().size());
	}
	
	@Test
	public void testLoadFileToFront() {
		file = new File("src/test/resources/files/logFiles/positioning/to-front.log");
		logFile.loadFile(file);
		assertTrue(model.get(1) instanceof Donut);
	}
	
	@Test
	public void testLoadFileBringToFront() {
		file = new File("src/test/resources/files/logFiles/positioning/bring-to-front.log");
		logFile.loadFile(file);
		assertTrue(model.get(2) instanceof Donut);
	}
	
	@Test
	public void testLoadFileToBack() {
		file = new File("src/test/resources/files/logFiles/positioning/to-back.log");
		logFile.loadFile(file);
		assertTrue(model.get(1) instanceof HexagonAdapter);
	}
	
	@Test
	public void testLoadFileBringToBack() {
		file = new File("src/test/resources/files/logFiles/positioning/bring-to-back.log");
		logFile.loadFile(file);
		assertTrue(model.get(0) instanceof HexagonAdapter);
	}
	
}
