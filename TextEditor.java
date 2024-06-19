package TextEditor;

// Импорт необходимых библиотек для работы с графическим интерфейсом и событиями.
import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/
        * Главный класс текстового редактора, наследуется от JFrame.
        * Создает основное окно приложения и управляет его компонентами.
        */
public class TextEditor extends JFrame {
    // Объявление компонентов редактора
    private TextPanel textPanel; // Панель для редактирования текста
    private ControlPanel controlPanel; // Панель с элементами управления
    private ControlFile controlFile; // Контроллер для работы с файлами

    /
            * Конструктор класса TextEditor.
     * Устанавливает начальные параметры окна и инициализирует компоненты.
     */
    public TextEditor() {
        super("Текстовый редактор"); // Заголовок окна
        setSize(600, 400); // Размер окна
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Операция закрытия окна
        initComponents(); // Инициализация компонентов
        initKeyBindings(); // Инициализация горячих клавиш
    }

    /
            * Метод для инициализации компонентов интерфейса.
     * Создает и размещает компоненты в окне.
            */
    private void initComponents() {
        textPanel = new TextPanel(); // Создание панели текста
        controlPanel = new ControlPanel(textPanel); // Создание панели управления
        controlFile = new ControlFile(textPanel, this); // Создание контроллера файла
        add(textPanel.getScrollPane(), BorderLayout.CENTER); // Добавление панели с текстом в центр
        add(controlPanel.getPanel(), BorderLayout.NORTH); // Добавление панели управления вверху
    }

    /
            * Метод для инициализации горячих клавиш.
     * Привязывает действия к комбинациям клавиш.
     */
    private void initKeyBindings() {
        // Привязка действий к горячим клавишам...
        // Пример создания нового файла:
        KeyStroke newKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        textPanel.getTextArea().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(newKeyStroke, "new");
        textPanel.getTextArea().getActionMap().put("new", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlFile.newFile(); // Создание нового файла
            }
        });

        // Другие привязки...
    }

    /**
     * Точка входа в программу.
     * Запускает текстовый редактор.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextEditor().setVisible(true));
    }
}

// Далее следуют комментарии к другим классам...