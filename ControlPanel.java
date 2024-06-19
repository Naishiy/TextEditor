package TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {
    private JPanel panel;
    private JComboBox<String> fontChoice;
    private JComboBox<Integer> fontSizeChoice;
    private JComboBox<String> fontFormatChoice;
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
        Integer[] sizes = {8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40};
        fontSizeChoice = new JComboBox<>(sizes);
        fontSizeChoice.addActionListener(new FontSizeActionListener());

        JLabel fontFormatLabel = new JLabel("Форматирование:");
        String[] formats = {"Обычный", "Курсив", "Полужирный", "Полужирный курсив"};
        fontFormatChoice = new JComboBox<>(formats);
        fontFormatChoice.addActionListener(new FontFormatActionListener());

        JButton openButton = new JButton("Открыть (Ctrl+O)");
        openButton.addActionListener(e -> ControlFile.open());

        JButton makeButton = new JButton("Создать (Ctrl+N)");
        makeButton.addActionListener(e -> ControlFile.newFile());

        JButton saveButton = new JButton("Сохранить (Ctrl+S)");
        saveButton.addActionListener(e -> ControlFile.save());

        JButton saveAsButton = new JButton("Сохранить как... (Ctrl+Shift+S)");
        saveAsButton.addActionListener(e -> ControlFile.saveAs());

        panel.add(fontLabel);
        panel.add(fontChoice);
        panel.add(fontSizeLabel);
        panel.add(fontSizeChoice);
        panel.add(fontFormatLabel);
        panel.add(fontFormatChoice);
        panel.add(openButton);
        panel.add(makeButton);
        panel.add(saveButton);
        panel.add(saveAsButton);

        JButton insertDbButton = new JButton("Вставить из БД (Ctrl+D)");
        insertDbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Здесь должен быть ваш код для вызова DatabaseInsertAction
                JTextPane textPane = textPanel.getTextPane();
                String dbUrl = "jdbc:ваш_драйвер_базы_данных:адрес"; // Замените на ваш URL базы данных
                String user = "ваш_логин"; // Замените на ваш логин
                String password = "ваш_пароль"; // Замените на ваш пароль
                DatabaseInsertAction databaseInsertAction = new DatabaseInsertAction(textPane, dbUrl, user, password);
                databaseInsertAction.actionPerformed(e);
            }
        });

        panel.add(insertDbButton);
    }

    private void setJMenuBar(JMenuBar menuBar) {
    }

    public JPanel getPanel() {
        return panel;
    }

    // Внутренние классы для обработки событий
    private class FontActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            updateTextProperties(String.valueOf(Font.PLAIN));
        }
    }

    private class FontSizeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            updateTextProperties(String.valueOf(Font.PLAIN));
        }
    }

    private class FontFormatActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selectedFormat = (String) fontFormatChoice.getSelectedItem();
            updateTextProperties(selectedFormat);
        }
    }

    private void updateTextProperties(String selectedFormat) {
        String fontName = (String) fontChoice.getSelectedItem();
        Integer fontSize = (Integer) fontSizeChoice.getSelectedItem();
        int fontStyle;
        switch (selectedFormat) {
            case "Обычный":
                fontStyle = Font.PLAIN;
                break;
            case "Курсив":
                fontStyle = Font.ITALIC;
                break;
            case "Полужирный":
                fontStyle = Font.BOLD;
                break;
            case "Полужирный курсив":
                fontStyle = Font.BOLD | Font.ITALIC;
                break;
            default:
                fontStyle = Font.PLAIN; // На случай, если выбор не удается распознать
        }
        Font font = new Font(fontName, fontStyle, fontSize);

        JTextPane textArea = textPanel.getTextPane();
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