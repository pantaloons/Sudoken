package sudoken.persistence;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import sudoken.domain.*;

public interface SectionParser {
    Collection<Constraint> load(String metadata, int width, int height)
            throws IOException;
    
    List<String> getConfig(Collection<Constraint> constraints);
}
