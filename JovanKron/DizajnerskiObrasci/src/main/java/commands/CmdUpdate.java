package commands;

import geometry.Shape;

public class CmdUpdate implements Command {
    private Shape oldState;
    private Shape newState;
    private Shape originalState;

    public CmdUpdate(Shape originalState, Shape newState) {
        this.originalState = originalState;
        this.newState = newState;
    }

    @Override
    public void execute() {
        this.oldState = originalState.clone(this.oldState);
        this.originalState = newState.clone(this.originalState);
    }

    @Override
    public void unexecute() {
        this.originalState = oldState.clone(this.originalState);
    }
}
