package sudoken.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

/**
 * A <code>LabelledFileChooser</code> is a grouping of a
 * {@link javax.swing.JFileChooser} , a {@link javax.swing.JButton} and a
 * {@link javax.swing.JLabel} . <code>LabelledFileChooser</code> combines the
 * common tasks of creating <code>FileChooser</code>, an accompanying
 * <code>JLabel</code> to identify the <code>FileChooser</code> and an
 * accompanying <code>JButton</code> to allow the user to open a
 * <code> FileChooser</code> dialog.
 * 
 * @author Kevin Doran
 * @version 1.0 11.04.2011
 */
public class LabelledFileChooser extends JPanel {
    private JFileChooser fileChooser;
    private JTextField textField;
    private JLabel label;
    private JButton browseButton;

    /**
     * Constructs a <code>LabelledFileChooser</code> using the given label and
     * button name.
     * 
     * @param labelName
     *            the text to be used as the <code>LabelledFileChooser
     * </code>'s label.
     * @param buttonName
     *            the text to be used on the 'browse' button.
     */
    public LabelledFileChooser(String labelName, String buttonName) {
        label = new JLabel(labelName);
        browseButton = new JButton(buttonName);
        textField = new JTextField();
        fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("./"));
        MigLayout layout = new MigLayout("insets 0", "[][grow][]");
        setLayout(layout);
        add(label, "gapx 0 10");
        add(textField, "growx");
        add(browseButton);

        browseButton.addActionListener(new OpenFile());
    }

    /**
     * Constructs a <code>LabelledFileChooser</code> using the given label name
     * and using the default 'browse' button text: browse.
     * 
     * @param labelName
     *            the text to be used as the <code>LabelledFileChooser
     * </code>'s label.
     */
    public LabelledFileChooser(String labelName) {
        this(labelName, "Browse");
    }

    /**
     * Responds to a user action by opening a <code>JFileChooser</code> dialog.
     * The path of the chosen file or directory is then set as the text in the
     * label for the <code>LabelledFileChooser</code>. This is the listener for
     * the <code>LabelledFileChooser</code>'s button.
     */
    class OpenFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int returnStatus = fileChooser
                    .showOpenDialog(LabelledFileChooser.this);
            if (returnStatus == JFileChooser.APPROVE_OPTION)
                textField.setText(fileChooser.getSelectedFile()
                        .getAbsolutePath());
        }
    }

    /**
     * Returns the <code>JFileChooser</code> being used by the
     * <code> LabelledFileChooser</code>. This is useful for modifying
     * properties of the <code>JFileChooser</code> such as file filters.
     * 
     * @return the <code>JFileChooser</code> being used by the
     *         <code>  LabelledFileChooser</code>.
     * @uml.property name="fileChooser"
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * Sets the <code>JFileChooser</code> being used by the
     * <code> LabelledFileChooser</code>. This is useful for modifying
     * properties of the <code>JFileChooser</code> such as file filters, and for
     * allowing multiple
     * <code>LabelledFileChooser<code>s to share a common <code> JFileChooser</code>
     * .
     * 
     * @param fileChooser
     *            the <code>JFileChooser</code> being used by the
     *            <code>LabelledFileChooser</code>.
     * @uml.property name="fileChooser"
     */
    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    /**
     * Returns the text within the <code>LabelledFileChooser</code>'s
     * <code> JLabel</code>.
     * 
     * @return the text within the <code>LabelledFileChooser</code>'s
     *         <code>  JLabel</code>.
     * @uml.property name="textField"
     */
    public JTextField getTextField() {
        return textField;
    }
}