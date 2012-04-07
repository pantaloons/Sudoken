import java.util.*;
import java.io.*;

class Painter {
	static void paint(Sudoku s) {
		for(int i = 0; i < s.getHeight(); i++) {
			for(int j = 0; j < s.getWidth(); j++) {
				int val = s.getCell(j, i);
				if(val == 0) System.out.print("  ");
				else System.out.print(val + " ");
			}
			System.out.println();
		}
	}
}

class Loader {
	static Sudoku loadCSV(String file) throws IOException {
		Scanner sc = new Scanner(new File(file));
		int width = sc.nextInt(), height = sc.nextInt();
		Sudoku s = new Sudoku(width, height);
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				s.setCell(j, i, sc.nextInt());
			}
		}
		sc.close();
		return s;
	}
	static void saveCSV(Sudoku s, String csvFile) {
	}
}

class Sudoku {
	private int width, height;
	private int[][] cells;
	public Sudoku(int width, int height) {
		this.width = width;
		this.height = height;
		this.cells = new int[height][width];
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getCell(int x, int y) {
		return cells[y][x];
	}
	public void setCell(int x, int y, int val) {
		cells[y][x] = val;
	}
	public boolean solve() {
		return solveR(-1, 0);
	}
	private boolean violated(int x, int y) {
		for(int i = 0; i < 9; i++) {
			if(i != x && cells[y][i] == cells[y][x]) return true;
			if(i != y && cells[i][x] == cells[y][x]) return true;
		}
		int xx = 3 * (x / 3), yy = 3 * (y / 3);
		for(int i = yy ; i < yy + 3; i++) {
			for(int j = xx; j < xx + 3; j++) {
				if(i == y && j == x) continue;
				if(cells[i][j] == cells[y][x]) return true;
			}
		}
		return false;
	}
	private boolean solveR(int x, int y) {
		int xx = 0, yy;
		outer: for(yy = 0; yy < height; yy++) {
			for(xx = 0; xx < width; xx++) {
				if(yy < y || (yy == y && xx <= x)) continue;
				if(cells[yy][xx] == 0) break outer;
			}
		}
		if(xx == width && yy == height) return true;
		
		for(int i = 1; i <= 9; i++) {
			cells[yy][xx] = i;
			if(violated(xx, yy)) continue;
			if(solveR(xx, yy)) return true;
		}
		cells[yy][xx] = 0;
		return false;
	}
}

public class Test {
	public static void main(String[] args) throws IOException {
		Sudoku s = Loader.loadCSV("test.csv");
		Painter.paint(s);
		s.solve();
		Painter.paint(s);
	}
}
