package sudoken.gui.util.errordisplay;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * {@code ScrollTextArea} is simply a scrollable text area. It is useful to uses
 * with {@link javax.swing.JOptionPane} to allow displayed messages to wrap, and
 * not create a very wide dialog.
 * 
 * @author Kevin Doran
 */
public class ScrollTextArea extends JScrollPane {
    /**
     * 
     */
    private static final long serialVersionUID = 8731919606768422549L;
    private final int INSET_SIZE = 5;
    private final int DEFAULT_WIDTH = 200;
    private final int DEFAULT_HEIGHT = 100;

    /**
     * Constructs a {@code ScrollTextArea}, setting the area's text to be the
     * given {@code String}.
     * 
     * @param text
     *            the text the <ScrollTextArea} is to display.
     */
    public ScrollTextArea(String text) {
        final JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE,
                INSET_SIZE));
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        // Assign the text area to the scroll pane.
        getViewport().setView(textArea);
    }
}
