package sudoken.extension;

import sudoken.domain.BoardCreator;
import sudoken.parser.SectionParser;

public abstract class Extension {
	private SectionParser parser;
	private BoardCreator creator;
	
	public Extension(SectionParser parser, BoardCreator creator) {
		this.parser = parser;
		this.creator = creator;
	}
	
	public SectionParser getParser() {
		return parser;
	}
	
	public BoardCreator getCreator() {
		return creator;
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
	public abstract boolean hasPrerequisites();
}
