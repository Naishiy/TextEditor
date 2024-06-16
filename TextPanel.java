package TextEditor;

import javax.swing.*;

public class TextPanel {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public TextPanel() {
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}