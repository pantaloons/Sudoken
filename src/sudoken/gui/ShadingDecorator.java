package sudoken.gui;

import java.awt.Color;
import java.util.List;

import sudoken.domain.ConstraintDecorator;
import sudoken.domain.Position;

/**
 * ShadingDecorator decorates a Board by shading some cells
 * @author Tim Hobbs
 *
 */
public class ShadingDecorator extends ConstraintDecorator {

	private List<Position> positions;
	private Color colour;

	/**
	 * Create a ShadingDecorator
	 * @param positions Positions of Cells to shade
	 * @param color Color to shade cells
	 */
	public ShadingDecorator(List<Position> positions, Color color) {
		this.positions = positions;
		this.colour = colour;
	}
	
	@Override
	public void decorate(BoardGraphics bg) {
		for (Position p : positions) {
			bg.getCell(p).setColor(colour);
		}
	}

}
