package sudoken.extension.jigsaw;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

public class Jigsaw extends Extension {

    public Jigsaw() {
        super(new JigsawParser(), new JigsawCreator(), new JigsawDecorator());
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	Set<String> s = new HashSet<String>();
    	s.add("latinsquare");
    	return s;
    }
}
