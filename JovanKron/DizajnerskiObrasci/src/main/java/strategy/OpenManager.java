package strategy;

import java.io.IOException;

public class OpenManager implements Open {
    private Open mode;

    public OpenManager(Open mode) {
        this.mode = mode;
    }

    public void setMode(Open mode) {
        this.mode = mode;
    }

    @Override
    public Object open() throws IOException, ClassNotFoundException {
        return mode.open();
    }
}
