package sudoken.extension.diagonals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sudoken.domain.Constraint;
import sudoken.domain.Position;
import sudoken.domain.UniqueConstraint;
import sudoken.parser.SectionParser;

public class DiagonalsParser implements SectionParser {
    /**
     * Format: No additional formatting.
     * 
     * @return
     */
    @Override
    public Collection<Constraint> load(String config, int width, int height) {
    	
        List<Constraint> diagonalConstraints = new ArrayList<Constraint>();
        
        if (width != height)
            throw new IllegalArgumentException(
                    "Width and height must be equal.");
        
		UniqueConstraint forwardConstraint = new UniqueConstraint();
		for (int i = 0; i < width; i++) {
			forwardConstraint.add(new Position(i, i));
		}
		diagonalConstraints.add(forwardConstraint);
		
		UniqueConstraint backwardsConstraint = new UniqueConstraint();
		for (int i = 0; i < width; i++) {
			backwardsConstraint.add(new Position(width - i - 1, i));
		}
		diagonalConstraints.add(backwardsConstraint);
		
		return diagonalConstraints;
    }
}