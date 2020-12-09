package commands;

import mvc.DrawingModel;

public interface Command {
    void setModel(DrawingModel model);
    void execute() throws Exception;
    void unexecute() throws Exception;
}
