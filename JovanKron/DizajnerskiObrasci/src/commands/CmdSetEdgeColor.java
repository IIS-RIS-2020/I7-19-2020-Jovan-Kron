package commands;

import mvc.DrawingModel;

import java.awt.*;

public class CmdSetEdgeColor implements Command {
    private DrawingModel model;
    private Color newEdgeColor;
    private Color oldEdgeColor;
    private Color originalEdgeColor;


    public CmdSetEdgeColor(Color oldEdgeColor, Color newEdgeColor) {
        this.newEdgeColor = newEdgeColor;
        this.oldEdgeColor = oldEdgeColor;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
    	this.model.getPropertyChangeSupport().firePropertyChange("EdgeColor", oldEdgeColor, newEdgeColor);
        this.originalEdgeColor = oldEdgeColor;
        this.oldEdgeColor = newEdgeColor;
        this.model.setEdgeColor(oldEdgeColor);
        this.model.pushInUndoStack(this);
        this.model.getAllCommands().append("Set Edge Color: " +
                oldEdgeColor.getRed() + " , " + oldEdgeColor.getGreen() + " , " + oldEdgeColor.getBlue() + "\n");
        model.getListModel().addElement("Set Edge Color: " +
                oldEdgeColor.getRed() + " , " + oldEdgeColor.getGreen() + " , " + oldEdgeColor.getBlue() + "\n");
    }

    @Override
    public void unexecute() {
        this.model.getPropertyChangeSupport().firePropertyChange("EdgeColor", oldEdgeColor, originalEdgeColor);
        this.oldEdgeColor = originalEdgeColor;
        this.model.setEdgeColor(oldEdgeColor);
    }
}
