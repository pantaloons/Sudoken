package sudoken.domain;

import sudoken.persistence.Parser;
import sudoken.solver.BacktrackingSolver;
import sudoken.solver.Solver;

public class Main {
	public static void main(String[] args) throws Exception {
		Class.forName("sudoken.extension.sudoku.Sudoku");
		Class.forName("sudoken.extension.futoshiki.Futoshiki");
		Board b = Parser.load("test/test2.csv");
		Solver s = new BacktrackingSolver();
		s.setSudokuBoard(b);
		s.solve();
		b.print();
	}
}
