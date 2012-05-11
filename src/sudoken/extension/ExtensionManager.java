package sudoken.extension;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import sudoken.domain.*;
import sudoken.persistence.*;

/**
 * 
 * ExtensionManager provides the various components required to load, solve, and render Puzzles.
 * ExtensionManager is also used to keep track of Extensions in a given folder, periodically checking for changes.
 *
 */
public class ExtensionManager {
    /** Registry mapping extension identifiers to {@link Extension} instances */
    private static Map<String, Extension> m;
    
    /** Name of the primary extension of the currently loaded puzzle. */
    private static String currentPrimaryExtension;

    /**
     * Refresh rate of the Extension scanner
     */
    private static final int REFRESH_RATE_IN_SECONDS = 2;

    // called on first usage of ExtensionManager
    static {
        //System.out.println("Extension Manager Starting up");

        m = new HashMap<String, Extension>();
    }
    
    /**
     * Check if the ExtensionManager has a set of Extensions
     * @param exts Set of Extensions to check for
     * @return True if ExtensionManager has all of the Extensions in the Set, False otherwise.
     */
    private static boolean hasAllExtensions(Set<String> exts) {
    	for(String s : exts) {
    		if(!m.containsKey(s) || !hasExtension(s)) return false;
    	}
    	return true;
    }

    /**
     * Checks if the named extension is currently registered with us
     * 
     * @param ext
     *            Identifier for extension. Lower-case.
     * @return {@code true} if a matching extension exists/can be used
     */
    public static boolean hasExtension(String ext) {
    	// 1) extension must be registered
        if (!m.containsKey(ext)) return false;
        
        // 2) extension's prerequisites must be met at this point in time
        Extension e = m.get(ext);
        return hasAllExtensions(e.getPrerequisites());
    }

    /**
     * Get parser for extension-specific constraints found in input files
     * 
     * @param ext
     *            Identifier for extension. Lower-case.
     * @Precondition Assumes that <code>hasExtension()</code> has been called
     *               and was successful
     */
    public static SectionParser getParser(String ext) {
        return m.get(ext).getParser();
    }
    
    /**
     * Checks if the named extension supplies a parser.
     * 
     * @param ext
     *             Identifier for extension. Lower-case.
     * @return {@code true} if the extension has a parser
     */
    public static boolean hasParser(String ext) {
    	return m.get(ext).getParser() != null;
    }

    /**
     * Get factory creating a {@link sudoken.domain.Board} from description
     * given in file
     * 
     * @param ext
     *            Identifier for extension. Lower-case.
     * @Precondition Assumes that <code>hasExtension()</code> has been called
     *               and was successful
     */
    public static BoardCreator getConstructor(String ext) {
        return m.get(ext).getCreator();
    }
    
    /**
     * Get decorator for an extension.
     * 
     * @param ext
     *            Identifier for extension. Lower-case.
     * @Precondition Assumes that <code>hasExtension()</code> has been called
     *               and was successful
     */
	public static BoardDecorator getDecorator(String ext) {
		BoardDecorator decorator = null;
		try {
			decorator = m.get(ext).getDecorator().getClass().newInstance();
		} 
        catch (InstantiationException e) {
			e.printStackTrace();
		} 
        catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return decorator;
	}

    /**
     * Called from static initialisers of plugins when they are first loaded
     */
    private static void register(Extension ext) {
        //String longName = ext.getClass().getName();
        String name = ext.getClass().getSimpleName().toLowerCase();

        //System.out.format("Registering: '%s' => %s\n", name, longName);
        m.put(name, ext);
    }

    /**
     * Starts the extension loading process. The class path is checked
     * continuously for extensions, and if found, they are loaded. When an
     * extension is loaded, all interested components are notified of the newly
     * loaded extension.
     */
    public static void startLoadingExtensions() {
        // This was chosen to be a public method rather than in the static
        // initialisation block,
        // as starting threads in constructors/initialisation blocks in
        // generally regarded as
        // poor use of concurrency (Goetz et al, 2006).
        Runnable extensionLoader = new ExtensionLoader();
        Thread loadingThread = new Thread(extensionLoader);
        loadingThread.start();
    }

    /**
     * Extension Loader is used to poll for Extensions 
     */
    private static class ExtensionLoader implements Runnable {
        @Override
        public void run() {
            try {
                loadExtensions();
            }
            catch (InterruptedException e) {
                // Nothing needs to be cleaned up. The thread will now end.
            }
        }

    }

    /**
     * Continuously loads new extensions. This method does not return unless
     * interrupted. A ServiceLoader is used to load the extensions. The
     * ServiceLoader will load any extensions it finds on the class path. Any
     * subscribed ExtensionListeners will be notified of newly loaded
     * extensions.
     * 
     * @throws InterruptedException
     */
    private static void loadExtensions() throws InterruptedException {
		URLClassLoader ucl = new URLClassLoader(new URL[]{});
        File dir = new File("./plugins");
        ServiceLoader<Extension> extensionLoader = 
            	ServiceLoader.load(Extension.class, ucl);
        
        while (true) {
            if (!dir.exists()) {
                TimeUnit.SECONDS.sleep(REFRESH_RATE_IN_SECONDS);
                continue;
            }
            //System.out.println("Looking for new extensions...");
            for (File f : dir.listFiles()) {
                if (f.isFile()) {
                    int i = f.getName().lastIndexOf('.');
                    if (i >= 0 && f.getName().substring(i + 1).equals("jar")) {
                        loadJAR(f, ucl);
                    }
                }
            }
            
            for (Extension ext : extensionLoader) {
            	register(ext);
            }
            TimeUnit.SECONDS.sleep(REFRESH_RATE_IN_SECONDS);
        }
    }

    /**
     * Load a JAR file
     * @param f File representing the JAR file
     * @param ul The classloader to load into
     */
    private static void loadJAR(File f, URLClassLoader ul) {
		try {
			Method m = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
			m.setAccessible(true);
			try {
				m.invoke(ClassLoader.getSystemClassLoader(), new Object[]{ f.toURI().toURL() });
			}
			catch(MalformedURLException e) {
				e.printStackTrace();
			}
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
    }
    
    /**
     * Set the current primary Extension name
     * @param ext current primary Extension name
     */
    public static void setCurrentPrimaryExtension(String ext) {
    	currentPrimaryExtension = ext;
    }
    
    /**
     * Get the current primary Extension name
     * @return the current primary Extension name
     */
    public static String getCurrentPrimaryExtension() {
    	return currentPrimaryExtension;
    }
}
