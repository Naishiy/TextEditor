package TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

/
        * Класс CompositeAction представляет собой действие, объединяющее два других действия.
        * Это позволяет одновременно выполнять два действия одним вызовом.
        */
public class CompositeAction extends AbstractAction {
    private final Action firstAction; // Первое действие для выполнения
    private final Action secondAction; // Второе действие для выполнения

    /
            * Конструктор класса CompositeAction.
     * @param firstAction Первое действие, которое будет выполнено
     * @param secondAction Второе действие, которое будет выполнено
     */
    public CompositeAction(Action firstAction, Action secondAction) {
        super("ПолужирныйКурсив"); // Название действия, отображаемое в пользовательском интерфейсе
        this.firstAction = firstAction;
        this.secondAction = secondAction;
    }

    /**
     * Метод, вызываемый при активации действия.
     * @param e Событие, которое инициировало действие
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction.actionPerformed(e); // Выполнение первого действия
        secondAction.actionPerformed(e); // Выполнение второго действия
    }
}