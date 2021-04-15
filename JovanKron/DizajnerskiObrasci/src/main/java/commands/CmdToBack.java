package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdToBack implements Command {
    private DrawingModel model;
    private Shape shape;
    private int index;

    public CmdToBack(Shape shape, int index) {
        this.shape = shape;
        this.index = index;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        if (index > 0) {
            Shape shapeToFront = model.get(index - 1);
            model.set(index - 1, shape);
            model.set(index, shapeToFront);
            model.pushInUndoStack(this);
            model.getAllCommands().append("ToBack " + shape.toString() + "\n");
            model.getListModel().addElement("ToBack " + shape.toString() + "\n");
        }
    }

    @Override
    public void unexecute() throws Exception {
        Shape shapeToFront = model.get(index);
        model.set(index, shape);
        model.set(index - 1, shapeToFront);
    }
}
