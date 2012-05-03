package sudoken.extension.diagonals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sudoken.domain.BoardDecorator;
import sudoken.domain.Constraint;
import sudoken.domain.Position;
import sudoken.domain.UniqueConstraint;
import sudoken.gui.ShadingDecorator;
//import sudoken.domain.UniqueConstraint;
//import sudoken.extensions.diagonals.DiagonalsUniqueConstraint;
import sudoken.persistence.SectionParser;

/**
 * Parser for Diagonals puzzle Boards
 *
 */
public class DiagonalsParser implements SectionParser {
	
	/**
	 * Name of the encompassing Extension
	 */
	private static final String EXTENSION_NAME = "diagonals";
	
    /**
     * Format: No additional formatting.
     * 
     * @return 
     */
    @Override
    public Collection<Constraint> load(String config, int width, int height, BoardDecorator bd) {
    	
        List<Constraint> diagonalConstraints = new ArrayList<Constraint>();
        
        if (width != height)
            throw new IllegalArgumentException(
                    "Width and height must be equal.");
        
		UniqueConstraint forwardConstraint = new UniqueConstraint(EXTENSION_NAME, false, false);
		for (int i = 0; i < width; i++) {
			forwardConstraint.add(new Position(i, i));
		}
		diagonalConstraints.add(forwardConstraint);
		
		UniqueConstraint backwardsConstraint = new UniqueConstraint(EXTENSION_NAME, false, false);
		for (int i = 0; i < width; i++) {
			backwardsConstraint.add(new Position(width - i - 1, i));
		}
		diagonalConstraints.add(backwardsConstraint);
		
		bd.addConstraintDecorator(new ShadingDecorator(backwardsConstraint.getPositions(), Color.DARK_GRAY));
		bd.addConstraintDecorator(new ShadingDecorator(forwardConstraint.getPositions(), Color.DARK_GRAY));
		
		return diagonalConstraints;
    }
}