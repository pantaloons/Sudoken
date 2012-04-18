package sudoken.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

import sudoken.domain.*;
import sudoken.extension.Extension;
import sudoken.persistence.Parser;
import sudoken.solver.BacktrackingSolver;
import sudoken.solver.Solver;

/**
 * Main user interface for Sudoku puzzles solvers
 * 
 * @author Joshua Leung & Kevin Doran
 */
public class SudokenGUIImp implements SudokenGUI {
    private static final long serialVersionUID = -4755160282006524528L;

    private Controller controller;
    private JPanel panel;
    private LabelledFileChooser fileChooser;

    private JButton solveButton;
    private JButton loadButton;

    // FIXME: this should eventually contain the body of the other again
    public SudokenGUIImp() {

        // setIconImage(new
        // ImageIcon(ClassLoader.getSystemResource("icon.png")).getImage());

        createComponents();
        layoutComponents();
        setIsPuzzleLoaded(false);
    }
    
    private void createComponents() {
        panel = new JPanel();
        fileChooser = new LabelledFileChooser("Browse");
        FileFilter fileExtension = new FileNameExtensionFilter(
                "Sudoken Config", "sudoken");
        fileChooser.getFileChooser().addChoosableFileFilter(fileExtension);
        fileChooser.getFileChooser().setFileSelectionMode(
                JFileChooser.FILES_ONLY);

        solveButton = new JButton("Solve");
        loadButton = new JButton("Load Puzzle");
        solveButton.addActionListener(new SolveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());
    }
    
    private void layoutComponents() {
        LayoutManager layout = new MigLayout("", "[grow][]");
        panel.setLayout(layout);
        panel.add(fileChooser, "grow");
        panel.add(loadButton);
        panel.add(solveButton, "wrap");
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
            String fileName = fileChooser.getTextField().getText();
            controller.loadPuzzle(fileName);
        }
        
    }

    @Override
    public void processNewExtension(Extension newlyLoadedExtension) {
        // add new icon for new extension (Maybe. This is not that important).
        
    }

    @Override
    public void processSolvedBoard(Board solvedBoard) {
        //
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

// I don't think it is even possible to have progress for a backtracking solver. If there is a new solver,
// then maybe this is useful.
//
//private JProgressBar progressbar;
//
///* Status panel */
//private JPanel setup_statusPanel() {
//  /* panel settings */
//  JPanel pnl = new JPanel();
//  pnl.setBorder(BorderFactory.createEmptyBorder(4, 2, 2, 2));
//  pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
//
//  /* progressbar - 75% */
//  progressbar = new JProgressBar();
//  pnl.add(progressbar);
//
//  /* labels - 20% */
//  status_label = new JLabel("Load puzzle to begin...");
//  status_label.setBorder(BorderFactory.createEmptyBorder(2, 7, 2, 3));
//  pnl.add(status_label);
//
//  return pnl;
//}
