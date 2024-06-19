package TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/
        * Класс ControlPanel отвечает за создание панели управления текстовым редактором.
 * Эта панель содержит элементы управления для выбора шрифта, размера шрифта и форматирования текста,
 * а также кнопки для создания нового файла, сохранения и сохранения файла как нового документа.
 */
public class ControlPanel {
    private JPanel panel; // Панель, содержащая все элементы управления
    private JComboBox<String> fontChoice; // Выпадающий список для выбора шрифта
    private JComboBox<Integer> fontSizeChoice; // Выпадающий список для выбора размера шрифта
    private JComboBox<String> fontFormatChoice; // Выпадающий список для выбора форматирования текста
    private TextPanel textPanel; // Ссылка на панель текста, с которой взаимодействуют элементы управления

    /
            * Конструктор класса ControlPanel.
     * @param textPanel Ссылка на объект класса TextPanel, который представляет собой панель текста.
            */
    public ControlPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
        panel = new JPanel();
        createControlPanel(); // Метод для создания элементов управления
    }

    /**
     * Метод для создания и настройки элементов управления на панели.
     */
    private void createControlPanel() {
        // Создание и добавление меток и выпадающих списков для выбора параметров шрифта
        JLabel fontLabel = new JLabel("Шрифт:");
        fontChoice = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontChoice.addActionListener(new FontActionListener()); // Добавление слушателя событий

        JLabel fontSizeLabel = new JLabel("Размер:");
        Integer[] sizes = {8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40};
        fontSizeChoice = new JComboBox<>(sizes);
        fontSizeChoice.addActionListener(new FontSizeActionListener()); // Добавление слушателя событий

        JLabel fontFormatLabel = new JLabel("Форматирование:");
        String[] formats = {"Обычный", "Курсив", "Полужирный", "Полужирный курсив"};
        fontFormatChoice = new JComboBox<>(formats);
        fontFormatChoice.addActionListener(new FontFormatActionListener()); // Добавление слушателя событий

        // Создание и добавление кнопок для управления файлами
        JButton makeButton = new JButton("Создать (Ctrl+N)");
        makeButton.addActionListener(e -> ControlFile.newFile()); // Добавление слушателя событий

        JButton saveButton = new JButton("Сохранить (Ctrl+S)");
        saveButton.addActionListener(e -> ControlFile.save()); // Добавление слушателя событий

        JButton saveAsButton = new JButton("Сохранить как... (Ctrl+Shift+S)");
        saveAsButton.addActionListener(e -> ControlFile.saveAs()); // Добавление слушателя событий

        // Добавление всех элементов управления на панель
        panel.add(fontLabel);
        panel.add(fontChoice);
        panel.add(fontSizeLabel);
        panel.add(fontSizeChoice);
        panel.add(fontFormatLabel);
        panel.add(fontFormatChoice);
        panel.add(makeButton);
        panel.add(saveButton);
        panel.add(saveAsButton);
    }

    // Оставшиеся методы и внутренние классы...
}