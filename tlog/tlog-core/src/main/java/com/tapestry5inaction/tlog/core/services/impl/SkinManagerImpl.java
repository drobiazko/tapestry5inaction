package com.tapestry5inaction.tlog.core.services.impl;

import com.tapestry5inaction.tlog.core.entities.Skin;
import com.tapestry5inaction.tlog.core.services.SkinManager;
import com.tapestry5inaction.tlog.core.services.SkinResources;
import org.apache.tapestry5.ioc.annotations.InjectResource;

import java.util.Map;
import java.util.Set;

public class SkinManagerImpl implements SkinManager {

    @InjectResource
    private Map<Skin, SkinResources> skins;

    public Set<Skin> getAvailableSkins() {
        return skins.keySet();
    }

    public SkinResources getSkinResources(Skin skin) {
        return skins.get(skin);
    }
}
