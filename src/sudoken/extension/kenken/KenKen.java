package sudoken.extension.kenken;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class KenKen extends Extension {
    static {
        ExtensionManager.register(new KenKen());
    }

    public KenKen() {
        super(new KenKenParser(), null);
    }
    
    @Override
    public boolean hasPrerequisites() {
    	// No dependencies.
    	return true;
    }
}
