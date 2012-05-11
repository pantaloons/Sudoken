package sudoken.extension.kenken;

import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import sudoken.domain.ConstraintDecorator;
import sudoken.gui.BoardGraphics;
import sudoken.gui.BorderDecorator;
import sudoken.gui.CellGraphics;

public class OperatorDecorator extends ConstraintDecorator {

	private OperatorConstraint constraint;
	private BorderDecorator borderDecorator;

	OperatorDecorator(OperatorConstraint c) {
		this.constraint = c;
		this.borderDecorator = new BorderDecorator(c.getPositions());
	}
	
	@Override
	public void decorate(BoardGraphics bg) {
    	CellGraphics firstCell = bg.getCell(constraint.getPositions().get(0));
    	JLabel cageLabel = new JLabel();
    	cageLabel.setFont(cageLabel.getFont().deriveFont(Font.BOLD, 9));
    	cageLabel.setText(constraint.getRepresentation());
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridy = 1;

    	firstCell.add(cageLabel, gbc);
    	
    	borderDecorator.decorate(bg);
	}
	


}
