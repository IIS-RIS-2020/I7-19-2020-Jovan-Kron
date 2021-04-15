package strategy;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommandsSave implements Save {
    private String filename;

    @Override
    public void save(Object data) throws IOException {
        JButton save = new JButton();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        if (filename == null) {
            chooser.setSelectedFile(new File("commands"));
            chooser.setFileFilter(new FileNameExtensionFilter("txt file", "txt"));
            if (chooser.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                filename = chooser.getSelectedFile().getName() + ".txt";
            }
        }
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write(((StringBuilder) data).toString());
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }
}
