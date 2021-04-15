package commands;

import mvc.DrawingModel;

import java.awt.*;

public class CmdSetFillColor implements Command {
    private DrawingModel model;
    private Color newFillColor;
    private Color oldFillColor;
    private Color originalFillColor;

    public CmdSetFillColor (Color oldFillColor, Color newFillColor) {
        this.newFillColor = newFillColor;
        this.oldFillColor = oldFillColor;
    }

    @Override
    public void setModel(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
    	this.model.getPropertyChangeSupport().firePropertyChange("FillColor", oldFillColor, newFillColor);
        this.originalFillColor = this.oldFillColor;
        this.oldFillColor = newFillColor;
        this.model.setFillColor(oldFillColor);
        model.pushInUndoStack(this);
        this.model.getAllCommands().append("Set Fill Color: " +
                oldFillColor.getRed() + " , " + oldFillColor.getGreen() + " , " + oldFillColor.getBlue() + "\n");
        model.getListModel().addElement("Set Fill Color: " +
                oldFillColor.getRed() + " , " + oldFillColor.getGreen() + " , " + oldFillColor.getBlue() + "\n");
    }

    @Override
    public void unexecute() {
    	this.model.getPropertyChangeSupport().firePropertyChange("FillColor", oldFillColor, originalFillColor);
        this.oldFillColor = this.originalFillColor;
        this.model.setFillColor(oldFillColor);
    }
}
