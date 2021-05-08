package commands;

import mvc.DrawingModel;
import geometry.Shape;
import geometry.SurfaceShape;

import java.io.Serializable;

public class CmdAdd implements Command {
    private Shape shape;
    private DrawingModel model;

    public CmdAdd(Shape shape, DrawingModel model) {
        this.shape = shape;
        this.model = model;
    }

    @Override
    public void execute() {
    	shape.setEdgeColor(model.getEdgeColor());
        if (shape instanceof SurfaceShape) {
            ((SurfaceShape) shape).setFillColor(model.getFillColor());
        }
        model.add(shape);
        model.pushInUndoStack(this);
        model.getAllCommands().append("Add " + shape.toString() + "\n");
        model.getListModel().addElement("Add " + shape.toString() + "\n");
    }

    @Override
    public void unexecute() {
        model.remove(shape);
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
