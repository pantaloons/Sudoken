package sudoken.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

import sudoken.domain.*;
import sudoken.extension.Extension;
import sudoken.gui.controller.Controller;
import sudoken.gui.util.LabelledFileChooser;
import sudoken.solver.BoardChangeListener;

/**
 * Main user interface for Sudoku puzzles solvers
 * 
 * @author Joshua Leung & Kevin Doran
 */
public class SudokenGUI implements BoardChangeListener {

    private Controller controller;
    private JPanel panel;
    private LabelledFileChooser labelledFileChooser;
    private BoardWidget boardWidget;

    private JButton solveButton;
    private JButton loadButton;
    private JProgressBar progressBar;

    // FIXME: this should eventually contain the body of the other again
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
        panel.add(progressBar, "align center, span, growx");
        panel.setPreferredSize(new Dimension(500, 500)); 
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
    
    public void processUpdatedBoard(final Board solvedBoard) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardWidget.updateUI(solvedBoard);
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
}
