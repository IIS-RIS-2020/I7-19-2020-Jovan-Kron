package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdBringToBack implements Command {
    private DrawingModel model;
    private Shape shape;
    private int index;

    public CmdBringToBack(Shape shape, int index) {
        this.shape = shape;
        this.index = index;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.remove(shape);
        model.addOnIndex(0, shape);
        model.pushInUndoStack(this);
        model.getAllCommands().append("BringToBack " + shape.toString() + "\n");
        model.getListModel().addElement("BringToBack " + shape.toString() + "\n");
    }

    @Override
    public void unexecute() {
        model.remove(shape);
        model.addOnIndex(index, shape);
    }
}
