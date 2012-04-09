package manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;

import extension.SudokuExtension;

public class ExtensionManagerImp implements ExtensionManager {

    private Collection<SudokuExtension> extensions = new HashSet<>();
    private ServiceLoader<SudokuExtension> extensionLoader = ServiceLoader.load(SudokuExtension.class);
    
    public ExtensionManagerImp() {}
    
    @Override
    public Collection<SudokuExtension> getExtensions() {
        // do some checking?
        return extensions;
    }
    
    private void checkForNewExtension() {
        Iterator<SudokuExtension> extensionIterator = extensionLoader.iterator();
        while(extensionIterator.hasNext()) {
            boolean isNew = extensions.add(extensionIterator.next());
            if(isNew) {
                notifyListeners();
            }
        }
    }
    
    private void notifyListeners() {
        // TODO Auto-generated method stub
        
    }
}
