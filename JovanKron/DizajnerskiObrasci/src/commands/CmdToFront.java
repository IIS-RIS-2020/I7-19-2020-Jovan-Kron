package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdToFront implements Command {
    private DrawingModel model;
    private Shape shape;
    private int index;

    public CmdToFront(Shape shape, int index) {
        this.shape = shape;
        this.index = index;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        Shape shapeToBack = model.get(index + 1);
        model.set(index + 1, shape);
        model.set(index, shapeToBack);
        model.pushInUndoStack(this);
        model.getAllCommands().append("ToFront " + shape.toString() + "\n");
        model.getListModel().addElement("ToFront " + shape.toString() + "\n");
    }

    @Override
    public void unexecute() {
        Shape shapeToBack = model.get(index);
        model.set(index, shape);
        model.set(index + 1, shapeToBack);
    }
}
