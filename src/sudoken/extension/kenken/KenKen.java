package sudoken.extension.kenken;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class KenKen extends Extension {
    static {
        ExtensionManager.register(new KenKen());
    }

    public KenKen() {
        super(new KenKenParser(), null, null);
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	return new HashSet<String>();
    }
}
