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
        Point basePoint = new Point(click.getX(), click.getY());
        dialog.fillForAdd(basePoint, currentEdgeColor);
        dialog.setVisible(true);
        if(dialog.isConfirmed())
        	addShape(dialog.createBasePointFromInput());
	}
	
	public void addLineOnClick(MouseEvent click) {
        if (flagForLine) {
            startPoint = new Point(click.getX(), click.getY());
            flagForLine = false;
        } else {
        	DlgLine dialog = new DlgLine();
        	Point endPoint = new Point(click.getX(), click.getY());
        	dialog.fillForAdd(startPoint, endPoint, currentEdgeColor);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                addShape(dialog.createLineFromInput());
                flagForLine = true;
            }
        }
	}
	
	public void addRectangleOnClick(MouseEvent click) {
         DlgRectangle dialog = new DlgRectangle();
         showDialogForAddingSurfaceShape(dialog, click);
         if (dialog.isConfirmed())
            addShape(dialog.createRectangleFromInput());
	}
	
	public void addCircleOnClick(MouseEvent click) {
        DlgCircle dialog = new DlgCircle();
        showDialogForAddingSurfaceShape(dialog, click);      
        if(dialog.isConfirmed())
            addShape(dialog.createCircleFromInput());
	}
	
	public void addDonutOnClick(MouseEvent click) {
        DlgDonut dialog = new DlgDonut();
        showDialogForAddingSurfaceShape(dialog, click);
        if(dialog.isConfirmed())
            addShape(dialog.createDonutFromInput());
	}
	
	public void addHexagonOnClick(MouseEvent click) {
        DlgHexagon dialog = new DlgHexagon();
        showDialogForAddingSurfaceShape(dialog, click);
        if(dialog.isConfirmed())
            addShape(dialog.createHexagonAdapterFromInput());
	}
	
	private void showDialogForAddingSurfaceShape(DlgSurfaceShape dialog, MouseEvent click) {
		Point basePoint = new Point(click.getX(), click.getY());
        dialog.fillForAdd(basePoint, currentEdgeColor, currentFillColor);
        dialog.setVisible(true);
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
		CmdRemove cmdRemove = new CmdRemove(model);
		for (Shape shape : model.getShapes())
			if(shape.isSelected())
				cmdRemove.addShapeToRemove(shape);
		executeAndLogRemoveCmd(cmdRemove);
		frame.setAllShapeManipultationButtonsState(false);
    }
	
	public void executeAndLogRemoveCmd(CmdRemove cmdRemove) {
		String cmdRemoveClassName = cmdRemove.getClass().getSimpleName();
		int numberOfShapesToBeRemoved = cmdRemove.getShapesToBeRemoved().size();
		String textToLog = "Executed " + cmdRemoveClassName + "_for_" + numberOfShapesToBeRemoved + "_shape(s)" + "\n";
		frame.appendToLogPanel(textToLog);
		textUndoCommandsStack.push(textToLog);
		executeCommand(cmdRemove);
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
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.fillForModify(originalPoint, originalPoint.getEdgeColor());
		dlgPoint.setVisible(true);
		if(dlgPoint.isConfirmed())
			updateShape(originalShape, dlgPoint.createBasePointFromInput());
	}
	
	private void modifyLine(Shape originalShape) {
		Line originalLine = (Line) originalShape;
        DlgLine dlgLine = new DlgLine();
        dlgLine.fillForModify(originalLine);
        dlgLine.setVisible(true);
        if(dlgLine.isConfirmed())
        	updateShape(originalShape, dlgLine.createLineFromInput());
	}
	
	private void modifyRectangle(Shape originalShape) {
		Rectangle originalRectangle = (Rectangle) originalShape;
        DlgRectangle dlgRectangle = new DlgRectangle();
        dlgRectangle.fillForModify(originalRectangle);
        dlgRectangle.setVisible(true);
        if(dlgRectangle.isConfirmed())
        	updateShape(originalShape, dlgRectangle.createRectangleFromInput());
	}
	
	private void modifyDonut(Shape originalShape) {
		Donut originalDonut = (Donut) originalShape;
        DlgDonut dlgDonut = new DlgDonut();
        dlgDonut.fillForModify(originalDonut);
        dlgDonut.setVisible(true);
        if(dlgDonut.isConfirmed())
        	updateShape(originalShape, dlgDonut.createDonutFromInput());
	}
	
	private void modifyCircle(Shape originalShape) {
		Circle originalCircle = (Circle) originalShape;
        DlgCircle dlgCircle = new DlgCircle();
        dlgCircle.fillForModify(originalCircle);
        dlgCircle.setVisible(true);
        if(dlgCircle.isConfirmed())
        	updateShape(originalShape, dlgCircle.createCircleFromInput());
	}
	
	private void modifyHexagonAdapter(Shape originalShape) {
		HexagonAdapter originalHexagonAdapter = (HexagonAdapter) originalShape;
        DlgHexagon dlgHexagon = new DlgHexagon();
        dlgHexagon.fillForModify(originalHexagonAdapter);
        dlgHexagon.setVisible(true);
        if (dlgHexagon.isConfirmed())
        	updateShape(originalShape, dlgHexagon.createHexagonAdapterFromInput());
	}
	
	public void toBack() {
		Shape shapeToMove = findMostTopSelectedShape();
		int indexOfShapeToMove = findIndexOfMostTopSelectedShape();
		if(indexOfShapeToMove != 0)
			executeAndLogCommand(new CmdToBack(indexOfShapeToMove, model), shapeToMove, null);
		else
			JOptionPane.showMessageDialog(null, "Shape is already on the back");
	}
	
	public void toFront() {
		Shape shapeToMove = findMostTopSelectedShape();
		int indexOfShapeToMove = findIndexOfMostTopSelectedShape();
		if(indexOfShapeToMove != model.getShapes().size() - 1)
			executeAndLogCommand(new CmdToFront(indexOfShapeToMove, model), shapeToMove, null);
		else
			JOptionPane.showMessageDialog(null, "Shape is already on the front");
	}
	
	public void bringToBack() {
		Shape shapeToMove = findMostTopSelectedShape();
		int indexOfShapeToMove = findIndexOfMostTopSelectedShape();
		if(indexOfShapeToMove != 0)
			executeAndLogCommand(new CmdBringToBack(indexOfShapeToMove, model), shapeToMove, null);
		else
			JOptionPane.showMessageDialog(null, "Shape is already on the back");
	}
	
	public void bringToFront() {
		Shape shapeToMove = findMostTopSelectedShape();
		int indexOfShapeToMove = findIndexOfMostTopSelectedShape();
		if(indexOfShapeToMove != model.getShapes().size() - 1)
			executeAndLogCommand(new CmdBringToFront(indexOfShapeToMove, model), shapeToMove, null);
		else
			JOptionPane.showMessageDialog(null, "Shape is already on the front");
	}
	
	public void undo() {
		if (undoCommandsStack.peek() instanceof CmdRemove)
			realizeUndoForCmdRemove();
		else {
			executeUndo();
			logUndo();
		}
		if(undoCommandsStack.isEmpty())
			frame.getTglBtnUndo().setEnabled(false);
		if(!redoCommandsStack.isEmpty())
			frame.getTglBtnRedo().setEnabled(true);
		frame.repaint();
	}
	
	private void realizeUndoForCmdRemove() {		
		if(((CmdRemove) undoCommandsStack.peek()).getShapesToBeRemoved().size() == 1)
			frame.setAllShapeManipultationButtonsState(true);
		else {
			frame.setAllShapeManipultationButtonsState(false);
			frame.getTglBtnDelete().setEnabled(true);
		}
		executeUndo();
		logUndo();
	}
	
	private void executeUndo() {
		redoCommandsStack.push(undoCommandsStack.peek());
		undoCommandsStack.pop().unexecute();
	}
	
	private void logUndo() {
		textRedoCommandsStack.push(textUndoCommandsStack.peek());
		String[] textToLog = textUndoCommandsStack.pop().split(" ");
		frame.appendToLogPanel("Unexecuted " + textToLog[1]);
	}

	public void redo() {
		if (redoCommandsStack.peek() instanceof CmdRemove)
			realizeRedoForCmdRemove();
		else {
			executeRedo();
			logRedo();
		}
		if(redoCommandsStack.isEmpty())
			frame.getTglBtnRedo().setEnabled(false);
		if(!undoCommandsStack.isEmpty())
			frame.getTglBtnUndo().setEnabled(true);
		frame.repaint();
	}
	
	private void realizeRedoForCmdRemove() {
		executeRedo();
		logRedo();
		frame.setAllShapeManipultationButtonsState(false);
	}
	
	private void executeRedo() {
		undoCommandsStack.push(redoCommandsStack.peek());
		redoCommandsStack.pop().execute();
	}
	
	private void logRedo() {
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
    	String question = "Current drawing will be lost, continue anyway?";
		int answer = JOptionPane.showConfirmDialog(null, question, "Warning", JOptionPane.YES_NO_OPTION);
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
        	FileManager fileManager = initalizeFileManager(fileChooser);
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
        	FileManager fileManager = initalizeFileManager(fileChooser);
        	fileManager.loadFile(fileChooser.getSelectedFile());
        	frame.repaint();
        }
	}
	
	private FileManager initalizeFileManager(JFileChooser fileChooser) {
    	if(fileChooser.getFileFilter().getDescription().equals("serialized file (.ser)"))
    		return new FileManager(new SerializableFile(model,frame));
    	else
    		return new FileManager(new LogFile(model,frame));
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
		Shape shape;
		for(int i = model.getShapes().size() - 1; i >= 0; i--) {
			shape = model.get(i);
			if(shape.isSelected()) {
				command = new CmdSelect(shape, false);
				executeAndLogCommand(command, shape, null);
			}
		}
	}
    
    public void executeAndLogCommand(Command command, Shape shape, Shape updatedShape) {
    	String commandClassName = command.getClass().getSimpleName();
		if(updatedShape == null) {
			//execute command before logging so that the new shape state will be logged
			//e.g. CmdSelect changes state from false to true  
			executeCommand(command);
			String commonTextToLog = "Executed " + commandClassName + "_" + shape.toString();
			frame.appendToLogPanel(commonTextToLog + "\n");
			textUndoCommandsStack.push(commonTextToLog + "\n");
		} else {
			String commonTextToLog = "Executed " + commandClassName + "_" + shape.toString();
			frame.appendToLogPanel(commonTextToLog + "_to_" + updatedShape.toString() + "\n");
			textUndoCommandsStack.push(commonTextToLog + "_to_" + updatedShape.toString() + "\n");
			//execute command after logging, otherwise shape and updatedShape will be the same
			executeCommand(command);
		}
	}
    
    private void executeCommand(Command command) {
    	command.execute();
    	undoCommandsStack.push(command);
    	redoCommandsStack.clear();
		frame.getTglBtnUndo().setEnabled(true);
		frame.getTglBtnRedo().setEnabled(false);
		textRedoCommandsStack.clear();
		frame.repaint();
    }
	
    private Shape findMostTopSelectedShape() {
		for(int i = model.getShapes().size() - 1; i >= 0; i--)
			if(model.get(i).isSelected())
				return model.get(i);
		return null;
	}
    
    public int findIndexOfMostTopSelectedShape() {
    	for(int i = model.getShapes().size() - 1; i >= 0; i--)
			if(model.get(i).isSelected())
				return i;
		return -1;
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
