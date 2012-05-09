package sudoken.gui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * A {@code JFrame} which wraps a {@code JPanel}.
 * 
 * @author Kevin Doran
 */
class WrappingFrame {
	private JFrame frame;
	
	/**
	 * Wraps a {@code JPanel} in a {@code JFrame}. For reusability, most GUI
	 * classes use JPanels to contain and layout components. This allows them to
	 * be combined into other JPanels or JFrames. When these JPanels need to be
	 * displayed, they can be wrapped in a {@code WrappingFrame}.
	 * 
	 * @param panel
	 *            the panel to wrap.
	 */
	WrappingFrame(String title, Component panel) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setMinimumSize(frame.getPreferredSize());
		frame.addComponentListener(new MinSizeComponentBehaviour());
		frame.pack();
		frame.setLocationRelativeTo(null); // centered... 
	}
	
	/**
	 * Returns the wrapping frame's {@code JFrame}.
	 * 
	 * @return the wrapping frame's {@code JFrame}.
	 */
	public JFrame getFrame() {
		return frame;
	}
}
