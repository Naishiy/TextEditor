package TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CompositeAction extends AbstractAction {
    private final Action firstAction;
    private final Action secondAction;

    public CompositeAction(Action firstAction, Action secondAction) {
        super("ПолужирныйКурсив");
        this.firstAction = firstAction;
        this.secondAction = secondAction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction.actionPerformed(e);
        secondAction.actionPerformed(e);
    }
}