import java.util.Observable;
import java.io.IOException;

public abstract class ISudokuState extends Observable {
	public abstract void load(String fileName) throws IOException;
}