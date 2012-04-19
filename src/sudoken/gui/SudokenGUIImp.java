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

/**
 * Main user interface for Sudoku puzzles solvers
 * 
 * @author Joshua Leung & Kevin Doran
 */
public class SudokenGUIImp implements SudokenGUI {

    private Controller controller;
    private JPanel panel;
    private LabelledFileChooser labelledFileChooser;
    private BoardWidget boardWidget;

    private JButton solveButton;
    private JButton loadButton;

    // FIXME: this should eventually contain the body of the other again
    public SudokenGUIImp() {

        createComponents();
        layoutComponents();
        setIsPuzzleLoaded(false);
    }

    private void createComponents() {
        panel = new JPanel();
        boardWidget = new BoardWidget();
        solveButton = new JButton("Solve");
        loadButton = new JButton("Load Puzzle");
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
        LayoutManager layout = new MigLayout("", "[grow][]", "[]20[grow]");
        panel.setLayout(layout);
        panel.add(labelledFileChooser, "growx");
        panel.add(loadButton);
        panel.add(solveButton, "wrap");
        panel.add(boardWidget, "align center, span");
        panel.setPreferredSize(new Dimension(500, 500)); 
    }

    @Override
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

    @Override
    public void processNewExtension(Extension newlyLoadedExtension) {
        // add new icon for new extension (Maybe. This is not that important).
    }

    @Override
    public void processSolvedBoard(final Board solvedBoard) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                boardWidget.setBoard(solvedBoard);
            }
        });
    }

    @Override
    public void setIsPuzzleLoaded(final boolean isLoaded) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                solveButton.setEnabled(isLoaded);
            }
        });
    }

    @Override
    public void setSolved(boolean isSuccess) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reset() {
        // clear board.

    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}

// I don't think it is even possible to have progress for a backtracking solver.
// If there is a new solver,
// then maybe this is useful.
//
// private JProgressBar progressbar;
//
// /* Status panel */
// private JPanel setup_statusPanel() {
// /* panel settings */
// JPanel pnl = new JPanel();
// pnl.setBorder(BorderFactory.createEmptyBorder(4, 2, 2, 2));
// pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
//
// /* progressbar - 75% */
// progressbar = new JProgressBar();
// pnl.add(progressbar);
//
// /* labels - 20% */
// status_label = new JLabel("Load puzzle to begin...");
// status_label.setBorder(BorderFactory.createEmptyBorder(2, 7, 2, 3));
// pnl.add(status_label);
//
// return pnl;
// }
