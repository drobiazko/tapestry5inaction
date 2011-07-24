package com.tapestry5inaction.pages.chapter08;

import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.FieldValidator;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.services.PropertyEditContext;

public class AppPropertyEditBlocks {
    @Property
    @Environmental
    private PropertyEditContext context;

    @Component(parameters = {"value=prop:context.propertyValue",
            "label=prop:context.label",
            "clientId=prop:context.propertyid",
            "translate=prop:urlTranslator",
            "validate=prop:urlValidator"})
    private TextField urlField;

    public FieldValidator getURLValidator() {
        return context.getValidator(urlField);
    }

    public FieldTranslator getURLTranslator() {
        return context.getTranslator(urlField);
    }
}
