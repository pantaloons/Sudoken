package sudoken.extension;

import java.util.Set;

import sudoken.domain.BoardCreator;
import sudoken.domain.BoardDecorator;
import sudoken.persistence.SectionParser;

public abstract class Extension {
	private SectionParser parser;
	private BoardCreator creator;
	private BoardDecorator decorator;
	
	public Extension(SectionParser parser, BoardCreator creator, BoardDecorator decorator) {
		this.parser = parser;
		this.creator = creator;
		this.decorator = decorator;
	}
	
	public SectionParser getParser() {
		return parser;
	}
	
	public BoardCreator getCreator() {
		return creator;
	}
	
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
