package sudoken.extension.jigsaw;

import sudoken.domain.BoardDecorator;
import sudoken.extension.ExtensionManager;
import sudoken.gui.BoardGraphics;

public class JigsawDecorator extends BoardDecorator {

	@Override
	public void decorate(BoardGraphics bg) {
		ExtensionManager.getDecorator("latinsquare").decorate(bg);
	}
}
