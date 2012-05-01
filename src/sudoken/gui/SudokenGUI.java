package sudoken.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

import sudoken.domain.*;
import sudoken.extension.Extension;

/**
 * Main user interface for Sudoku puzzles solvers
 * 
 * @author Joshua Leung
 * @author Kevin Doran
 */
public class SudokenGUI implements BoardChangeListener {

	/** Controller which controls this GUI */
    private Controller controller;
    
    /**Panel to contain components */
    private JPanel panel;
    /** File chooser for puzzle file selection */
    private JFileChooser fileChooser;
    /**BoardWidget to display Board*/
    private BoardWidget boardWidget;

    /** Solve Button */
    private JButton solveButton;
    /** Load Button */
    private JButton loadButton;
    /** Save Button */
    private JButton saveButton;
    /** Slider for selection of solving speed */
    private JSlider solverSpeedSlider;
    /** Slider label */
	private JLabel sliderLabel;
	
	/**
	 * Create a SudokenGUI, displaying components and a blank Board
	 */
    public SudokenGUI() {

        createComponents();
        layoutComponents();
        setIsPuzzleLoaded(false);
    }

    /**
     * Create the components of the board
     */
    private void createComponents() {
        panel = new JPanel();
        boardWidget = new BoardWidget();
        solveButton = new JButton("Solve");
        loadButton = new JButton("Load Puzzle");
        saveButton = new JButton("Save Puzzle");
        sliderLabel = new JLabel("Solve Speed: ");
        createSlider();
        createFileChooser();
        
        solveButton.addActionListener(new SolveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());
        saveButton.addActionListener(new SaveButtonListener());
    }
    
    /**
     * Create the slider
     */
    private void createSlider() {
        final int minSpeed = 0;
        final int maxSpeed = 15;
        final int initialSpeed = 5;
        solverSpeedSlider = new JSlider(JSlider.HORIZONTAL, minSpeed, maxSpeed, initialSpeed);
        solverSpeedSlider.addChangeListener(new SliderListener());
    }

    /**
     * Create the file chooser
     */
    private void createFileChooser() {
    	final FileFilter fileExtension = new FileNameExtensionFilter("Sudoken Puzzle", "sudoken");
    	final File curDir = new File("test/");
    	
    	fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(curDir);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileExtension);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    /**
     * Layout the components of the GUI
     */
    private void layoutComponents() {
        LayoutManager layout = new MigLayout("", "[][grow][]", "[]20[grow][]");        
        panel.setPreferredSize(new Dimension(500, 500)); 
        panel.setLayout(layout);
        
        panel.add(loadButton);
        panel.add(solveButton, "align center");
        panel.add(saveButton, "align right, wrap");
        
        // TODO: show the type of puzzle?
        panel.add(boardWidget, "align center, span, wrap");
        
        panel.add(sliderLabel, "split, span");
        panel.add(solverSpeedSlider, "grow, wrap");
    }

    /**
     * Set the Controller of this GUI
     * @param controller new Controller for this GUI
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Listener forthe Solver Button
     *
     */
    private class SolveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.solve();
        }
    }

    /**
     * Listener for the Load Button
     *
     */
    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int returnStatus = fileChooser.showOpenDialog(saveButton);
            if (returnStatus == JFileChooser.APPROVE_OPTION) {
            	String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                controller.loadPuzzle(fileName);
            }
        }
    }
    
    /**
     * Listener for the Slider
     *
     */
    private class SliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            controller.setSolveSpeed(solverSpeedSlider.getValue());            
        }
    }
    
    /**
     * Listener for the Save button
     *
     */
    private class SaveButtonListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
            int returnStatus = fileChooser.showSaveDialog(saveButton);
            if (returnStatus == JFileChooser.APPROVE_OPTION) {
            	String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                controller.savePuzzle(fileName);
            }
    	}
    }

    /**
     * Handle the event of a new Extension being loaded
     * @param newlyLoadedExtension
     */
    public void processNewExtension(Extension newlyLoadedExtension) {
        // add new icon for new extension (Maybe. This is not that important).
    }
    
    /** 
     * Update UI in response to changes to the board state 
     */
    public void processUpdatedBoard() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardWidget.repaint();
            }
        });
    }

    /**
     * Puts the GUI in the puzzle loaded or puzzle not-loaded state.
     * 
     * @param isLoaded
     *            the state to put the GUI in. {@code true}: the loaded state;
     *            {@code false}: the not-loaded state.
     */
    public void setIsPuzzleLoaded(final boolean isLoaded) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                solveButton.setEnabled(isLoaded);
                saveButton.setEnabled(isLoaded);
            }
        });
    }

    /**
     * Puts the GUI in the puzzle solved or puzzle not-solved state.
     * 
     * @param isSuccess
     *            the state to put the GUI in. {@code true}: the solved state;
     *            {@code false}: the not-solved state.
     */
    public void setSolved(boolean isSuccess) {
        // TODO Auto-generated method stub

    }

    /**
     * Resets the GUI. A reset GUI acts as if it has just started up.
     */
    public void reset() {
        // clear board.

    }

    /**
     * @return the JPanel containing all the GUI components.
     */
    public JPanel getPanel() {
        return panel;
    }
    
    /**
     * Set the Board to be displayed by the GUI
     * @param puzzleBoard Board to be displayed by the GUI
     */
	public void setPuzzle(Board puzzleBoard) {
		boardWidget.setBoard(puzzleBoard);
		setIsPuzzleLoaded(true);
	}
}
