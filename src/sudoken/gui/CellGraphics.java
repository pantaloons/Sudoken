package sudoken.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CellGraphics extends JPanel {
    private static final long serialVersionUID = 3126607752896973719L;
    
    private JLabel txt;
    
    public CellGraphics(String label) {
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        txt = new JLabel(label);
        setPreferredSize(new Dimension(35, 35));
        add(txt, gbc);
        revalidate();
    }
    
    public void setBorderWidth(int top, int right, int bottom, int left) {
        setBorder(BorderFactory.createMatteBorder(top,left, bottom, right, Color.BLACK));
    }
    
    public void setColor(Color c) {
        setBackground(c);
    }
    
    public void setText(String s) {
        txt.setText(s);
    }
}