package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdUpdate implements Command {
    private Shape oldState;
    private Shape newState;
    private Shape original;
    private DrawingModel model;

    public CmdUpdate(Shape oldState, Shape newState) {
        this.oldState = oldState;
        this.newState = newState;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        this.original = oldState.clone(this.original);
        this.oldState = newState.clone(this.oldState);
        model.pushInUndoStack(this);
        model.getAllCommands().append("Update " + original.toString() + " to " + oldState.toString() + "\n");
        model.getListModel().addElement("Update " + original.toString() + " to " + oldState.toString() + "\n");
    }

    @Override
    public void unexecute() {
        this.oldState = original.clone(this.oldState);
    }
}
