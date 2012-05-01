package sudoken.domain;

/**
 * A BoardChangeListener listens to Board update events
 */
public interface BoardChangeListener {

	/**
	 * Act on a board update
	 */
    public void processUpdatedBoard();
}
