package sudoken.extension.jigsaw;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class Jigsaw extends Extension {
	static {
		ExtensionManager.register(new Jigsaw());
	}
	
	public Jigsaw() {
		super(new JigsawParser(), new JigsawCreator());
	}
}
