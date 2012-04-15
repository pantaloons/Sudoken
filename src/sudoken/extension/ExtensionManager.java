package sudoken.extension;

import java.util.HashMap;
import java.util.Map;

import sudoken.domain.*;
import sudoken.persistence.*;

public class ExtensionManager {
	/** Registry mapping extension identifiers to {@link Extension} instances */
	private static Map<String, Extension> m;
	
	// called on first usage of ExtensionManager
	static {
		System.out.println("Extension Manager Starting up");
		
		m = new HashMap<String, Extension>();
		//TODO: Load property files and locate plugins
	}
	
	/**
	 * Checks if the named extension is currently registered with us
	 * @param ext   Identifier for extension. Lower-case.
	 * @return      <code>true</code> if a matching extension exists/can be used 
	 */
	public static boolean hasExtension(String ext) {
		return m.containsKey(ext);
	}
	
	/**
	 * Get parser for extension-specific constraints found in input files
	 * @param ext    Identifier for extension. Lower-case.
	 * @Precondition Assumes that <code>hasExtension()</code> has been called and was successful 
	 */
	public static SectionParser getParser(String ext) {
		return m.get(ext).getParser();
	}
	
	/**
	 * Get factory creating a {@link sudoken.domain.Board} from description given in file 
	 * @param ext    Identifier for extension. Lower-case.
	 * @Precondition Assumes that <code>hasExtension()</code> has been called and was successful 
	 */
	public static BoardCreator getConstructor(String ext) {
		return m.get(ext).getCreator();
	}
	
	/**
	 * Called from static initialisers of plugins when they are first loaded
	 */
	public static void register(Extension ext) {
		String longName = ext.getClass().getName();
		String name = ext.getClass().getSimpleName().toLowerCase();
		
		System.out.format("Registering: '%s' => %s\n", name, longName);
		m.put(name, ext);
	}
}
