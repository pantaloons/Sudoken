package sudoken.domain;

import sudoken.gui.BoardGraphics;

/**
 * 
 * A Board Decorator is used to decorate a BoardGraphics object
 *
 */
public abstract class BoardDecorator {
	/**
	 * Decorate a BoardGraphics
	 * @param bg BoardGraphics to decorate
	 */
	public abstract void decorate(BoardGraphics bg);
}