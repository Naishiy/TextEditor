package TextEditor;

import TextEditor.TextPanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/
        * Класс ControlFile управляет файловыми операциями в текстовом редакторе.
        * Он позволяет создавать новые файлы, сохранять и открывать существующие файлы.
 */
public class ControlFile {
    private static TextPanel textPanel; // Панель текста, с которой будет работать файловый контроллер
    private static JFrame frame; // Главное окно приложения, используемое для диалоговых окон
    private static File currentFile; // Текущий открытый файл

    /
            * Конструктор класса ControlFile.
     * @param textPanel Панель текста, с которой будет работать файловый контроллер
     * @param frame Главное окно приложения
     */
    public ControlFile(TextPanel textPanel, JFrame frame) {
        this.textPanel = textPanel;
        this.frame = frame;
    }

    /
            * Метод для создания нового файла.
     * Очищает текстовую панель и сбрасывает текущий файл.
     */
    public static void newFile() {
        textPanel.getTextArea().setText(""); // Очистка текстовой панели
        currentFile = null; // Сброс текущего файла
    }

    /
            * Метод для сохранения файла как нового.
            * Открывает диалоговое окно для выбора места сохранения файла.
            */
    public static void saveAs() {
        JFileChooser fileChooser = new JFileChooser(); // Создание диалога для выбора файла
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            save(fileChooser.getSelectedFile()); // Сохранение файла
        }
    }

    /
            * Метод для сохранения файла.
            * @param file Файл, в который будет сохранен текст
     */
    public static void save(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(textPanel.getTextArea().getText()); // Запись текста из текстовой панели в файл
            writer.close(); // Закрытие потока записи
            currentFile = file; // Обновление текущего файла
        } catch (IOException e) {
            e.printStackTrace(); // Вывод информации об ошибке
        }
    }

    /
            * Метод для сохранения текущего файла.
     * Если файл не был создан, вызывает метод saveAs().
            */
    public static void save() {
        if (currentFile == null) {
            saveAs(); // Сохранение файла как нового
        } else {
            save(currentFile); // Сохранение текущего файла
        }
    }

    /
            * Метод для получения текущего файла.
     * @return Возвращает текущий файл.
     */
    public File getCurrentFile() {
        return currentFile;
    }

    /
            * Метод для установки текущего файла.
     * @param file Файл, который будет установлен как текущий
     */
    public void setCurrentFile(File file) {
        this.currentFile = file;
    }
}