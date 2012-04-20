package sudoken.extension.futoshiki;

import java.util.*;

import sudoken.persistence.*;
import sudoken.domain.*;

public class FutoshikiParser implements SectionParser {
    /**
     * Format: cella inequality cellb Eg: 0 5 < 0 6
     * 
     * @return
     */
    @Override
    public Collection<Constraint> load(String config, int width, int height) {
        Scanner sc = new Scanner(config);
        List<Constraint> c = new ArrayList<Constraint>();
        while (sc.hasNext()) {
            Position p1 = new Position(sc.nextInt(), sc.nextInt());
            String type = sc.next();
            Position p2 = new Position(sc.nextInt(), sc.nextInt());
            c.add(new InequalityConstraint(p1, p2, type.equals("<")));
        }
        return c;
    }
}
