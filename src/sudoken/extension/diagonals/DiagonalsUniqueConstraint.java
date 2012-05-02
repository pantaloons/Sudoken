package sudoken.extension.diagonals;

import java.awt.*;

import sudoken.gui.*;
import sudoken.domain.*;

/**
 * DiagonalsUniqueConstraint overrides paint functionality to color diagonal squares.
 */
public class DiagonalsUniqueConstraint extends UniqueConstraint {
	public DiagonalsUniqueConstraint(String provider, boolean shouldSave) {
    	super(provider, shouldSave, false);
    }
	
	@Override
    public void decorate(BoardGraphics bg) {
		for (Position p : positions) {
			bg.getCell(p).setColor(Color.LIGHT_GRAY);
		}
	}
}
