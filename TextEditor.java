package TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TextEditor extends JFrame {
    private TextPanel textPanel;
    private ControlPanel controlPanel;
    private ControlFile controlFile;

    public TextEditor() {
        super("Текстовый редактор");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        initKeyBindings();
    }

    private void initComponents() {
        textPanel = new TextPanel();
        controlPanel = new ControlPanel(textPanel);
        controlFile = new ControlFile(textPanel, this);
        add(textPanel.getScrollPane(), BorderLayout.CENTER);
        add(controlPanel.getPanel(), BorderLayout.NORTH);
    }

    private void initMenu() {
    }

    private void initKeyBindings() {
        // Создание нового файла
        KeyStroke newKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        textPanel.getTextArea().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(newKeyStroke, "new");
        textPanel.getTextArea().getActionMap().put("new", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFile.newFile();
            }
        });

        // Сохранение файла
        KeyStroke saveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        textPanel.getTextArea().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(saveKeyStroke, "save");
        textPanel.getTextArea().getActionMap().put("save", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFile.save();
            }
        });

        // Сохранение файла как...
        KeyStroke saveAsKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
        textPanel.getTextArea().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(saveAsKeyStroke, "saveAs");
        textPanel.getTextArea().getActionMap().put("saveAs", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFile.saveAs();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextEditor().setVisible(true));
    }
}