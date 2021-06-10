package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Stack;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import commands.*;
import dialogs.*;
import geometry.*;
import observer.ObserverForButtons;
import strategy.*;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Point startPoint;
	private boolean flagForLine = true;
	private Color currentEdgeColor = Color.BLACK;
	private Color currentFillColor = Color.YELLOW;
    private Stack<Command> undoCommandsStack = new Stack<Command>();
	private Stack<Command> redoCommandsStack = new Stack<Command>();
	private Stack<String> textUndoCommandsStack = new Stack<String>();
	private Stack<String> textRedoCommandsStack = new Stack<String>();

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void addPointOnClick(MouseEvent click) {
        DlgPoint dialog = new DlgPoint();
        dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor);
        dialog.setVisible(true);
        if(dialog.isConfirmed()) {
        	Point point = new Point(click.getX(), click.getY(), dialog.getEdgeColor());
        	addShape(point);
        }
	}
	
	public void addLineOnClick(MouseEvent click) {
        if (flagForLine) {
            startPoint = new Point(click.getX(), click.getY());
            flagForLine = false;
        } else {
        	DlgLine dialog = new DlgLine();
        	dialog.fillForAdd(startPoint.getX(), startPoint.getY(), click.getX(), click.getY(), currentEdgeColor);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
            	Line line = new Line(startPoint, new Point(click.getX(), click.getY()), dialog.getEdgeColor());
                addShape(line);
                flagForLine = true;
            }
        }
	}
	
	public void addRectangleOnClick(MouseEvent click) {
         DlgRectangle dialog = new DlgRectangle();
         dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor, currentFillColor);
         dialog.setVisible(true);
         if (dialog.isConfirmed()) {
         	Rectangle rectangle = new Rectangle(new Point(click.getX(), click.getY()), dialog.getRectangleWidth(), dialog.getRectangleHeight(), dialog.getEdgeColor(), dialog.getFillColor());
            addShape(rectangle);
         }
	}
	
	public void addCircleOnClick(MouseEvent click) {
        DlgCircle dialog = new DlgCircle();
        dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor, currentFillColor);
        dialog.setVisible(true);        
        if(dialog.isConfirmed()) {
        	Circle circle = new Circle(new Point(click.getX(), click.getY()), dialog.getRadius(), dialog.getEdgeColor(), dialog.getFillColor());
            addShape(circle);
        }
	}
	
	public void addDonutOnClick(MouseEvent click) {
        DlgDonut dialog = new DlgDonut();
        dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor, currentFillColor);
        dialog.setVisible(true);
        if(dialog.isConfirmed()) {
        	Donut donut = new Donut(new Point(click.getX(), click.getY()), dialog.getOuterRadius(), dialog.getInnerRadius(), dialog.getEdgeColor(), dialog.getFillColor());
            addShape(donut);
        }
	}
	
	public void addHexagonOnClick(MouseEvent click) {
        DlgHexagon dialog = new DlgHexagon();
        dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor, currentFillColor);
        dialog.setVisible(true);
        if(dialog.isConfirmed()) {
        	HexagonAdapter hexagon = new HexagonAdapter(click.getX(), click.getY(), dialog.getRadius(), dialog.getEdgeColor(), dialog.getFillColor());
            addShape(hexagon);
        }
	}
	
	public void selectShape(MouseEvent click) {
		CmdSelect cmdSelect = null;
		Shape shape = null;
		for(int i = model.getShapes().size() - 1; i >= 0; i--) {
			shape = model.get(i);
			if(shape.contains(click.getX(), click.getY())) {
				if(shape.isSelected())
					cmdSelect = new CmdSelect(shape, false);
				else
					cmdSelect = new CmdSelect(shape, true);
				executeAndLogCommand(cmdSelect, shape, null);
				break;
			}
		}
	}
	
	public void deleteShapes() {
		CmdRemove cmdRemove;
		Shape shape;
		for(int i = model.getShapes().size() - 1; i >= 0; i--) {
			shape = model.get(i);
			if(shape.isSelected()) {
				cmdRemove = new CmdRemove(shape, model);
				executeAndLogCommand(cmdRemove, shape, null);
			}
		}
		frame.setAllShapeManipultationButtonsState(false);
    }
	
	public void modifyShape() {
		Shape originalShape = findMostTopSelectedShape();
        if(originalShape instanceof Point)
        	modifyPoint(originalShape);
        else if(originalShape instanceof Line)
        	modifyLine(originalShape);
        else if(originalShape instanceof Rectangle)
        	modifyRectangle(originalShape);        
        else if(originalShape instanceof Donut)
        	modifyDonut(originalShape);            
        else if(originalShape instanceof Circle)
        	modifyCircle(originalShape);
        else if(originalShape instanceof HexagonAdapter)
        	modifyHexagonAdapter(originalShape);
	}
	
	private void modifyPoint(Shape originalShape) {
		Point originalPoint = (Point) originalShape;
		Shape updatedShape = null;
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.fillForModify(originalPoint.getX(), originalPoint.getY(), originalPoint.getEdgeColor());
		dlgPoint.setVisible(true);
		if(dlgPoint.isConfirmed()) {
			updatedShape = new Point(dlgPoint.getBaseCoordinateX(), dlgPoint.getBaseCoordinateY(), dlgPoint.getEdgeColor());
			updateShape(originalShape, updatedShape);
		}
	}
	
	private void modifyLine(Shape originalShape) {
		Line originalLine = (Line) originalShape;
		Shape updatedShape = null;
        DlgLine dlgLine = new DlgLine();
        dlgLine.fillForModify(originalLine.getStartPoint().getX(), originalLine.getStartPoint().getY(),
        		originalLine.getEndPoint().getX(), originalLine.getEndPoint().getY(), originalLine.getEdgeColor());
        dlgLine.setVisible(true);
        if(dlgLine.isConfirmed()) {
        	updatedShape = new Line(new Point(dlgLine.getBaseCoordinateX(), dlgLine.getBaseCoordinateY()), new Point(dlgLine.getEndX(), dlgLine.getEndY()), dlgLine.getEdgeColor());
        	updateShape(originalShape, updatedShape);
        }
	}
	
	private void modifyRectangle(Shape originalShape) {
		Rectangle originalRectangle = (Rectangle) originalShape;
		Shape updatedShape = null;
        DlgRectangle dlgRectangle = new DlgRectangle();
        dlgRectangle.fillForModify(originalRectangle.getUpperLeftPoint().getX(), originalRectangle.getUpperLeftPoint().getY(), originalRectangle.getWidth(), originalRectangle.getHeight(), originalRectangle.getEdgeColor(), originalRectangle.getFillColor());
        dlgRectangle.setVisible(true);
        if(dlgRectangle.isConfirmed()) {
        	updatedShape = new Rectangle(new Point(dlgRectangle.getBaseCoordinateX(), dlgRectangle.getBaseCoordinateY()), dlgRectangle.getRectangleWidth(), dlgRectangle.getRectangleHeight(), dlgRectangle.getEdgeColor(), dlgRectangle.getFillColor());
        	updateShape(originalShape, updatedShape);
        }
	}
	
	private void modifyDonut(Shape originalShape) {
		Donut originalDonut = (Donut) originalShape;
		Shape updatedShape = null;
        DlgDonut dlgDonut = new DlgDonut();
        dlgDonut.fillForModify(originalDonut.getCenter().getX(), originalDonut.getCenter().getY(), originalDonut.getInnerRadius(), originalDonut.getRadius(), originalDonut.getEdgeColor(), originalDonut.getFillColor());
        dlgDonut.setVisible(true);
        if(dlgDonut.isConfirmed()) {
        	updatedShape = new Donut(new Point(dlgDonut.getBaseCoordinateX(), dlgDonut.getBaseCoordinateY()), dlgDonut.getOuterRadius(), dlgDonut.getInnerRadius(), dlgDonut.getEdgeColor(), dlgDonut.getFillColor());
        	updateShape(originalShape, updatedShape);
        }
	}
	
	private void modifyCircle(Shape originalShape) {
		Circle originalCircle = (Circle) originalShape;
		Shape updatedShape = null;
        DlgCircle dlgCircle = new DlgCircle();
        dlgCircle.fillForModify(originalCircle.getCenter().getX(), originalCircle.getCenter().getY(), originalCircle.getRadius(), originalCircle.getEdgeColor(), originalCircle.getFillColor());
        dlgCircle.setVisible(true);
        if(dlgCircle.isConfirmed()) {
        	updatedShape = new Circle(new Point(dlgCircle.getBaseCoordinateX(), dlgCircle.getBaseCoordinateY()), dlgCircle.getRadius(), dlgCircle.getEdgeColor(), dlgCircle.getFillColor());
        	updateShape(originalShape, updatedShape);
        }
	}
	
	private void modifyHexagonAdapter(Shape originalShape) {
		HexagonAdapter originalHexagonAdapter = (HexagonAdapter) originalShape;
		Shape updatedShape = null;
        DlgHexagon dlgHexagon = new DlgHexagon();
        dlgHexagon.fillForModify(originalHexagonAdapter.getX(), originalHexagonAdapter.getY(), originalHexagonAdapter.getR(), originalHexagonAdapter.getEdgeColor(), originalHexagonAdapter.getFillColor());
        dlgHexagon.setVisible(true);
        if (dlgHexagon.isConfirmed()) {
        	updatedShape = new HexagonAdapter(dlgHexagon.getBaseCoordinateX(), dlgHexagon.getBaseCoordinateY(), dlgHexagon.getRadius(), dlgHexagon.getEdgeColor(), dlgHexagon.getFillColor());
        	updateShape(originalShape, updatedShape);
        }
	}
	
	public void toBack() {
		Shape shapeToMove = findMostTopSelectedShape();
		if(model.getShapes().indexOf(shapeToMove) != 0)
			executeAndLogCommand(new CmdToBack(shapeToMove, model), shapeToMove, null);
		else
			JOptionPane.showMessageDialog(null, "Shape is already on the back");
	}
	
	public void toFront() {
		Shape shapeToMove = findMostTopSelectedShape();
		if(model.getShapes().indexOf(shapeToMove) != model.getShapes().size() - 1)
			executeAndLogCommand(new CmdToFront(shapeToMove, model), shapeToMove, null);
		else
			JOptionPane.showMessageDialog(null, "Shape is already on the front");
	}
	
	public void bringToBack() {
		Shape shapeToMove = findMostTopSelectedShape();
		if(model.getShapes().indexOf(shapeToMove) != 0)
			executeAndLogCommand(new CmdBringToBack(shapeToMove, model), shapeToMove, null);
		else
			JOptionPane.showMessageDialog(null, "Shape is already on the back");
	}
	
	public void bringToFront() {
		Shape shapeToMove = findMostTopSelectedShape();
		if(model.getShapes().indexOf(shapeToMove) != model.getShapes().size() - 1)
			executeAndLogCommand(new CmdBringToFront(shapeToMove, model), shapeToMove, null);
		else
			JOptionPane.showMessageDialog(null, "Shape is already on the front");
	}
	
	public void undo() {
		if (!(undoCommandsStack.peek() instanceof CmdRemove))
			executeUndo();
		else {
			int deletedShapesToUndoCount = 0;
			while (undoCommandsStack.peek() instanceof CmdRemove) {
				executeUndo();
				deletedShapesToUndoCount++;
			}
			if(deletedShapesToUndoCount == 1)
				frame.setAllShapeManipultationButtonsState(true);
			else {
				frame.getTglBtnModify().setEnabled(false);
				frame.getTglBtnDelete().setEnabled(true);
			}
		}
		if(undoCommandsStack.isEmpty())
			frame.getTglBtnUndo().setEnabled(false);
		if(!redoCommandsStack.isEmpty())
			frame.getTglBtnRedo().setEnabled(true);
		frame.repaint();
	}
	
	private void executeUndo() {
		redoCommandsStack.push(undoCommandsStack.peek());
		undoCommandsStack.pop().unexecute();
		textRedoCommandsStack.push(textUndoCommandsStack.peek());
		String[] textToLog = textUndoCommandsStack.pop().split(" ");
		frame.appendToLogPanel("Unexecuted " + textToLog[1]);
	}
	
	public void redo() {
		if (!(redoCommandsStack.peek() instanceof CmdRemove))
			executeRedo();
		else {
			frame.setAllShapeManipultationButtonsState(false);
			while (redoCommandsStack.peek() instanceof CmdRemove) {
				executeRedo();
				if(redoCommandsStack.isEmpty())
					break;
			}
		}
		if(redoCommandsStack.isEmpty())
			frame.getTglBtnRedo().setEnabled(false);
		if(!undoCommandsStack.isEmpty())
			frame.getTglBtnUndo().setEnabled(true);
		frame.repaint();
	}
	
	private void executeRedo() {
		undoCommandsStack.push(redoCommandsStack.peek());
		redoCommandsStack.pop().execute();
		textUndoCommandsStack.push(textRedoCommandsStack.peek());
		String[] textToLog = textRedoCommandsStack.pop().split(" ");
		frame.appendToLogPanel("Re-executed " + textToLog[1]);
	}
	    
	public void chooseEdgeColor() {
    	Color chosenEdgeColor = JColorChooser.showDialog(null, "Choose the edge color", currentEdgeColor);
    	if (chosenEdgeColor != null)
    		currentEdgeColor = chosenEdgeColor;
    }

    public void chooseFillColor() {
    	Color chosenFillColor = JColorChooser.showDialog(null, "Choose the fill color", currentFillColor);
    	if (chosenFillColor != null)
    		currentFillColor = chosenFillColor;
    }
    
    public void newPainting() {
		int answer = JOptionPane.showConfirmDialog(null, "Current drawing will be lost, continue anyway?","Warning",JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION)
			clearCanvas();
	}
	
	public void save() {
		JFileChooser fileChooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir") + "/src/main/resources");
		fileChooser.setCurrentDirectory(workingDirectory);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		fileChooser.setFileFilter(new FileNameExtensionFilter("serialized file (.ser)", "ser"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		fileChooser.setDialogTitle("Save file");
        int returnState = fileChooser.showSaveDialog(null);
        if (returnState == JFileChooser.APPROVE_OPTION) {
        	AnyFile anyFile = null;
        	if(fileChooser.getFileFilter().getDescription().equals("serialized file (.ser)"))
        		anyFile = new SerializableFile(model,frame);
        	else
        		anyFile = new LogFile(model,frame);
        	FileManager fileManager = new FileManager(anyFile);
        	fileManager.saveFile(fileChooser.getSelectedFile());
        
        }
	}
	
	public void load() {
		JFileChooser fileChooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir") + "/src/main/resources");
		fileChooser.setCurrentDirectory(workingDirectory);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		fileChooser.setFileFilter(new FileNameExtensionFilter("serialized file (.ser)", "ser"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		fileChooser.setDialogTitle("Load file");
        int returnState = fileChooser.showOpenDialog(null);
        if (returnState == JFileChooser.APPROVE_OPTION) {
    		clearCanvas();
        	AnyFile anyFile = null;
        	if(fileChooser.getFileFilter().getDescription().equals("serialized file (.ser)"))
        		anyFile = new SerializableFile(model,frame);
        	else
        		anyFile = new LogFile(model,frame);
        	FileManager fileManager = new FileManager(anyFile);
        	fileManager.loadFile(fileChooser.getSelectedFile());
        	frame.repaint();
        
        }
	}
	
	private void addShape(Shape shape) {
		shape.addObserver(new ObserverForButtons(model, frame));
		CmdAdd cmdAdd = new CmdAdd(shape, model);
		executeAndLogCommand(cmdAdd, shape, null);
	}
	    
	private void updateShape(Shape originalState, Shape newState) {
		//shape is still selected after update
		newState.setSelected(true);
		CmdUpdate cmdUpdate = new CmdUpdate(originalState, newState);
		executeAndLogCommand(cmdUpdate, originalState, newState);
	}
	
	public void unselectAll() {
		CmdSelect command;
		for(int i = model.getShapes().size() - 1; i >= 0; i--) {
			if(model.get(i).isSelected()) {
				command = new CmdSelect(model.get(i), false);
				executeAndLogCommand(command, model.get(i), null);
			}
		}
	}
    
    public void executeAndLogCommand(Command command, Shape shape, Shape updatedShape) {
		if(updatedShape == null) {
			//execute command before logging so that the new shape state will be logged e.g. CmdSelect changes state from false to true  
			command.execute();
			frame.appendToLogPanel("Executed" + " " + command.getClass().getSimpleName() + "_" + shape.toString() + "\n");
			textUndoCommandsStack.push("Executed" + " " + command.getClass().getSimpleName() + "_" + shape.toString() + "\n");
		} else {
			frame.appendToLogPanel("Executed" + " " + command.getClass().getSimpleName() + "_" + shape.toString() + "_to_" + updatedShape.toString() + "\n");
			textUndoCommandsStack.push("Executed" + " " + command.getClass().getSimpleName() + "_" + shape.toString() + "_to_" + updatedShape.toString() + "\n");
			//execute command after logging, otherwise shape and updatedShape will be the same
			command.execute();
		}
		undoCommandsStack.push(command);
		frame.getTglBtnUndo().setEnabled(true);
		redoCommandsStack.clear();
		textRedoCommandsStack.clear();
		frame.getTglBtnRedo().setEnabled(false);
		frame.repaint();
	}
	
    private Shape findMostTopSelectedShape() {
		for(int i = model.getShapes().size() - 1; i >= 0; i--)
			if(model.get(i).isSelected())
				return model.get(i);
		return null;
	}
    
    private void clearCanvas() {
		undoCommandsStack.clear();
		redoCommandsStack.clear();
		frame.getTglBtnRedo().setEnabled(false);
		frame.getTglBtnUndo().setEnabled(false);
		textRedoCommandsStack.clear();
		textUndoCommandsStack.clear();
		model.getShapes().clear();
		flagForLine = true;
		frame.setAllShapeManipultationButtonsState(false);
		frame.getLogPanel().setText(null);
		frame.repaint();
	} 

	public Color getCurrentEdgeColor() {
		return currentEdgeColor;
	}

	public Color getCurrentFillColor() {
		return currentFillColor;
	}
	
	public void setFlagForLine(boolean flagForLine) {
		this.flagForLine = flagForLine;
	}
	
}
