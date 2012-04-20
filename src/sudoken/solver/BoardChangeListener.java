package sudoken.solver;

import sudoken.domain.Board;

public interface BoardChangeListener {

    public void processUpdatedBoard(Board solvedBoard);
}
