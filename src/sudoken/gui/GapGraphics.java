package sudoken.gui;

import java.awt.Dimension;
import java.awt.Color;

/**
 * Gap Graphics is used to display constraints between Cells as well as to
 * space out Cells on a BoardGraphics 
 * @author Tim Hobbs
 *
 */
public class GapGraphics extends CellGraphics {
	//TODO: This shouldn't be a CellGraphics (may want non-text in a gap?)
	private static final long serialVersionUID = 2654728970861543683L;

	public GapGraphics(String text) {
		super(text);

		this.setPreferredSize(new Dimension(0,0));
		setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
	}

}
