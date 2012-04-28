package sudoken.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class CellGraphics extends JPanel {
    private static final long serialVersionUID = 3126607752896973719L;
    
    private JLabel txt;
    
    public CellGraphics(String label) {
        super(new GridBagLayout());
        
        //setBackground(UIManager.getColor("nimbusLightBackground"));
        setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        setPreferredSize(new Dimension(35, 35));
        
        txt = new JLabel(label);
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