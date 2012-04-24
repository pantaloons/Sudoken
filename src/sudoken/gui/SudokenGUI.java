package sudoken.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

import sudoken.domain.*;
import sudoken.extension.Extension;

/**
 * Main user interface for Sudoku puzzles solvers
 * 
 * @author Joshua Leung & Kevin Doran
 */
public class SudokenGUI implements BoardChangeListener, ActionListener {

    private Controller controller;
    private JPanel panel;
    private LabelledFileChooser labelledFileChooser;
    private BoardWidget boardWidget;

    private JButton solveButton;
    private JButton loadButton;
    private JProgressBar progressBar;
	private JMenuBar menuBar;
	private JMenu fileMenu, settingsMenu, helpMenu;

    // FIXME: this should eventually contain the body of the other again
    public SudokenGUI() {
        createComponents();
        layoutComponents();
        createMenu();
        setIsPuzzleLoaded(false);
    }

    private void createComponents() {
        panel = new JPanel();
        boardWidget = new BoardWidget();
        solveButton = new JButton("Solve");
        loadButton = new JButton("Load Puzzle");
        progressBar = new JProgressBar();
        solveButton.addActionListener(new SolveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());
        createFileChooser();
    }

    private void createFileChooser() {
        labelledFileChooser = new LabelledFileChooser("Browse");
        FileFilter fileExtension = new FileNameExtensionFilter("Sudoken Config", "sudoken");
        labelledFileChooser.getFileChooser().setAcceptAllFileFilterUsed(false);
        labelledFileChooser.getFileChooser().addChoosableFileFilter(fileExtension);
        labelledFileChooser.getFileChooser().setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    private void layoutComponents() {
        LayoutManager layout = new MigLayout("", "[grow][]", "[]20[grow][]");
        panel.setLayout(layout);
        panel.add(labelledFileChooser, "growx");
        panel.add(loadButton);
        panel.add(solveButton, "wrap");
        panel.add(boardWidget, "align center, span, wrap");
        panel.add(progressBar, "span, growx");
        panel.setPreferredSize(new Dimension(500, 500)); 
    }
    
    
    private void createMenu()
    {
    	menuBar = new JMenuBar();
    	
    	fileMenu = new JMenu("File");
    	JMenuItem menuOpen = new JMenuItem("Open Puzzle");
    	menuOpen.setActionCommand("menu_open");
    	menuOpen.addActionListener(this);
    	JMenuItem menuExit = new JMenuItem("Exit");
    	menuExit.setActionCommand("menu_exit");
    	menuExit.addActionListener(this);
    	fileMenu.add(menuOpen);
    	fileMenu.add(menuExit);
    	
    	settingsMenu = new JMenu("Settings");
    	JMenuItem menuDisplay = new JMenu("Display Plugin");
    	JMenuItem menuDisplayPlugin = new JMenuItem("Example Plugin");
    	menuDisplay.add(menuDisplayPlugin);
    	settingsMenu.add(menuDisplay);
    	
    	
    	helpMenu = new JMenu("Help");
    	JMenuItem menuAbout = new JMenuItem("About");
    	helpMenu.add(menuAbout);
    	
    	
    	menuBar.add(fileMenu);
    	menuBar.add(settingsMenu);
    	menuBar.add(helpMenu);
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
            String fileName = labelledFileChooser.getTextField().getText();
            controller.loadPuzzle(fileName);
        }
    }

    public void processNewExtension(Extension newlyLoadedExtension) {
        // add new icon for new extension (Maybe. This is not that important).
    }
    
    public void processUpdatedBoard() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            //TODO: Board change listener should be implemented by board, not solver
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
    
    public JMenuBar getMenuBar(){
    	return menuBar;
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String command = event.getActionCommand();
		
		if ("menu_exit".equals(command)){
			System.exit(0);
		
		} else if ("menu_open".equals(command)){
			labelledFileChooser.getFileChooser().showOpenDialog(panel);
			controller.loadPuzzle(labelledFileChooser.getFileChooser().getSelectedFile().getAbsolutePath() );
		}
	}

	public void setPuzzle(Board puzzleBoard) {
		boardWidget.setBoard(puzzleBoard);
		setIsPuzzleLoaded(true);
	}
    
}
