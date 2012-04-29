package sudoken.extension.futoshiki;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

public class Futoshiki extends Extension {

    public Futoshiki() {
        super(new FutoshikiParser(), new FutoshikiCreator(), new FutoshikiDecorator());
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	Set<String> s = new HashSet<String>();
    	s.add("latinsquare");
    	return s;
    }
}
