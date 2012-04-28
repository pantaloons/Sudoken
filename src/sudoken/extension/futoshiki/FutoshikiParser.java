package sudoken.extension.futoshiki;

import java.text.ParseException;
import java.util.*;

import sudoken.persistence.*;
import sudoken.domain.*;

public class FutoshikiParser implements SectionParser {
	
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
            inequalityConstraints.add(new InequalityConstraint(EXTENSION_NAME, p1, p2, type.equals("<")));
        }
        return inequalityConstraints;
    }
    
    @Override
    public List<String> save(Collection<Constraint> constraints) throws ParseException {
    	List<String> lines = new ArrayList<String>();
    	
    	for (Constraint c : constraints) {
    		InequalityConstraint ic = (InequalityConstraint) c;
    		Position p1 = ic.getFirstPosition();
    		Position p2 = ic.getSecondPosition();
    		String sign = ">";
    		if (ic.isLess())
    			sign = "<";
    		lines.add(p1.getX() + " " + p1.getY() + " " + sign + " " + p2.getX() + " " + p2.getY());
    	}
    	
    	return lines;
    }
}
