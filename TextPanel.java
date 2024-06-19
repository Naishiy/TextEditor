package TextEditor;

import javax.swing.*;

/
        * Класс TextPanel отвечает за создание текстовой панели в текстовом редакторе.
        * Эта панель включает в себя текстовое поле для ввода и редактирования текста,
 * а также прокручиваемую область, которая позволяет пользователю листать текст.
        */
public class TextPanel {
    private JTextArea textArea; // Текстовое поле для ввода текста
    private JScrollPane scrollPane; // Прокручиваемая область, содержащая текстовое поле

    /
            * Конструктор класса TextPanel.
     * Создает новое текстовое поле и прокручиваемую область для него.
     */
    public TextPanel() {
        textArea = new JTextArea(); // Инициализация текстового поля
        scrollPane = new JScrollPane(textArea); // Создание прокручиваемой области с текстовым полем внутри
    }

    /
            * Метод для получения прокручиваемой области.
     * @return Возвращает прокручиваемую область с текстовым полем.
            */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /
            * Метод для получения текстового поля.
     * @return Возвращает текстовое поле.
     */
    public JTextArea getTextArea() {
        return textArea;
    }
}