package TextEditor;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private void initKeyBindings() {
        // Создание нового файла
        KeyStroke newKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        textPanel.getTextPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(newKeyStroke, "new");
        textPanel.getTextPane().getActionMap().put("new", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFile.newFile();
            }
        });

        // Сохранение файла
        KeyStroke saveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        textPanel.getTextPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(saveKeyStroke, "save");
        textPanel.getTextPane().getActionMap().put("save", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFile.save();
            }
        });

        // Сохранение файла как...
        KeyStroke saveAsKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
        textPanel.getTextPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(saveAsKeyStroke, "saveAs");
        textPanel.getTextPane().getActionMap().put("saveAs", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFile.saveAs();
            }
        });

        JTextPane textPane = textPanel.getTextPane();
        InputMap inputMap = textPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = textPane.getActionMap();

        // Привязываем горячие клавиши к действиям форматирования
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK), "Полужирный");
        actionMap.put("Полужирный", new StyledEditorKit.BoldAction());

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK), "Курсив");
        actionMap.put("Курсив", new StyledEditorKit.ItalicAction());

        // Действие для комбинированного стиля полужирного курсива
        Action boldAction = actionMap.get("Полужирный");
        Action italicAction = actionMap.get("Курсив");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK), "ПолужирныйКурсив");
        actionMap.put("ПолужирныйКурсив", new CompositeAction(boldAction, italicAction));

        // Действие для сброса форматирования
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_DOWN_MASK), "Обычный");

        // Привязываем "Ctrl+V" к действию вставки изображения
        KeyStroke pasteImageKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK);
        inputMap.put(pasteImageKeyStroke, "Вставить изображение");
        actionMap.put("Вставить изображение", new ImagePasteAction(textPane));
    }

    private TextPanel getTextPanel() {
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextEditor().setVisible(true));
    }
}