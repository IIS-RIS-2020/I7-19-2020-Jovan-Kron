package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.*;
import dialogs.*;
import geometry.*;
import hexagon.Hexagon;
import observer.ObserverForButtons;
import strategy.*;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Command cmd;
	private Point startPoint;
	private Shape tempShape;
	private Color currentEdgeColor = Color.BLACK;
	private Color currentFillColor = Color.YELLOW;
	private SaveManager saveManager;
    private OpenManager openManager;
    private Save shapesSave = new ShapesSave(); 
    private Open shapesOpen = new ShapesOpen();
    private ListIterator<Shape> it;
    private Save commandsSave = new CommandsSave();
    private Open commandsOpen = new CommandsOpen();
    private LogParser lp;
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
        	point.addObserver(new ObserverForButtons(model, frame));
        	addShape(point);
        }
	}
	
	public void addLineOnClick(MouseEvent click) {
        if (startPoint == null) {
            startPoint = new Point(click.getX(), click.getY());
        } else {
        	DlgLine dialog = new DlgLine();
        	dialog.fillForAdd(startPoint.getX(), startPoint.getY(), click.getX(), click.getY(), currentEdgeColor);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
            	Line line = new Line(startPoint, new Point(click.getX(), click.getY()), dialog.getEdgeColor());
                line.addObserver(new ObserverForButtons(model, frame));
                addShape(line);
            }
            startPoint = null;
        }
	}
	
	public void addRectangleOnClick(MouseEvent click) {
         DlgRectangle dialog = new DlgRectangle();
         dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor, currentFillColor);
         dialog.setVisible(true);

         if (dialog.isConfirmed()) {
         	Rectangle rectangle = new Rectangle(new Point(click.getX(), click.getY()), dialog.getRectangleWidth(), dialog.getRectangleHeight(), dialog.getEdgeColor(), dialog.getFillColor());
            rectangle.addObserver(new ObserverForButtons(model, frame));
            addShape(rectangle);
         }
	}
	
	public void addCircleOnClick(MouseEvent click) {
        DlgCircle dialog = new DlgCircle();
        dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor, currentFillColor);
        dialog.setVisible(true);        

        if(dialog.isConfirmed()) {
        	Circle circle = new Circle(new Point(click.getX(), click.getY()), dialog.getRadius(), dialog.getEdgeColor(), dialog.getFillColor());
            circle.addObserver(new ObserverForButtons(model, frame));
            addShape(circle);
        }
	}
	
	public void addDonutOnClick(MouseEvent click) {
        DlgDonut dialog = new DlgDonut();
        dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor, currentFillColor);
        dialog.setVisible(true);

        if(dialog.isConfirmed()) {
        	Donut donut = new Donut(new Point(click.getX(), click.getY()), dialog.getOuterRadius(), dialog.getInnerRadius(), dialog.getEdgeColor(), dialog.getFillColor());
            donut.addObserver(new ObserverForButtons(model, frame));
            addShape(donut);
        }
	}
	
	public void addHexagonOnClick(MouseEvent click) {
        DlgHexagon dialog = new DlgHexagon();
        dialog.fillForAdd(click.getX(), click.getY(), currentEdgeColor, currentFillColor);
        dialog.setVisible(true);
        
        if(dialog.isConfirmed()) {
        	HexagonAdapter hexagon = new HexagonAdapter(click.getX(), click.getY(), dialog.getRadius(), dialog.getEdgeColor(), dialog.getFillColor());
            hexagon.addObserver(new ObserverForButtons(model, frame));
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
            Point oldState = (Point) originalShape;

            DlgPoint mp = new DlgPoint();
            mp.fillForModify(oldState.getX(), oldState.getY(), oldState.getEdgeColor());
            mp.setVisible(true);

            if(mp.isConfirmed())
            	updatedShape = new Point(mp.getBaseCoordinateX(), mp.getBaseCoordinateY(), mp.getEdgeColor());
            	
        } else if(originalShape instanceof Line) {
            Line oldState = (Line) originalShape;

            DlgLine ml = new DlgLine();
            ml.fillForModify(oldState.getStartPoint().getX(), oldState.getStartPoint().getY(),
            		oldState.getEndPoint().getX(), oldState.getEndPoint().getY(), oldState.getEdgeColor());
            ml.setVisible(true);

            if(ml.isConfirmed())
            	updatedShape = new Line(new Point(ml.getBaseCoordinateX(), ml.getBaseCoordinateY()), new Point(ml.getEndX(), ml.getEndY()), ml.getEdgeColor());
            
        } else if(originalShape instanceof Rectangle) {
            Rectangle oldState = (Rectangle) originalShape;

            DlgRectangle dr = new DlgRectangle();
            dr.fillForModify(oldState.getUpperLeftPoint().getX(), oldState.getUpperLeftPoint().getY(), oldState.getWidth(), oldState.getHeight(), oldState.getEdgeColor(), oldState.getFillColor());
            dr.setVisible(true);

            if(dr.isConfirmed())
            	updatedShape = new Rectangle(new Point(dr.getBaseCoordinateX(), dr.getBaseCoordinateY()), dr.getRectangleWidth(), dr.getRectangleHeight(), dr.getEdgeColor(), dr.getFillColor());
        
        } else if(originalShape instanceof Donut) {
            Donut oldState = (Donut) originalShape;

            DlgDonut dd = new DlgDonut();
            dd.fillForModify(oldState.getCenter().getX(), oldState.getCenter().getY(), oldState.getInnerRadius(), oldState.getRadius(), oldState.getEdgeColor(), oldState.getFillColor());
            dd.setVisible(true);

            if(dd.isConfirmed()) {
            	updatedShape = new Donut(new Point(dd.getBaseCoordinateX(), dd.getBaseCoordinateY()), dd.getOuterRadius(), dd.getInnerRadius(), dd.getEdgeColor(), dd.getFillColor());
            }
            
        } else if(originalShape instanceof Circle) {
            Circle oldState = (Circle) originalShape;

            DlgCircle dc = new DlgCircle();
            dc.fillForModify(oldState.getCenter().getX(), oldState.getCenter().getY(), oldState.getRadius(), oldState.getEdgeColor(), oldState.getFillColor());
            dc.setVisible(true);

            if(dc.isConfirmed())
            	updatedShape = new Circle(new Point(dc.getBaseCoordinateX(), dc.getBaseCoordinateY()), dc.getRadius(), dc.getEdgeColor(), dc.getFillColor());

        } else if(originalShape instanceof HexagonAdapter) {
            HexagonAdapter oldState = (HexagonAdapter) originalShape;

            DlgHexagon dh = new DlgHexagon();
            dh.fillForModify(oldState.getX(), oldState.getY(), oldState.getR(), oldState.getEdgeColor(), oldState.getFillColor());
            dh.setVisible(true);

            if (dh.isConfirmed())
            	updatedShape = new HexagonAdapter(dh.getBaseCoordinateX(), dh.getBaseCoordinateY(), dh.getRadius(), dh.getEdgeColor(), dh.getFillColor());
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
    /*
    public void onSaveShapes() throws IOException {
        saveManager = new SaveManager(shapesSave);
        saveManager.save(model.getShapes());
    }
    
    public void onOpenShapes() throws IOException, ClassNotFoundException {
        openManager = new OpenManager(shapesOpen);
        it = ((ArrayList) openManager.open()).listIterator();
        while (it.hasNext()) {
            Shape s = it.next();
            model.add(s);
        }
        frame.repaint();
    }
    
    public void next() throws Exception {
        lp.read();
        frame.repaint();
    }

    public void onSaveCommands() throws IOException {
        saveManager = new SaveManager(commandsSave);
        saveManager.save(model.getAllCommands());
    }
    
    public void onOpenCommands() throws IOException, ClassNotFoundException {
        openManager = new OpenManager(commandsOpen);
        Scanner myReader = (Scanner)openManager.open();
        lp = new LogParser(myReader);
        lp.setModel(model);
    }*/
    
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

	public void setCurrentEdgeColor(Color currentEdgeColor) {
		this.currentEdgeColor = currentEdgeColor;
	}

	public void setCurrentFillColor(Color currentFillColor) {
		this.currentFillColor = currentFillColor;
	}
}
