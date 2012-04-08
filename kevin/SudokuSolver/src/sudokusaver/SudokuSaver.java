package sudokusaver;

import java.nio.file.Path;

import sudokuboard.SudokuBoard;

public interface SudokuSaver {

    void save(SudokuBoard board);
    SudokuBoard load(Path boardFilePath);
}
