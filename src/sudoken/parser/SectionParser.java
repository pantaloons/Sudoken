package sudoken.parser;

import java.io.IOException;
import java.util.Collection;
import sudoken.domain.*;

public interface SectionParser {
    Collection<Constraint> load(String metadata, int width, int height)
            throws IOException;
}
