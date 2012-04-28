package sudoken.extension.jigsaw;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Jigsaw extends Extension {

    public Jigsaw() {
        super(new JigsawParser(), new JigsawCreator());
    }
    
    @Override
    public boolean hasPrerequisites() {
    	// Depends on latin square
    	return ExtensionManager.hasExtension("latinsquare");
    }
}
