package commands;

import mvc.DrawingModel;

import java.io.Serializable;

public class CmdUndo implements Command {
    private DrawingModel model;

    public CmdUndo(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() throws Exception {
    	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() - 1);
        model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), model.getRedoStack().size() + 1);
        model.getUndoStack().peek().unexecute();
        model.getRedoStack().push(model.getUndoStack().pop());
        model.getAllCommands().append("Undo\n");
        model.getListModel().addElement("Undo\n");
    }

    @Override
    public void unexecute() throws Exception {
    	model.getRedoStack().peek().execute();
        model.getUndoStack().pop();
        model.getUndoStack().push(model.getRedoStack().pop());
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }
}
