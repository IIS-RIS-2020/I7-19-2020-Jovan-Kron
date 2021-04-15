package commands;

import mvc.DrawingModel;
import geometry.Shape;

import java.util.ArrayList;

public class CmdDeselect implements Command {
    private DrawingModel model;
    private ArrayList<Shape> shapesForDeselect;

    public CmdDeselect(ArrayList<Shape> shapesForDeselect, DrawingModel model) {
        this.shapesForDeselect = (ArrayList<Shape>) shapesForDeselect.clone();
        this.model = model;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.getAllCommands().append("Deselect next " + shapesForDeselect.size() + " shapes\n");
        model.getListModel().addElement("Deselect next " + shapesForDeselect.size() + " shapes\n");
        model.getPropertyChangeSupport().firePropertyChange("sizeUpdate", model.getSelectedShapes().size(), model.getSelectedShapes().size() - shapesForDeselect.size());
        for (Shape shape : shapesForDeselect) {
            shape.setSelected(false);
            model.removeSelected(shape);
            model.getAllCommands().append("Unselect " + shape.toString() + "\n");
            model.getListModel().addElement("Unselect " + shape.toString() + "\n");
        }
        model.pushInUndoStack(this);
    }

    @Override
    public void unexecute() {
        model.getPropertyChangeSupport().firePropertyChange("sizeUpdate", model.getSelectedShapes().size(), model.getSelectedShapes().size() + shapesForDeselect.size());
        for (Shape shape : shapesForDeselect) {
            shape.setSelected(true);
            model.addSelected(shape);
        }
    }
}
