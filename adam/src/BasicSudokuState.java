import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BasicSudokuState extends ISudokuState {
	
	private int size;
	private List<List<BasicCellValue>> matrix;
	
	public BasicSudokuState(int theSize) {
		if (theSize < 1 || theSize > 9) {
			//error
		}
		this.size = theSize;
		this.matrix = new ArrayList<List<BasicCellValue>>();
		for (int i = 0; i < this.size; i++) {
			this.matrix.add(new ArrayList<BasicCellValue>());
			for (int j = 0; j < this.size; j++) {
				this.matrix.get(i).add(new BasicCellValue(null));
			}
		}
	}
	
	public BasicSudokuState() {
		this(9);
	}
	
	@Override
	public void load(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		for (int i = 0; line != null && i < this.size; i++) {
			String[] row = line.split(",");
			for (int j = 0; j < row.length && j < this.size; j++) {
				row[j] = row[j].trim();
				Integer v = null;
				boolean isOriginal = false;
				try {
					v = Integer.parseInt(row[j]);
					isOriginal = true;
				}
				catch (NumberFormatException e) {}
				this.matrix.get(i).get(j).set(v, isOriginal);
			}
			line = br.readLine();
		}
	}
	
	public Integer getCellValue(int row, int col) {
		return this.matrix.get(row).get(col).getIntValue();
	}
	
	public void setCellValue(int v, int row, int col) {
		this.matrix.get(row).get(col).set(v);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void unsetCellValue(int row, int col) {
		this.matrix.get(row).get(col).set(null);
		this.setChanged();
		this.notifyObservers();
	}
	
	public boolean isOriginal(int row, int col) {
		return this.matrix.get(row).get(col).isOriginal();
	}
}