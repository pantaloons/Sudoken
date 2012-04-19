package sudoken.solver;

import sudoken.domain.Board;

public interface BoardChangeListener {

    public void processSolvedBoard(Board solvedBoard);
}
