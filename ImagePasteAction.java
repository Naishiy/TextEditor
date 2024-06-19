package TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagePasteAction extends AbstractAction {
    private JTextPane textPane;

    public ImagePasteAction(JTextPane textPane) {
        super("Вставить изображение");
        this.textPane = textPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        if (contents != null && contents.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            try {
                BufferedImage image = (BufferedImage) contents.getTransferData(DataFlavor.imageFlavor);
                textPane.insertIcon(new ImageIcon(image));
            } catch (UnsupportedFlavorException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}