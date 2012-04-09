public class BasicSudokuSolver implements ISudokuSolver {
	
	private BasicSudokuState state;
	
	public BasicSudokuSolver(BasicSudokuState theState) {
		this.state = theState;
	}

	@Override
	public void solve() {
		findSolution(0);
	}
	
	private boolean findSolution(int pos) {
		if (pos >= 81) {
			System.exit(1);
			return false;
		}
		int col = pos % 9;
		int row = (pos - col) / 9;
		if (this.state.isOriginal(row, col)) {
			return findSolution(pos + 1);
		}
		for (int i = 1; i <= 9; i++) {
			if (isCoherent(i, row, col)) {
				this.state.setCellValue(i, row, col);
				if (findSolution(pos + 1)) {
					return true;
				}
				this.state.unsetCellValue(row, col);
			}
		}
		return false;
	}
	
	private boolean isCoherent(int v, int row, int col) {
		boolean ret = true;
		for (int i = 0; i < 9; i++) {
			if (i != row && this.state.getCellValue(i, col) == v) {
				ret = false;
			}
			if (i != col && this.state.getCellValue(row, i) == v) {
				ret = false;
			}
		}
		int gridCol = col - (col % 3);
		int gridRow = row - (row % 3);
		for (int i = gridRow; i < gridRow + 3; i++) {
			for (int j = gridCol; j < gridCol + 3; j++) {
				if (i != row && j != col) {
					if (this.state.getCellValue(i, j) == v) {
						ret = false;
					}
				}
			}
		}
		return ret;
	}
}
