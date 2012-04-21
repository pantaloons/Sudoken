package sudoken.gui.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import sudoken.domain.Board;
import sudoken.extension.NoMatchingExtensionException;
import sudoken.gui.SudokenGUI;
import sudoken.gui.util.errordisplay.ErrorDisplay;
import sudoken.gui.util.errordisplay.PopupErrorDisplay;
import sudoken.parser.Parser;
import sudoken.solver.Solver;

public class Controller {

    private Solver puzzleSolver;
    private ErrorDisplay errorDisplay = new PopupErrorDisplay();
    private SudokenGUI gui;

    private Controller() {
        super();
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
    public static Controller createController(Solver puzzleSolver,
            SudokenGUI gui) {
        Controller controller = new Controller();
        controller.setGUI(gui);
        gui.setController(controller);
        puzzleSolver.addListener(gui);
        controller.setSolver(puzzleSolver);
        return controller;
    }

    private void setGUI(SudokenGUI gui) {
        this.gui = gui;
        errorDisplay.setParentComponent(gui.getPanel());
    }

    private void setSolver(Solver puzzleSolver) {
        this.puzzleSolver = puzzleSolver;
    }

    public void loadPuzzle(String fileName) {
        File puzzleFile = new File(fileName);
        if (!puzzleFile.canRead()) {
            errorDisplay
                    .showErrorMessage("The choosen file cannot be read. "
                            + "This program may not have sufficient privilages to read "
                            + "this file.");
            return;
        }

        try {
            Board puzzleBoard = Parser.load(puzzleFile);
            puzzleSolver.setSudokuBoard(puzzleBoard);
        } catch (IOException e) {
            errorDisplay
                    .showErrorMessage("An error occurred while trying to read "
                            + "the file. The file was opened successfully, but "
                            + "reading the file contents failed.");
            e.printStackTrace();
            return;
        } catch (NoMatchingExtensionException e) {
            errorDisplay.showErrorMessage("No sudoku extension could be found "
                    + "which can read the given file's format.");
            e.printStackTrace();
            return;
        } catch (ParseException e) {
            errorDisplay
                    .showErrorMessage("An error occured while trying to parse "
                            + "the file. The file was opened successfully and a "
                            + "sudoku extension was found to read the file; "
                            + "however, there is an error in the files syntax.");
            e.printStackTrace();
            return;
        }
        gui.setIsPuzzleLoaded(true);
    }

    public void solve() {
        boolean isSolved = puzzleSolver.solve();
        // show message;
    }

}
