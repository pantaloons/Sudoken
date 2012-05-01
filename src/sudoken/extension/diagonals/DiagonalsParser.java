package sudoken.extension.diagonals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sudoken.domain.BoardDecorator;
import sudoken.domain.Constraint;
import sudoken.domain.Position;
//import sudoken.domain.UniqueConstraint;
//import sudoken.extensions.diagonals.DiagonalsUniqueConstraint;
import sudoken.persistence.SectionParser;

public class DiagonalsParser implements SectionParser {
	
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
        
		DiagonalsUniqueConstraint forwardConstraint = new DiagonalsUniqueConstraint(EXTENSION_NAME, false);
		for (int i = 0; i < width; i++) {
			forwardConstraint.add(new Position(i, i));
		}
		diagonalConstraints.add(forwardConstraint);
		
		DiagonalsUniqueConstraint backwardsConstraint = new DiagonalsUniqueConstraint(EXTENSION_NAME, false);
		for (int i = 0; i < width; i++) {
			backwardsConstraint.add(new Position(width - i - 1, i));
		}
		diagonalConstraints.add(backwardsConstraint);
		
		return diagonalConstraints;
    }
}