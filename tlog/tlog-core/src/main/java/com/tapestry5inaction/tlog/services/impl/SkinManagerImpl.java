package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.services.Skin;
import com.tapestry5inaction.tlog.services.SkinManager;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.InjectResource;

import java.util.Map;

public class SkinManagerImpl implements SkinManager {

    @InjectResource
    private Map<Skin, Resource> skins;

    public Resource getSkinTemplate(Skin skin) {
        return skins.get(skin);
    }
}
