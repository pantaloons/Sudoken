package sudoken.gui;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.Timer;

import sudoken.domain.Board;
import sudoken.domain.Solver;
import sudoken.extension.NoMatchingExtensionException;
import sudoken.persistence.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller is the Controller part of the MVC pattern, and controls all GUI behaviour.
 *
 * @author Tim Hobbs
 */
public class Controller {
	
	/** Puzzle solver used by this controller */
	private Solver puzzleSolver;
	/** Popup for displaying errors */
	private ErrorDisplay errorDisplay = new PopupErrorDisplay();
	/** Main GUI */
	private SudokenGUI gui;
	/** Is the solver currently running? */
	private boolean solverRunning;
	/** Timer for updates */
	private Timer guiUpdateTimer;
	/** Thread to run Solver in */
	private Thread solverThread;

	private boolean solverPaused = false;
	
	/**
	 * Create a Controller
	 */
	private Controller() {
		super();
		guiUpdateTimer = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				gui.processUpdatedBoard();
			}
		});
		guiUpdateTimer.stop();
		
	}
	
	/**
	 * Creates a Controller. The controller is used by the given GUI to
	 * control the given puzzle solver.
	 * 
	 * @param puzzleSolver
	 *            the puzzle solver to use to solve sudoku-like puzzles.
	 * @param gui
	 *            the GUI that is being used to interface with the user and take
	 *            user input.
	 * @return the created Controller
	 */
	// A normal constructor could not be used, as
	public static Controller createController(Solver puzzleSolver, SudokenGUI gui) {
		Controller controller = new Controller();
		controller.setGUI(gui);
		gui.setController(controller);
		puzzleSolver.addListener(gui);
		controller.setSolver(puzzleSolver);
		return controller;
	}
	
	/**
	 * Set the GUI for the Controller to control
	 * @param gui GUI to control
	 */
	private void setGUI(SudokenGUI gui) {
		this.gui = gui;
		errorDisplay.setParentComponent(gui.getPanel());
		gui.setSolverPaused(solverPaused);
	}
	
	/**
	 * Set the Solver for this Controller to use
	 * @param puzzleSolver Solver to use
	 */
	private void setSolver(Solver puzzleSolver) {
		this.puzzleSolver = puzzleSolver;
	}
	
	/**
	 * Load a puzzle from a file 
	 * @param fileName
	 */
	public void loadPuzzle(String fileName) {
		stopSolver();
		File puzzleFile = new File(fileName);
		Board puzzleBoard;
		if (!puzzleFile.canRead()) {
			errorDisplay
			.showErrorMessage("The choosen file cannot be read. "
					+ "This program may not have sufficient privilages to read "
					+ "this file.");
			return;
		}
		
		try {
			puzzleBoard = Parser.load(puzzleFile);
		} 
		catch (IOException e) {
			errorDisplay
			.showErrorMessage("An error occurred while trying to read "
					+ "the file. The file was opened successfully, but "
					+ "reading the file contents failed.");
			e.printStackTrace();
			return;
		} 
		catch (NoMatchingExtensionException e) {
			errorDisplay.showErrorMessage("No sudoku extension could be found "
					+ "which can read the given file's format.");
			e.printStackTrace();
			return;
		} 
		catch (ParseException e) {
			errorDisplay
			.showErrorMessage("An error occurred while trying to parse "
					+ "the file. The file was opened successfully and a "
					+ "sudoku extension was found to read the file; "
					+ "however, there is an error in the files syntax.");
			e.printStackTrace();
			return;
		}
		puzzleSolver.setSudokuBoard(puzzleBoard);
		gui.setPuzzle(puzzleBoard);
	}
	
	/**
	 * Save a puzzle to a file
	 * @param fileName filename of file to save to
	 */
	public void savePuzzle(String fileName) {
		File saveFile = new File(fileName);
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} 
			catch (IOException e) {
				errorDisplay.showErrorMessage("An error occurred while trying to create "
						+ "a new file.");
				return;
			}
		}
		if (!saveFile.canWrite()) {
			errorDisplay.showErrorMessage("The file is unable to be written to.");
			return;
		}
		try {
			Parser.save(puzzleSolver.getSudokuBoard(), saveFile);
		} 
		catch (IOException e) {
			errorDisplay.showErrorMessage("An error occurred while trying to write "
					+ "to the file. The file was opened successfully, but "
					+ "writing the file contents failed.");
		} 
		catch (ParseException e) {
			errorDisplay.showErrorMessage("An error occurred while trying to convert "
					+ "the loaded puzzle to its save file format.");
		}
		// TODO: Save feedback in GUI.
	}
	
	/**
	 * Solve the current puzzle, running the solver in a new thread
	 */
	public void solve() {
		solverPaused = false;
		gui.setSolverPaused(solverPaused);
		if (!solverRunning) {
			solverRunning = true;
			guiUpdateTimer.start();
			Runnable runSolver = new Runnable() {
				public void run() {
					try {
						puzzleSolver.solve();
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
						// continue. Just set the gui to false state.
					}
					solverRunning = false;
					guiUpdateTimer.stop();
					
				}
			};
			solverThread = new Thread(runSolver);
			solverThread.start();
		}
		
		// show message;
	}
	
	/**
	 * Stop the Solver
	 */
	private void stopSolver() {
		if (solverThread != null && solverThread.getState() != Thread.State.TERMINATED) {
			puzzleSolver.resume();
			puzzleSolver.stop();
			try {
				
				solverThread.join();
				while (solverThread.getState() != Thread.State.TERMINATED) {
					Thread.sleep(100);
				}
				
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}

		}
	}
	
	/** 
	 * Set the solve speed of the solver
	 * @param value solve speed
	 */
	public void setSolveSpeed(int value) {
		puzzleSolver.setStepsPerSecond(value);
	}

	/**
	 * Toggle the pause state of the solver
	 */
	public void togglePause() {
		if (solverPaused) puzzleSolver.resume();
		else puzzleSolver.pause();
		
		solverPaused = !solverPaused;
		
		gui.setSolverPaused(solverPaused);
		
	}
	
}
