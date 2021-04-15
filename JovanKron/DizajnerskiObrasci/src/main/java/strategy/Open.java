package strategy;

import java.io.IOException;

public interface Open {
    Object open() throws IOException, ClassNotFoundException;
}
