package sudoken.domain;

import sudoken.gui.BoardGraphics;

/**
 * A ConstraintDecorator is used to decorate Constraints on a Board
 * @author tim
 *
 */
public abstract class ConstraintDecorator {
    /**
     * Decorate a BoardGraphics with this constraint
     * @param bg BoardGraphics to decorate
     */
	public abstract void decorate(BoardGraphics bg);

}
