package strategy;

import geometry.Shape;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ShapesOpen implements Open {
    private String filename;

    @Override
    public Object open() throws IOException, ClassNotFoundException {
        JButton open = new JButton();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new FileNameExtensionFilter("ser file","ser"));
        if (chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile().getName();
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Shape> readFileCommands = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            return readFileCommands;
        }
        return null;
    }
}
