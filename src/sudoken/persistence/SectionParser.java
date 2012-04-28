package sudoken.persistence;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import sudoken.domain.*;

public interface SectionParser {
    Collection<Constraint> load(String metadata, int width, int height, BoardDecorator bd)
            throws IOException;
    
    List<String> save(Collection<Constraint> constraints) throws ParseException;
}
