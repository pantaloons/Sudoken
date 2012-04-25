package sudoken.persistence;

import java.io.IOException;
import java.util.Collection;
import sudoken.domain.*;

public interface SectionParser {
    Collection<Constraint> load(String metadata, int width, int height, BoardDecorator bd)
            throws IOException;
}
