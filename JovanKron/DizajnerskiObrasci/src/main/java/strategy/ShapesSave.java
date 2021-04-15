package strategy;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ShapesSave implements Save {
    private String filename;

    @Override
    public void save(Object data) throws IOException {
        JButton save = new JButton();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        if (filename == null) {
            chooser.setSelectedFile(new File("shapes"));
            chooser.setFileFilter(new FileNameExtensionFilter("ser file", "ser"));
            if (chooser.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                filename = chooser.getSelectedFile().getName() + ".ser";
            }
        }
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(data);

        out.close();
        file.close();

        System.out.println("Object has been serialized");
    }
}
