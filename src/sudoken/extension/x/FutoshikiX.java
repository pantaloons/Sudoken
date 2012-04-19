package sudoken.extension.x;

import sudoken.extension.*;

public class FutoshikiX extends Extension
{
	static {
		ExtensionManager.register(new FutoshikiX());
	}
	
	public FutoshikiX()
	{
		super(null, new XCreator("futoshiki"));
	}
	
	@Override
	public boolean hasPrerequisites()
	{
		// depends on standard futoshiki
		return ExtensionManager.hasExtension("futoshiki");
	}
}
