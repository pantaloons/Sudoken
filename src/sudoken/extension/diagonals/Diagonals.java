package sudoken.extension.diagonals;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Diagonals extends Extension {
    static {
        ExtensionManager.register(new Diagonals());
    }

    public Diagonals() {
        super(new DiagonalsParser(), null, null);
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	return new HashSet<String>();
    }
}