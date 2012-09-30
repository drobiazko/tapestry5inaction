package com.tapestry5book.tlog.core.services;


import org.apache.tapestry5.ioc.Resource;

public class SkinResources {

    private Resource preview;
    private Resource template;

    public SkinResources(Resource preview, Resource template) {
        this.preview = preview;
        this.template = template;
    }

    public Resource getPreview() {
        return preview;
    }

    public Resource getTemplate() {
        return template;
    }
}
