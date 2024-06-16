package TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ControlPanel {
    private JPanel panel;
    private JComboBox<String> fontChoice;
    private JComboBox<Integer> fontSizeChoice;
    private TextPanel textPanel;

    public ControlPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
        panel = new JPanel();
        createControlPanel();
    }

    private void createControlPanel() {
        JLabel fontLabel = new JLabel("Шрифт:");
        fontChoice = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontChoice.addActionListener((ActionListener) new FontActionListener());

        JLabel fontSizeLabel = new JLabel("Размер:");
        Integer[] sizes = { 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 };
        fontSizeChoice = new JComboBox<>(sizes);
        fontSizeChoice.addActionListener(new FontSizeActionListener());

        // Кнопка "Создать"
        JButton makeButton = new JButton("Создать (Ctrl+N)");
        makeButton.addActionListener(e -> ControlFile.newFile());

        // Кнопка "Сохранить"
        JButton saveButton = new JButton("Сохранить (Ctrl+S)");
        saveButton.addActionListener(e -> ControlFile.save());

        // Кнопка "Сохранить как..."
        JButton saveAsButton = new JButton("Сохранить как... (Ctrl+Shift+S)");
        saveAsButton.addActionListener(e -> ControlFile.saveAs());

        panel.add(fontLabel);
        panel.add(fontChoice);
        panel.add(fontSizeLabel);
        panel.add(fontSizeChoice);
        panel.add(makeButton);
        panel.add(saveButton);
        panel.add(saveAsButton);

    }

    private void setJMenuBar(JMenuBar menuBar) {
    }

    public JPanel getPanel() {
        return panel;
    }

    // Внутренние классы для обработки событий
    private class FontActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            updateTextProperties();
        }
    }

    private class FontSizeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            updateTextProperties();
        }
    }

    private void updateTextProperties() {
        String fontName = (String) fontChoice.getSelectedItem();
        Integer fontSize = (Integer) fontSizeChoice.getSelectedItem();
        Font font = new Font(fontName, Font.PLAIN, fontSize);

        JTextArea textArea = textPanel.getTextArea();
        if (textArea.getSelectedText() != null) { // Проверка наличия выделенного текста
            textArea.setFont(font); // Применение шрифта ко всему тексту, если нет выделения
        } else {
            String allText = textArea.getText();
            String beforeSelection = allText.substring(0, textArea.getSelectionStart());
            String selectedText = textArea.getSelectedText();
            String afterSelection = allText.substring(textArea.getSelectionEnd());

            textArea.setText(beforeSelection + selectedText + afterSelection);
            textArea.select(beforeSelection.length(), beforeSelection.length() + selectedText.length());
            textArea.replaceSelection(selectedText); // Применение шрифта только к выделенному тексту
        }
    }
}
