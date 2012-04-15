import java.io.IOException;
import java.util.Observer;
import java.util.Observable;

public class BasicSudokuCommandUI implements ISudokuUI, Observer {

	private BasicSudokuState state;
	private BasicSudokuSolver solver;
	
	public BasicSudokuCommandUI() {
		this.state = new BasicSudokuState();
		this.state.addObserver(this);
		this.solver = new BasicSudokuSolver(state);
	}
	
	public BasicSudokuCommandUI(String fileName) {
		this();
		try {
			this.state.load(fileName);
		}
		catch (IOException e) {
		}
	}
	
	@Override
	public void updateDisplay() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int v = this.state.getCellValue(i, j);
				if (v == 0) {
					System.out.print("  ");
				}
				else {
					System.out.print(this.state.getCellValue(i, j) + " ");
				}
				if (j == 2 || j == 5) {
					System.out.print("| ");
				}
			}
			System.out.println();
			if (i == 2 || i == 5) {
				System.out.println("------+-------+------ ");
			}
		}
		System.out.println();
	}
	
	public void solve() {
		this.solver.solve();
	}

	@Override
	public void updateCell(int row, int col, ICellValue value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o.equals(this.state)) {
			this.updateDisplay();
		}
	}
	
	public static void main(String args[]) {
		BasicSudokuCommandUI game = new BasicSudokuCommandUI("test.dat");
		game.updateDisplay();
		game.solve();
		game.updateDisplay();
	}

}
