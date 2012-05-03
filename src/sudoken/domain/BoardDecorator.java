package sudoken.domain;

import java.util.ArrayList;
import java.util.List;

import sudoken.gui.BoardGraphics;

/**
 * 
 * A Board Decorator is used to decorate a BoardGraphics object. ConstraintDecorators may
 * be added to the BoardDecorator to render constraints on the board.
 *
 */
public class BoardDecorator {
	
	List<ConstraintDecorator> constraintDecorators = new ArrayList<ConstraintDecorator>();
	
	/**
	 * Decorate a BoardGraphics
	 * @param bg BoardGraphics to decorate
	 */
	public void decorate(BoardGraphics bg) {
		for (ConstraintDecorator cd: constraintDecorators) {
			cd.decorate(bg);
		}
	}
	
	public void addConstraintDecorator(ConstraintDecorator c){
		constraintDecorators.add(c);
	}
}