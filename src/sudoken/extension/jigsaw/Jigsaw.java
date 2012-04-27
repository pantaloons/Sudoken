package sudoken.extension.jigsaw;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Jigsaw extends Extension {
    static {
        ExtensionManager.register(new Jigsaw());
    }

    public Jigsaw() {
        super(new JigsawParser(), new JigsawCreator(), ExtensionManager.getDecorator("latinsquare"));
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	Set<String> s = new HashSet<String>();
    	s.add("latinsquare");
    	return s;
    }
}
