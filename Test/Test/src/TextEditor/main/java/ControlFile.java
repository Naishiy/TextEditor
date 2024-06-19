import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        frame.setTitle("Новый файл");
    }

    public static void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовые файлы", "txt"));
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
            }
            save(fileToSave);
            frame.setTitle(fileToSave.getName());
        }
    }

    public static void save(File file) {
        if (!file.getName().toLowerCase().endsWith(".txt")) {
            file = new File(file.getParentFile(), file.getName() + ".txt");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textPanel.getTextPane().getText());
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

    public static void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовые файлы", "txt"));
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
                textPanel.getTextPane().read(reader, null);
                currentFile = fileToOpen;
                frame.setTitle(fileToOpen.getName()); // Установка заголовка окна в имя файла
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File file) {
        this.currentFile = file;
    }
}