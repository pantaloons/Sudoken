package sudoken.extension;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import sudoken.domain.*;
import sudoken.persistence.*;

public class ExtensionManager {
    /** Registry mapping extension identifiers to {@link Extension} instances */
    private static Map<String, Extension> m;
    
    // Name of the primary extension of the currently loaded puzzle.
    private static String currentPrimaryExtension;

    // This collection is accessed in multiple threads, thus needs to be a
    // thread safe implementation.
    private static Collection<ExtensionListener> listeners = new CopyOnWriteArrayList<ExtensionListener>();

    private static final int REFRESH_RATE_IN_SECONDS = 10;

    // called on first usage of ExtensionManager
    static {
        System.out.println("Extension Manager Starting up");

        m = new HashMap<String, Extension>();
        // TODO: Load property files
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
        if (m.containsKey(ext) == false)
        	return false;
        
        // 2) extension's prerequisites must be met at this point in time
        Extension extension = m.get(ext);
        return extension.hasPrerequisites();
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
     * Called from static initialisers of plugins when they are first loaded
     */
    public static void register(Extension ext) {
        String longName = ext.getClass().getName();
        String name = ext.getClass().getSimpleName().toLowerCase();

        System.out.format("Registering: '%s' => %s\n", name, longName);
        m.put(name, ext);
    }

    /**
     * Subscribes an ExtensionListener to new Extension updates. When a new
     * Extension is loaded, all extension listeners will be notified by having
     * their {@link ExtensionListener#processNewExtension(Extension)} method
     * called.
     * 
     * @param listener
     *            the ExtensionListener to subscribe.
     * 
     * @return {@code true} if the listener was added, {@code false} if not. A
     *         possible reasons for not being added is that the listener has
     *         already been added.
     */
    public static boolean addExtensionListener(ExtensionListener listener) {
        boolean isAdded = listeners.add(listener);
        return isAdded;
    }

    private static void notifyListeners(Extension newlyLoadedExtension) {
        for (ExtensionListener listener : listeners) {
            listener.processNewExtension(newlyLoadedExtension);
        }
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

    private static class ExtensionLoader implements Runnable {

        @Override
        public void run() {
            try {
                loadExtensions();
            } catch (InterruptedException e) {
                // Nothing needs to be cleaned up. The thread will now end.
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
     * @throws MalformedURLException
     */
    private static void loadExtensions() throws InterruptedException,
            MalformedURLException {
        File dir = new File("./plugins");
        while (true) {
            if (!dir.exists()) {
                TimeUnit.SECONDS.sleep(REFRESH_RATE_IN_SECONDS);
                continue;
            }
            System.out.println("Looking for new extensions...");
            for (File f : dir.listFiles()) {
                if (f.isFile()) {
                    int i = f.getName().lastIndexOf('.');
                    if (i >= 0 && f.getName().substring(i + 1).equals("jar")) {
                        ClassLoader cl = loadJAR(f);
                        ServiceLoader<Extension> extensionLoader = 
                        		ServiceLoader.load(Extension.class, cl);
                        
                        for (Extension newlyLoadedExtension : extensionLoader) {
                            register(newlyLoadedExtension);
                        	notifyListeners(newlyLoadedExtension);
                        }
                    }
                }
            }
            TimeUnit.SECONDS.sleep(REFRESH_RATE_IN_SECONDS);
        }
    }

    private static ClassLoader loadJAR(File f) throws MalformedURLException {
        return new URLClassLoader(new URL[] { f.toURI().toURL() });
    }
    
    public static void setCurrentPrimaryExtension(String ext) {
    	currentPrimaryExtension = ext;
    }
    
    public static String getCurrentPrimaryExtension() {
    	return currentPrimaryExtension;
    }
}
