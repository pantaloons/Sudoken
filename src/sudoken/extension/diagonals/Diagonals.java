package sudoken.extension.diagonals;

import java.util.HashSet;
import java.util.Set;

import sudoken.extension.Extension;

public class Diagonals extends Extension {

    public Diagonals() {
        super(new DiagonalsParser(), null, null);
    }
    
    @Override
    public Set<String> getPrerequisites() {
    	return new HashSet<String>();
    }
}