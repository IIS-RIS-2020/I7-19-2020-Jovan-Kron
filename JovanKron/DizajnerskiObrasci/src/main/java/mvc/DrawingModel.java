package mvc;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.DefaultListModel;

import commands.Command;
import geometry.Shape;;

public class DrawingModel {
	private List<Shape> shapes = new ArrayList<Shape>();
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();
	private StringBuilder allCommands = new StringBuilder();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private Color edgeColor = Color.BLACK;
    private Color fillColor = Color.YELLOW;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private DefaultListModel<String> listModel = new DefaultListModel<>();
	
	public List<Shape> getShapes() {
		return shapes;
	}

	public void add(Shape p) {
		shapes.add(p);
	}
	
	public void remove(Shape p) {
		shapes.remove(p);
	}
	
	public Shape get(int i) {
		return shapes.get(i);
	}
	
	public void pushInUndoStack(Command command) {
        undoStack.push(command);
    }
	
	public StringBuilder getAllCommands() {
        return allCommands;
    }
	
	public ArrayList<Shape> getSelectedShapes() {
        return selectedShapes;
    }
	
	public void removeSelected(Shape shape) {
        selectedShapes.remove(shape);
    }
	
	public void addSelected(Shape shape) {
        selectedShapes.add(shape);
    }
	
	public Color getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getFillColor() {
        return fillColor;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }
    
    public PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}
    
    public void addOnIndex(int index, Shape shape) { 
    	shapes.add(index, shape);
    }
    
    public void set(int index, Shape shape) { 
    	shapes.set(index, shape);
    }
    
    public int indexOf(Shape s) { 
    	return shapes.indexOf(s);
    }
    
    public Stack<Command> getUndoStack() {
		return undoStack;
	}
    
    public Stack<Command> getRedoStack() {
		return redoStack;
	}
    
    public DefaultListModel<String> getListModel() {
		return listModel;
	}
    public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}
}
