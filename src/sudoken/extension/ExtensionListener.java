package sudoken.extension;

/**
 * Listens for newly Loaded extensions. When a new extension is loaded, the
 * {@link ExtensionListener#processNewExtension(Extension)} method should be called. In 
 * this way, listeners will be notified of new extensions. 
 * 
 * @author Kevin Doran
 *
 */
public interface ExtensionListener {
    
    public void processNewExtension(Extension newlyLoadedExtension);
}
