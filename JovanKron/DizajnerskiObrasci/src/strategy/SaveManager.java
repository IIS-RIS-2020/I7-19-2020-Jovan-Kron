package strategy;

import java.io.IOException;

public class SaveManager implements Save {
    private Save mode;

    public SaveManager(Save mode) {
        this.mode = mode;
    }

    @Override
    public void save(Object data) throws IOException {
        mode.save(data);
    }
}
