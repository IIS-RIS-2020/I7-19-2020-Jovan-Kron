package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdBringToFront implements Command {
    private Shape shape;
    private int index;
    private DrawingModel model;

    public CmdBringToFront(Shape shape, int index) {
        this.shape = shape;
        this.index = index;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.add(shape);
        model.remove(shape);
        model.pushInUndoStack(this);
        model.getAllCommands().append("BringToFront " + shape.toString() + "\n");
        model.getListModel().addElement("BringToFront " + shape.toString() + "\n");
    }

    @Override
    public void unexecute() {
        model.remove(shape);
        model.addOnIndex(index, shape);
    }
}
