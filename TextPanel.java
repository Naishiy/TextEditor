package TextEditor;

import javax.swing.*;

public class TextPanel {
    private JTextPane textPane;
    private JScrollPane scrollPane;

    public TextPanel() {
        textPane = new JTextPane();
        scrollPane = new JScrollPane(textPane);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTextPane getTextPane() {
        return textPane;
    }
}