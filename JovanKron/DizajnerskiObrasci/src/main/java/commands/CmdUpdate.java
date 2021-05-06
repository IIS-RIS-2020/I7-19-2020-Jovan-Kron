package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdUpdate implements Command {
    private Shape oldState;
    private Shape newState;
    private Shape originalState;
    private DrawingModel model;

    public CmdUpdate(Shape originalState, Shape newState) {
        this.originalState = originalState;
        this.newState = newState;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        this.oldState = originalState.clone(this.oldState);
        this.originalState = newState.clone(this.originalState);
        model.pushInUndoStack(this);
        model.getAllCommands().append("Update " + oldState.toString() + " to " + newState.toString() + "\n");
        model.getListModel().addElement("Update " + oldState.toString() + " to " + newState.toString() + "\n");
    }

    @Override
    public void unexecute() {
        this.originalState = oldState.clone(this.originalState);
    }
}
