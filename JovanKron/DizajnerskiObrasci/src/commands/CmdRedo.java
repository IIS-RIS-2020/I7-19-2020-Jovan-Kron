package commands;

import mvc.DrawingModel;

import java.io.Serializable;

import javax.swing.DefaultListModel;

public class CmdRedo implements Command {
    private DrawingModel model;

    public CmdRedo(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() throws Exception {
        StringBuilder tempRedo = new StringBuilder();
        DefaultListModel<String> dlm = new DefaultListModel<>();
	    for (int i = 0; i < model.getListModel().size(); i++) {
	    	dlm.addElement(model.getListModel().getElementAt(i));
	    }
        tempRedo.append(model.getAllCommands().toString());
        model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
        model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), model.getRedoStack().size() - 1);
        model.getRedoStack().peek().execute();
        model.getUndoStack().pop();
        model.getUndoStack().push(model.getRedoStack().pop());
        model.getAllCommands().setLength(0);
        model.getAllCommands().append(tempRedo.toString());
        model.getListModel().clear();
        for (int i = 0; i < dlm.size(); i++) {
	    	model.getListModel().addElement(dlm.elementAt(i));
	    }
        model.getAllCommands().append("Redo\n");
        model.getListModel().addElement("Redo\n");
    }

    @Override
    public void unexecute() throws Exception {
    	model.getUndoStack().peek().unexecute();
        model.getRedoStack().push(model.getUndoStack().pop());
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }
}
