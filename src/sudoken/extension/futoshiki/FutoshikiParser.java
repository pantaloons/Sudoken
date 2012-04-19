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
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            String type = sc.next();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            c.add(new InequalityConstraint(x1, y1, x2, y2, type.equals("<")));
        }
        return c;
    }
}
