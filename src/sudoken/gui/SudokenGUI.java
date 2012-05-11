package sudoken.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;

import net.miginfocom.swing.MigLayout;

import sudoken.domain.*;

/**
 * Main user interface for Sudoku puzzles solvers
 * 
 * @author Joshua Leung
 * @author Kevin Doran
 */
class SudokenGUI implements BoardChangeListener {

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
    
    //private JButton pauseButton;
    
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
        //pauseButton = new JButton("Pause");
        saveButton = new JButton("Save Puzzle");
        sliderLabel = new JLabel("Solve Speed: ");
        createSlider();
        createFileChooser();
        
        solveButton.addActionListener(new SolveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());
        saveButton.addActionListener(new SaveButtonListener());
        //pauseButton.addActionListener(new PauseButtonListener());
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
        
        //Create pause button with fixed width so it doesn't resize when changed to resume
        /*panel.add(pauseButton);
        Dimension pauseButtonDimens = pauseButton.getSize();
        pauseButtonDimens.width = 100;
        pauseButton.setPreferredSize(pauseButtonDimens);*/
        
        
        panel.add(saveButton, "align right, wrap");
        
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
     * Listener for the Pause button
     *
     */
    private class PauseButtonListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
            controller.togglePause();
    	}
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
    
    public void setIsSolverPaused(boolean solverRunning, boolean solverPaused){
    	if (solverRunning) {
	    	if (solverPaused) {
	    		solveButton.setText("Resume");
	    	}
	    	else {
	    		solveButton.setText("Pause");
	    	}
    	}
    	else {
    		solveButton.setText("Solve");
    	}
    	//System.out.println("Solver is paused: " + solverPaused);
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
