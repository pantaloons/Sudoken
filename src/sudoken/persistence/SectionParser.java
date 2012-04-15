package sudoken.persistence;

import java.util.Collection;
import sudoken.domain.*;

public interface SectionParser {
    Collection<Constraint> load(String metadata);
}
