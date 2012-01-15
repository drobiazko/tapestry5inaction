package com.tapestry5inaction.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.util.TextStreamResponse;

@Import(library = "${tapestry.scriptaculous}/controls.js")
public class InPlaceEditor {
    public static final String EVENT_NAME = "edit";
    private static final String PARAM_NAME = "t:InPlaceEditor";

    @Parameter(required = true)
    private String value;

    @Environmental
    private JavaScriptSupport javaScriptSupport;

    @Inject
    private ComponentResources resources;

    @Inject
    private Request request;

    void afterRender(MarkupWriter writer) {
        String elementName = resources.getElementName();

        if (elementName == null) {
            elementName = "span";
        }

        String clientId = javaScriptSupport
                .allocateClientId(resources.getId());

        writer.element(elementName, "id", clientId);

        if (value != null) {
            writer.write(value);
        }

        writer.end();

        Link link = resources.createEventLink(EVENT_NAME);

        JSONObject config = new JSONObject("paramName", PARAM_NAME);

        javaScriptSupport.addScript(
                "new Ajax.InPlaceEditor('%s', '%s', %s);",
                clientId, link.toAbsoluteURI(), config);
    }

    Object onEdit() {
        value = request.getParameter(PARAM_NAME);

        return new TextStreamResponse("text/plain", value);
    }
}