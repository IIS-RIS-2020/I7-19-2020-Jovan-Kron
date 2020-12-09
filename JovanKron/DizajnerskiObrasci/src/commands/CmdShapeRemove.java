package commands;

import mvc.DrawingModel;
import geometry.Shape;

import java.io.Serializable;
import java.util.ArrayList;

public class CmdShapeRemove implements Command {
    private ArrayList<Shape> shapes;
    private DrawingModel model;

    public CmdShapeRemove(ArrayList<Shape> shapes, DrawingModel model) {
        this.shapes = (ArrayList<Shape>) shapes.clone();
        this.model = model;
    }

    @Override
    public void execute() {
    	model.getPropertyChangeSupport().firePropertyChange("sizeUpdate", model.getSelectedShapes().size(), 0);
        model.getAllCommands().append("Delete next " + shapes.size() + " shapes\n");
        model.getListModel().addElement("Delete next " + shapes.size() + " shapes\n");
        for (Shape shape : shapes) {
            shape.setPositionInArray(model.indexOf(shape));
            this.model.getSelectedShapes().remove(shape);
            this.model.remove(shape);
            this.model.getAllCommands().append("Remove " + shape.toString() + "\n");
            model.getListModel().addElement("Remove " + shape.toString() + "\n");
        }
        this.model.pushInUndoStack(this);
    }

    @Override
    public void unexecute() {
    	model.getPropertyChangeSupport().firePropertyChange("sizeUpdate", 0, shapes.size());
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (model.getSelectedShapes().indexOf(shape) == -1) {
                this.model.addOnIndex(shape.getPositionInArray(), shape);
                this.model.addSelected(shape);
            }
        }
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }
}
