package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdSelect implements Command {
    private DrawingModel model;
    private Shape shape;

    public CmdSelect(Shape shape, DrawingModel model) {
        this.shape = shape;
        this.model = model;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        shape.setSelected(true);
        model.pushInUndoStack(this);
        model.getPropertyChangeSupport().firePropertyChange("sizeUpdate", model.getSelectedShapes().size(), model.getSelectedShapes().size() + 1);
        model.addSelected(shape);
        model.getAllCommands().append("Select " + shape.toString() + "\n");
        model.getListModel().addElement("Select " + shape.toString() + "\n");
    }

    @Override
    public void unexecute() {
        shape.setSelected(false);
        model.getPropertyChangeSupport().firePropertyChange("sizeUpdate", model.getSelectedShapes().size(), model.getSelectedShapes().size() - 1);
        model.removeSelected(shape);
    }
}
