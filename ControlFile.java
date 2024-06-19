package TextEditor;

import TextEditor.TextPanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ControlFile {
    private static TextPanel textPanel;
    private static JFrame frame;
    private static File currentFile;

    public ControlFile(TextPanel textPanel, JFrame frame) {
        this.textPanel = textPanel;
        this.frame = frame;
    }

    public static void newFile() {
        textPanel.getTextPane().setText("");
        currentFile = null;
    }

    public static void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            save(fileChooser.getSelectedFile());
        }
    }

    public static void save(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(textPanel.getTextPane().getText());
            writer.close();
            currentFile = file;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        if (currentFile == null) {
            saveAs();
        } else {
            save(currentFile);
        }
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File file) {
        this.currentFile = file;
    }
}