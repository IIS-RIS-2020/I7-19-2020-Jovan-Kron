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
		Point point = new Point(click.getX(), click.getY());

        DlgPoint dialog = new DlgPoint();
        dialog.setTxtX(Integer.toString(point.getX()));
        dialog.setTxtY(Integer.toString(point.getY()));
        dialog.setTxtXEdit(false);
        dialog.setTxtYEdit(false);
        dialog.setVisible(true);

        if(dialog.isOk()) {
        	point.setEdgeColor(currentEdgeColor);
        	point.addObserver(new ObserverForButtons(model, frame));
        	addShape(point);
        }
	}
	
	public void addLineOnClick(MouseEvent click) {
		DlgLine dialog = new DlgLine();

        Point p = new Point(click.getX(), click.getY());

        if (startPoint == null) {
            startPoint = new Point(p.getX(), p.getY());
        } else {
            dialog.setTxtStartX(Integer.toString(startPoint.getX()));
            dialog.setTxtStartY(Integer.toString(startPoint.getY()));
            dialog.setTxtStartXEdit(false);
            dialog.setTxtStartYEdit(false);

            dialog.setTxtEndX(Integer.toString(p.getX()));
            dialog.setTxtEndY(Integer.toString(p.getY()));
            dialog.setTxtEndXEdit(false);
            dialog.setTxtEndYEdit(false);
            dialog.setVisible(true);

            if (dialog.isOk()) {
            	Line line = new Line(startPoint, new Point(click.getX(), click.getY()));
                line.setEdgeColor(currentEdgeColor);
                line.addObserver(new ObserverForButtons(model, frame));
                
                addShape(line);
            }
            startPoint = null;
        }
	}
	
	public void addRectangleOnClick(MouseEvent click) {
		 Point p = new Point(click.getX(), click.getY());

         DlgRectangle dialog = new DlgRectangle();
         dialog.setTxtX(Integer.toString(p.getX()));
         dialog.setTxtY(Integer.toString(p.getY()));
         dialog.setTxtXEdit(false);
         dialog.setTxtYEdit(false);
         dialog.setVisible(true);

         int height = Integer.parseInt(dialog.getTxtHeight().getText());
         int width = Integer.parseInt(dialog.getTxtWidth().getText());

         if (dialog.isOk()) {
         	Rectangle rectangle = new Rectangle(p,width,height);
              
             rectangle.setEdgeColor(currentEdgeColor);
             rectangle.setFillColor(currentFillColor);
             rectangle.addObserver(new ObserverForButtons(model, frame));
             
             addShape(rectangle);
         }
	}
	
	public void addCircleOnClick(MouseEvent click) {
		Point p = new Point(click.getX(), click.getY());

        DlgCircle dialog = new DlgCircle();
        dialog.setTxtX(Integer.toString(p.getX()));
        dialog.setTxtY(Integer.toString(p.getY()));
        dialog.setTxtXEdit(false);
        dialog.setTxtYEdit(false);
        dialog.setVisible(true);

        int radius = Integer.parseInt(dialog.getTxtRadius());
        

        if(dialog.isOk()) {
        	Circle circle = new Circle(p,radius);
            
            circle.setEdgeColor(currentEdgeColor);
            circle.setFillColor(currentFillColor);
            circle.addObserver(new ObserverForButtons(model, frame));
            
            addShape(circle);
        }
	}
	
	public void addDonutOnClick(MouseEvent click) {
		Point p = new Point(click.getX(), click.getY());

        DlgDonut dialog = new DlgDonut();
        dialog.setTxtX(Integer.toString(p.getX()));
        dialog.setTxtY(Integer.toString(p.getY()));
        dialog.setTxtXEdit(false);
        dialog.setTxtYEdit(false);
        dialog.setVisible(true);

        int innerRadius = Integer.parseInt(dialog.getTxtInner());
        int outerRadius = Integer.parseInt(dialog.getTxtOuter());

        if(dialog.isOk()) {
        	Donut donut = new Donut(p,outerRadius,innerRadius);
            
            donut.setEdgeColor(currentEdgeColor);
            donut.setFillColor(currentFillColor);
            donut.addObserver(new ObserverForButtons(model, frame));
            
            addShape(donut);
        }
	}
	
	public void addHexagonOnClick(MouseEvent click) {
		Point p = new Point(click.getX(), click.getY());

        DlgHexagon dialog = new DlgHexagon();
        dialog.setTxtX(Integer.toString(p.getX()));
        dialog.setTxtY(Integer.toString(p.getY()));
        dialog.setTxtXEdit(false);
        dialog.setTxtYEdit(false);
        dialog.setVisible(true);
        int radius = Integer.parseInt(dialog.getTxtRadius());
        
        if(dialog.isOk()) {
        	HexagonAdapter hexagon = new HexagonAdapter(p.getX(), p.getY(), radius);
            
            hexagon.setEdgeColor(currentEdgeColor);
            hexagon.setFillColor(currentFillColor);
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
				if(shape.isSelected()) {
					cmdSelect = new CmdSelect(shape, false);
				}
				else {
					cmdSelect = new CmdSelect(shape, true);
				}
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
            mp.setTxtX(Integer.toString(oldState.getX()));
            mp.setTxtY(Integer.toString(oldState.getY()));
            mp.setVisible(true);

            if(mp.isOk())
            	updatedShape = new Point(Integer.parseInt(mp.getTxtX()), Integer.parseInt(mp.getTxtY()), currentEdgeColor);
            	
        } else if(originalShape instanceof Line) {
            Line oldState = (Line) originalShape;

            DlgLine ml = new DlgLine();
            ml.setTxtStartXEdit(true);
            ml.setTxtStartYEdit(true);
            ml.setTxtEndXEdit(true);
            ml.setTxtEndYEdit(true);
            ml.setTxtStartX(Integer.toString(oldState.getStartPoint().getX()));
            ml.setTxtStartY(Integer.toString(oldState.getStartPoint().getY()));
            ml.setTxtEndX(Integer.toString(oldState.getEndPoint().getX()));
            ml.setTxtEndY(Integer.toString(oldState.getEndPoint().getY()));
            ml.setColor(oldState.getEdgeColor());
            ml.setVisible(true);

            if(ml.isOk()) {
            	Point newStartPoint = new Point(Integer.parseInt(ml.getTxtStartX()), Integer.parseInt(ml.getTxtStartY()));
                Point newEndPoint = new Point(Integer.parseInt(ml.getTxtEndX()), Integer.parseInt(ml.getTxtEndY()));
                updatedShape = new Line(newStartPoint, newEndPoint, currentEdgeColor);
            }
        } else if(originalShape instanceof Rectangle) {
            Rectangle oldState = (Rectangle) originalShape;

            DlgRectangle dr = new DlgRectangle();
            dr.setTxtXEdit(true);
            dr.setTxtYEdit(true);
            dr.setTxtX(Integer.toString(oldState.getUpperLeftPoint().getX()));
            dr.setTxtY(Integer.toString(oldState.getUpperLeftPoint().getY()));
            dr.setTxtWidth(Integer.toString(oldState.getWidth()));
            dr.setTxtHeight(Integer.toString(oldState.getHeight()));
            dr.setEdgeColor(oldState.getEdgeColor());
            dr.setFillColor(oldState.getFillColor());
            dr.setVisible(true);

            if(dr.isOk())
            	updatedShape = new Rectangle(new Point(Integer.parseInt(dr.getTxtX().getText()), Integer.parseInt(dr.getTxtY().getText())), Integer.parseInt(dr.getTxtWidth().getText()), Integer.parseInt(dr.getTxtHeight().getText()), currentEdgeColor, currentFillColor);
        
        } else if(originalShape instanceof Donut) {
            Donut oldState = (Donut) originalShape;

            DlgDonut dd = new DlgDonut();
            dd.setTxtXEdit(true);
            dd.setTxtYEdit(true);
            dd.setTxtX(Integer.toString(oldState.getCenter().getX()));
            dd.setTxtY(Integer.toString(oldState.getCenter().getY()));
            dd.setTxtInner(Integer.toString(oldState.getInnerRadius()));
            dd.setTxtOuter(Integer.toString(oldState.getRadius()));
            dd.setEdgeColor(oldState.getEdgeColor());
            dd.setFillColor(oldState.getFillColor());
            dd.setVisible(true);

            if(dd.isOk()) {
            	updatedShape = new Donut(
                        new Point(Integer.parseInt(dd.getTxtX()),Integer.parseInt(dd.getTxtY())),
                        Integer.parseInt(dd.getTxtOuter()),
                        Integer.parseInt(dd.getTxtInner()),
                        currentEdgeColor,
                        currentFillColor
                );
            }
            
        } else if(originalShape instanceof Circle) {
            Circle oldState = (Circle) originalShape;

            DlgCircle dc = new DlgCircle();
            dc.setTxtXEdit(true);
            dc.setTxtYEdit(true);
            dc.setTxtX(Integer.toString(oldState.getCenter().getX()));
            dc.setTxtY(Integer.toString(oldState.getCenter().getY()));
            dc.setTxtRadius(Integer.toString(oldState.getRadius()));
            dc.setEdgeColor(oldState.getEdgeColor());
            dc.setFillColor(oldState.getFillColor());
            dc.setVisible(true);

            if(dc.isOk())
            	updatedShape = new Circle(new Point(Integer.parseInt(dc.getTxtX()),Integer.parseInt(dc.getTxtY())), Integer.parseInt(dc.getTxtRadius()), currentEdgeColor, currentFillColor);

        } else if(originalShape instanceof HexagonAdapter) {
            HexagonAdapter oldState = (HexagonAdapter) originalShape;

            DlgHexagon dh = new DlgHexagon();
            dh.setTxtXEdit(true);
            dh.setTxtYEdit(true);
            dh.setTxtX(Integer.toString(oldState.getX()));
            dh.setTxtY(Integer.toString(oldState.getY()));
            dh.setTxtRadius(Integer.toString(oldState.getR()));
            dh.setEdgeColor(oldState.getEdgeColor());
            dh.setFillColor(oldState.getFillColor());
            dh.setVisible(true);

            if (dh.isOk()) {
            	HexagonAdapter newState = new HexagonAdapter(Integer.parseInt(dh.getTxtX()),Integer.parseInt(dh.getTxtY()), Integer.parseInt(dh.getTxtRadius()));
                newState.setEdgeColor(currentEdgeColor);
                newState.setFillColor(currentFillColor);
                updatedShape = newState;
            }
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
