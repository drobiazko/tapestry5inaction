package com.tapestry5book.components;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet="context:style.css")
public class Layout {
	
	@Parameter("true")
	@Property
	private boolean showSource;
}
