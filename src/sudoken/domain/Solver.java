package sudoken.domain;

import java.util.ArrayList;
import java.util.Collection;
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
    
    /**
     * Get the Sudoku Board being worked on by the Solver
     * @return
     */
    public Board getSudokuBoard() {
    	return board;
    }

    /**
     * Solves the sudoku board.
     * 
     * @return {@code true} if the board is solvable, {@code false} if it is
     *         not.
     */
    public abstract boolean solve() throws InterruptedException;
    
    /**
     * Stop the solver. After stop is called the solver will be in a dirty
     * state and cannot be resumed.
     */
    public abstract void stop();
    
    /**
     * Pause the solver such that it may be resumed later. Has no effect if already paused.
     */
    public abstract void pause();
    
    /**
     * Resume the solver. Has no effect if the solver is not paused. 
     */
    public abstract void resume();

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
    	return listeners.add(listener);
    }
    
    /**
     * Notify all BoardListeners to a Board change
     * @param solvedBoard The current Sudoku Board
     */
    protected void notifyListeners(Board solvedBoard) {
        for (BoardChangeListener listener : listeners) {
            listener.processUpdatedBoard();
        }
    }
    
    /**
     * Set how many solve steps should be run each second
     * @param logOfStepsPerSecond The logarithm of how many solve steps should be run each second
     */
    public void setStepsPerSecond(int logOfStepsPerSecond) {
        int base = 2;
        long stepsPerSecond = new Double(Math.pow(base, logOfStepsPerSecond)).longValue();
        long delay = 1000 / stepsPerSecond;
        this.milisecondDelay.set(delay);
    }
    
    /**
     * Get the millisecond delay waited at each solve step
     * @return the millisecond delay waited at each solve step
     */
    protected long getMilisecondDelay() {
        return this.milisecondDelay.get();
    }
}
