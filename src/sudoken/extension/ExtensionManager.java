package sudoken.extension;

import java.util.HashMap;
import java.util.Map;

import sudoken.domain.*;
import sudoken.persistence.*;

public class ExtensionManager {
	private static boolean inited = false;
	private static Map<String, Extension> m;
	
	private static void init() {
		m = new HashMap<String, Extension>();
		inited = true;
	}
	
	public static boolean hasExtension(String ext) {
		if(!inited) init();
		return m.containsKey(ext);
	}
	
	public static SectionParser getParser(String ext) {
		if(!inited) init();
		return m.get(ext).getParser();
	}
	
	public static BoardCreator getConstructor(String ext) {
		if(!inited) init();
		return m.get(ext).getCreator();
	}

	public static void register(String name, Extension ext) {
		if(!inited) init();
		System.out.println("Registering: " + name);
		m.put(name, ext);
	}
}
