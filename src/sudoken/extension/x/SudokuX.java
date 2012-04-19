package sudoken.extension.x;

import sudoken.extension.Extension;
import sudoken.extension.ExtensionManager;

public class SudokuX extends Extension
{
	static {
		ExtensionManager.register(new SudokuX());
	}
	
	public SudokuX()
	{
		super(null, new XCreator("sudoku"));
	}
	
	@Override
	public boolean hasPrerequisites()
	{
		// depends on standard sudoku
		return ExtensionManager.hasExtension("sudoku");
	}
}
