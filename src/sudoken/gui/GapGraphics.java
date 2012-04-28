package sudoken.gui;

import java.awt.Dimension;

public class GapGraphics extends CellGraphics {
	private static final long serialVersionUID = 2654728970861543683L;

	public GapGraphics(String text) {
		super(text);

		this.setPreferredSize(new Dimension(0,0));
	}

}