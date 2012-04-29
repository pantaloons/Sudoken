package sudoken.gui;

import java.awt.Dimension;

public class GapGraphics extends CellGraphics {
	//TODO: This shouldn't be a CellGraphics (may want non-text in a gap?)
	private static final long serialVersionUID = 2654728970861543683L;

	public GapGraphics(String text) {
		super(text);

		this.setPreferredSize(new Dimension(0,0));
	}

}