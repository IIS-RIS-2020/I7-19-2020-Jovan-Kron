package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
		for(int i = model.getShapes().size()-1; i>=0; i--){
			shape = model.get(i);
			if(shape.contains(click.getX(), click.getY())) {
				if(shape.isSelected())
					cmdSelect = new CmdSelect(shape, false);
				else
					cmdSelect = new CmdSelect(shape, true);
				break;
			}
		}
		if(cmdSelect != null)
			logCommand(cmdSelect, shape, null);
	}
	
	public void deleteShapes() {
		Shape shapeToDelete;
		Command command;
		for(int i = model.getShapes().size()-1; i>=0; i--) {
			shapeToDelete = model.get(i);
			if(shapeToDelete.isSelected()) {
				command = new CmdRemove(shapeToDelete, model);
				logCommand(command, shapeToDelete, null);
			}
		}
		disableButtons();
    }
	
	public void modifyShape() {
		Shape originalShape = findMostTopSelectedShape();
		Shape updatedShape = null;

        if(originalShape instanceof Point) {
            Point originalPoint = (Point) originalShape;

            DlgPoint dlgPoint = new DlgPoint();
            dlgPoint.fillForModify(originalPoint.getX(), originalPoint.getY(), originalPoint.getEdgeColor());
            dlgPoint.setVisible(true);

            if(dlgPoint.isConfirmed())
            	updatedShape = new Point(dlgPoint.getBaseCoordinateX(), dlgPoint.getBaseCoordinateY(), dlgPoint.getEdgeColor());
            	
        } else if(originalShape instanceof Line) {
            Line originalLine = (Line) originalShape;

            DlgLine dlgLine = new DlgLine();
            dlgLine.fillForModify(originalLine.getStartPoint().getX(), originalLine.getStartPoint().getY(),
            		originalLine.getEndPoint().getX(), originalLine.getEndPoint().getY(), originalLine.getEdgeColor());
            dlgLine.setVisible(true);

            if(dlgLine.isConfirmed())
            	updatedShape = new Line(new Point(dlgLine.getBaseCoordinateX(), dlgLine.getBaseCoordinateY()), new Point(dlgLine.getEndX(), dlgLine.getEndY()), dlgLine.getEdgeColor());
            
        } else if(originalShape instanceof Rectangle) {
            Rectangle originalRectangle = (Rectangle) originalShape;

            DlgRectangle dlgRectangle = new DlgRectangle();
            dlgRectangle.fillForModify(originalRectangle.getUpperLeftPoint().getX(), originalRectangle.getUpperLeftPoint().getY(), originalRectangle.getWidth(), originalRectangle.getHeight(), originalRectangle.getEdgeColor(), originalRectangle.getFillColor());
            dlgRectangle.setVisible(true);

            if(dlgRectangle.isConfirmed())
            	updatedShape = new Rectangle(new Point(dlgRectangle.getBaseCoordinateX(), dlgRectangle.getBaseCoordinateY()), dlgRectangle.getRectangleWidth(), dlgRectangle.getRectangleHeight(), dlgRectangle.getEdgeColor(), dlgRectangle.getFillColor());
        
        } else if(originalShape instanceof Donut) {
            Donut originalDonut = (Donut) originalShape;

            DlgDonut dlgDonut = new DlgDonut();
            dlgDonut.fillForModify(originalDonut.getCenter().getX(), originalDonut.getCenter().getY(), originalDonut.getInnerRadius(), originalDonut.getRadius(), originalDonut.getEdgeColor(), originalDonut.getFillColor());
            dlgDonut.setVisible(true);

            if(dlgDonut.isConfirmed()) {
            	updatedShape = new Donut(new Point(dlgDonut.getBaseCoordinateX(), dlgDonut.getBaseCoordinateY()), dlgDonut.getOuterRadius(), dlgDonut.getInnerRadius(), dlgDonut.getEdgeColor(), dlgDonut.getFillColor());
            }
            
        } else if(originalShape instanceof Circle) {
            Circle originalCircle = (Circle) originalShape;

            DlgCircle dlgCircle = new DlgCircle();
            dlgCircle.fillForModify(originalCircle.getCenter().getX(), originalCircle.getCenter().getY(), originalCircle.getRadius(), originalCircle.getEdgeColor(), originalCircle.getFillColor());
            dlgCircle.setVisible(true);

            if(dlgCircle.isConfirmed())
            	updatedShape = new Circle(new Point(dlgCircle.getBaseCoordinateX(), dlgCircle.getBaseCoordinateY()), dlgCircle.getRadius(), dlgCircle.getEdgeColor(), dlgCircle.getFillColor());

        } else if(originalShape instanceof HexagonAdapter) {
            HexagonAdapter originalHexagonAdapter = (HexagonAdapter) originalShape;

            DlgHexagon dlgHexagon = new DlgHexagon();
            dlgHexagon.fillForModify(originalHexagonAdapter.getX(), originalHexagonAdapter.getY(), originalHexagonAdapter.getR(), originalHexagonAdapter.getEdgeColor(), originalHexagonAdapter.getFillColor());
            dlgHexagon.setVisible(true);

            if (dlgHexagon.isConfirmed())
            	updatedShape = new HexagonAdapter(dlgHexagon.getBaseCoordinateX(), dlgHexagon.getBaseCoordinateY(), dlgHexagon.getRadius(), dlgHexagon.getEdgeColor(), dlgHexagon.getFillColor());
        }
        
        if(originalShape != null && updatedShape != null)
        	updateShape(originalShape, updatedShape);
	}
	
	
	public void positionCommand(String typeOfAction) {
		Shape shapeToMove = findMostTopSelectedShape();
		Command command = null;
		
		if(typeOfAction.equals("BTB")) {
			if(model.getShapes().indexOf(shapeToMove) != 0)
				command = new CmdBringToBack(shapeToMove, model);
			else
				JOptionPane.showMessageDialog(null, "Shape is already on the back");
			
		} else if(typeOfAction.equals("TB")) {
			if(model.getShapes().indexOf(shapeToMove) != 0)
				command = new CmdToBack(shapeToMove, model);
			else
				JOptionPane.showMessageDialog(null, "Shape is already on the back");
			
		} else if(typeOfAction.equals("TF")) {
			if(model.getShapes().indexOf(shapeToMove) != model.getShapes().size()-1)
				command = new CmdToFront(shapeToMove, model);
			else
				JOptionPane.showMessageDialog(null, "Shape is already on the front");
			
		} else if(typeOfAction.equals("BTF")) {
			if(model.getShapes().indexOf(shapeToMove) != model.getShapes().size()-1)
				command = new CmdBringToFront(shapeToMove, model);
			else
				JOptionPane.showMessageDialog(null, "Shape is already on the front");
		}
		
		if(command != null) 
			logCommand(command, shapeToMove, null);
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
			if(deletedShapesToUndoCount == 1) {
				frame.getTglBtnModify().setEnabled(true);
				frame.getTglBtnDelete().setEnabled(true);
				frame.getTglBtnBringToBack().setEnabled(true);
				frame.getTglBtnBringToFront().setEnabled(true);
				frame.getTglBtnToBack().setEnabled(true);
				frame.getTglBtnToFront().setEnabled(true);
			} else {
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
			disableButtons();
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
	
	public void savePainting() {
		JFileChooser fileChooser = new JFileChooser();
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
	
	public void loadPainting() {
		JFileChooser fileChooser = new JFileChooser();
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
    
    public void logCommand(Command command, Shape shape, Shape updatedShape) {
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
    
    public void addShape(Shape shape) {
    	shape.addObserver(new ObserverForButtons(model, frame));
		CmdAdd cmdAdd = new CmdAdd(shape, model);
		logCommand(cmdAdd, shape, null);
	}
    
	public void updateShape(Shape originalState, Shape newState) {
		//shape is still selected after update
		newState.setSelected(true);
		CmdUpdate cmdUpdate = new CmdUpdate(originalState, newState);
		logCommand(cmdUpdate, originalState, newState);
	}
	
	public Shape findMostTopSelectedShape() {
		for(int i = model.getShapes().size()-1; i>=0; i--)
			if(model.get(i).isSelected())
				return model.get(i);
		return null;
	}
    
    public void disableButtons() {
		frame.getTglBtnDelete().setEnabled(false);
		frame.getTglBtnModify().setEnabled(false);
		frame.getTglBtnBringToBack().setEnabled(false);
		frame.getTglBtnToBack().setEnabled(false);
		frame.getTglBtnToFront().setEnabled(false);
		frame.getTglBtnBringToFront().setEnabled(false);
	}
    
    public void clearCanvas() {
		undoCommandsStack.clear();
		redoCommandsStack.clear();
		frame.tglBtnRedo.setEnabled(false);
		frame.tglBtnUndo.setEnabled(false);
		textRedoCommandsStack.clear();
		textUndoCommandsStack.clear();
		model.getShapes().clear();
		flagForLine = true;
		disableButtons();
		frame.getLogPanel().setText(null);
		frame.repaint();
	}
    
    public void unselectAll() {
		CmdSelect command;
		for(int i = model.getShapes().size()-1; i>=0; i--) {
			if(model.get(i).isSelected()) {
				command = new CmdSelect(model.get(i), false);
				logCommand(command, model.get(i), null);
			}
		}
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
