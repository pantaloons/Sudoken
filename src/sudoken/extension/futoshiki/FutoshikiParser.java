package sudoken.extension.futoshiki;

import java.util.*;

import sudoken.persistence.*;
import sudoken.domain.*;

/**
 * FutoshikiParser reads FutoshikiConstraints
 *
 */
class FutoshikiParser implements SectionParser {
	
	/**
	 * Name of the encompassing Extension
	 */
	private static final String EXTENSION_NAME = "futoshiki";
	
    /**
     * Format: cella inequality cellb Eg: 0 5 < 0 6
     * 
     * @return
     */
    @Override
    public Collection<Constraint> load(String config, int width, int height, BoardDecorator bd) {
        Scanner sc = new Scanner(config);
        List<Constraint> inequalityConstraints = new ArrayList<Constraint>();
        while (sc.hasNext()) {
            Position p1 = new Position(sc.nextInt(), sc.nextInt());
            String type = sc.next();
            Position p2 = new Position(sc.nextInt(), sc.nextInt());
            InequalityConstraint c = new InequalityConstraint(EXTENSION_NAME, true, p1, p2, type.equals("<"));
            inequalityConstraints.add(c);
            bd.addConstraintDecorator(new InequalityDecorator(c));
        }
        return inequalityConstraints;
    }
}
