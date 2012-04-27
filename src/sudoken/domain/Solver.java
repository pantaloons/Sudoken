package sudoken.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The main sudoku solver. The sudoku solver solves the given sudoku board while
 * taking into account any added constraints.
 * 
 * @author Kevin Doran
 * 
 */
public abstract class Solver {
    protected Board board;
    private Collection<BoardChangeListener> listeners = new ArrayList<BoardChangeListener>();
    private AtomicLong milisecondDelay = new AtomicLong();
    private AtomicBoolean running = new AtomicBoolean(true);
    
    public Solver() {
    }

    /**
     * Sets the sudoku board that is to be solved.
     * 
     * @param sudokuBoard
     *            the sudoku board to be solved.
     */
    public void setSudokuBoard(Board sudokuBoard) {
        board = sudokuBoard;
        notifyListeners(board);
    }
    
    public Board getSudokuBoard() {
    	return board;
    }

    /**
     * Solves the sudoku board.
     * 
     * @return {@code true} if the board is solvable, {@code false} if it is
     *         not.
     */
    public abstract boolean solve();

    /**
     * Subscribes a SolverListener to this solver. The BoardChangeListener will
     * now have its {@link BoardChangeListener#processSolvedBoard(Board)} method
     * called when a board is loaded or is changed/solved.
     * 
     * @param listener
     *            the BoardChangeListener to subscribe.
     * @return {@code true} if the listener was successfully subscribed,
     *         {@code false} otherwise. A potential reason for having
     *         {@code false} returned is that the listener was already added
     *         previously.
     */
    public boolean addListener(BoardChangeListener listener) {
        boolean added = listeners.add(listener);
        if (board != null) {
            // Inform listeners of the current board.
            listener.processUpdatedBoard(board);
        }
        return added;
    }
    
    protected void notifyListeners(Board solvedBoard) {
        for (BoardChangeListener listener : listeners) {
            listener.processUpdatedBoard(solvedBoard);
        }
    }
    
    public void setStepsPerSecond(int stepsPerSecond) {
        if(stepsPerSecond == 0) {
            running.set(false);
        }
        running.set(true);
        long delay = 1000 / stepsPerSecond;
        this.milisecondDelay.set(delay);
    }
    
    protected long getMilisecondDelay() {
        return this.milisecondDelay.get();
    }
}
