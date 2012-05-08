package sudoken.extension;

/**
 * Listens for newly Loaded extensions. When a new extension is loaded, the
 * {@link ExtensionListener#processNewExtension(Extension)} method should be
 * called. In this way, listeners will be notified of new extensions.
 * 
 * @author Kevin Doran
 * 
 */
interface ExtensionListener {

	/**
	 * Act on a newly loaded Extension
	 * @param newlyLoadedExtension the newly loaded Extension 
	 */
    public void processNewExtension(Extension newlyLoadedExtension);
}
