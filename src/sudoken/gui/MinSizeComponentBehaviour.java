package sudoken.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * {@code MinSizeComponentBehaviour} is a class that, when instantiated and
 * added as a {@link javax.swing.JComponent}'s
 * {@link java.awt.event.ComponentListener}, prevents the component from being
 * smaller than its minimum size. If the component becomes smaller than its
 * minimum size, {@code MinSizeComponentBehavior} resize the component to its
 * minimum size.
 * 
 * @author Kevin Doran
 * @version 1.0 15.04.2011
 */
class MinSizeComponentBehaviour extends ComponentAdapter {
    /**
     * Called when a component resizes.
     */
    @Override
    public void componentResized(ComponentEvent event) {
        final Component sourceComponent = event.getComponent();
        final Dimension minimumSize = sourceComponent.getMinimumSize();
        final int minimumWidth = new Double(minimumSize.getWidth()).intValue();
        final int minimumHeight = new Double(minimumSize.getHeight())
                .intValue();
        final int currentWidth = sourceComponent.getWidth();
        final int currentHeight = sourceComponent.getHeight();
        if (currentWidth < minimumWidth || currentHeight < minimumHeight) {
            sourceComponent.setSize(Math.max(minimumWidth, currentWidth),
                    Math.max(minimumHeight, currentHeight));
        }
    }
}
