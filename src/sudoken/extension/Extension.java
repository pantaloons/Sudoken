package sudoken.extension;

import sudoken.domain.BoardCreator;
import sudoken.persistence.SectionParser;

public abstract class Extension {
    private SectionParser parser;
    private BoardCreator creator;

    public Extension(SectionParser parser, BoardCreator creator) {
        this.parser = parser;
        this.creator = creator;
    }

    public SectionParser getParser() {
        return parser;
    }

    public BoardCreator getCreator() {
        return creator;
    }
}
