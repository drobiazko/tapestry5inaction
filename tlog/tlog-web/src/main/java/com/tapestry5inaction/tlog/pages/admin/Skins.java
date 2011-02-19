package com.tapestry5inaction.tlog.pages.admin;

import com.tapestry5inaction.tlog.core.annotations.PluginPage;
import com.tapestry5inaction.tlog.core.entities.Blog;
import com.tapestry5inaction.tlog.core.entities.Skin;
import com.tapestry5inaction.tlog.core.services.SkinManager;
import com.tapestry5inaction.tlog.core.services.SkinResources;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;

import java.util.Set;

@PluginPage
public class Skins {

    @Inject
    private SkinManager skinManager;

    @Inject
    private AssetSource assetSource;

    @Inject
    private Messages messages;

    @Property
    private Skin currentSkin;

    @SessionState
    private Blog blog;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String skinActivateMessage;

    public Set<Skin> getSkins() {
        return skinManager.getAvailableSkins();
    }

    public Asset getCurrentPreview() {
        SkinResources resources = skinManager.getSkinResources(currentSkin);

        Resource preview = resources.getPreview();

        return assetSource.getUnlocalizedAsset(preview.getPath());
    }

    @CommitAfter
    void onActivateSkin(String name, String version) {
        blog.setSkin(new Skin(name, version));

        skinActivateMessage = messages.format("skin-aktivated", name, version);
    }
}
