package sudoken.extension;

import java.util.Set;

import sudoken.domain.BoardCreator;
import sudoken.domain.BoardDecorator;
import sudoken.persistence.SectionParser;

/**
 * 
 * An Extension provides any combination of a SectionParser, BoardCreator or BoardDecorator.
 * Extension is designed for dynamic loading, and may rely on other Extensions.
 *
 */
public abstract class Extension {
	/**
	 * SectionParser provided by the Extension
	 */
	private SectionParser parser;
	
	/**
	 * BoardCreator provided by the Extension
	 */
	private BoardCreator creator;
	
	/**
	 * BoardDecorator provided by the Extension
	 */
	private BoardDecorator decorator;
	
	/**
	 * Create an Extension
	 * @param parser SectionParser to read Puzzles loadable with this extension
	 * @param creator BoardCreator to create Boards for puzzles loaded with this Extension
	 * @param decorator BoardDecorator to decorate Boards loaded with this Extension
	 */
	public Extension(SectionParser parser, BoardCreator creator, BoardDecorator decorator) {
		this.parser = parser;
		this.creator = creator;
		this.decorator = decorator;
	}
	
	/**
	 * Get a SectionParser to read Puzzles loadable with this extension
	 * @return a SectionParser to read Puzzles loadable with this extension
	 */
	public SectionParser getParser() {
		return parser;
	}
	
	/**
	 * Get a BoardCreator to create Boards for puzzles loaded with this Extension
	 * @return a BoardCreator to create Boards for puzzles loaded with this Extension
	 */
	public BoardCreator getCreator() {
		return creator;
	}
	
	/**
	 * Get a BoardDecorator to decorate Boards loaded with this Extension
	 * @return a BoardDecorator to decorate Boards loaded with this Extension
	 */
	public BoardDecorator getDecorator() {
		return decorator;
	}
	
	/**
	 * Call used to check whether all prerequisites are met before
	 * allowing extension to be considered usable.
	 *
	 * Examples of prerequisites include dependencies to other
	 * extensions, where one extension may be built on top of
	 * another.
	 *
	 * @return   {@code true} if all prerequisites for using this 
	 *           extension are met
	 */
	public abstract Set<String> getPrerequisites();
}
