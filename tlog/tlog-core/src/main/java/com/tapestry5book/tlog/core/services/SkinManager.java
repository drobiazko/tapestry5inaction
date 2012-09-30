package com.tapestry5book.tlog.core.services;

import com.tapestry5book.tlog.core.entities.Skin;

import java.util.Set;

public interface SkinManager {

    Set<Skin> getAvailableSkins();

    SkinResources getSkinResources(Skin skin);
}
