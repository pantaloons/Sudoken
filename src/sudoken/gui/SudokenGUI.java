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

    private Controller controller;
    private JPanel panel;
    private JFileChooser fileChooser;
    private BoardWidget boardWidget;

    private JButton solveButton;
    private JButton loadButton;
    private JButton saveButton;
	private JSlider solverSpeedSlider;
	private JLabel sliderLabel;
	
    public SudokenGUI() {

        createComponents();
        layoutComponents();
        setIsPuzzleLoaded(false);
    }

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
    
    private void createSlider() {
        final int minSpeed = 0;
        final int maxSpeed = 15;
        final int initialSpeed = 5;
        solverSpeedSlider = new JSlider(JSlider.HORIZONTAL, minSpeed, maxSpeed, initialSpeed);
        solverSpeedSlider.addChangeListener(new SliderListener());
    }

    private void createFileChooser() {
    	final FileFilter fileExtension = new FileNameExtensionFilter("Sudoken Puzzle", "sudoken");
    	final File curDir = new File("../test");
    	
    	fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(curDir);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileExtension);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

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

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private class SolveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.solve();
        }
    }

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
    
    private class SliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            controller.setSolveSpeed(solverSpeedSlider.getValue());            
        }
    }
    
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

    public void processNewExtension(Extension newlyLoadedExtension) {
        // add new icon for new extension (Maybe. This is not that important).
    }
    
    /* update UI in response to changes to the board state */
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
    
	public void setPuzzle(Board puzzleBoard) {
		boardWidget.setBoard(puzzleBoard);
		setIsPuzzleLoaded(true);
	}
}
