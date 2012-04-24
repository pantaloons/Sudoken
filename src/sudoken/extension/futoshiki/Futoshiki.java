package sudoken.extension.futoshiki;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Futoshiki extends Extension {
    static {
        ExtensionManager.register(new Futoshiki());
    }

    public Futoshiki() {
        super(new FutoshikiParser(), new FutoshikiCreator(), null);
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	Set<String> s = new HashSet<String>();
    	s.add("latinsquare");
    	return s;
    }
}
