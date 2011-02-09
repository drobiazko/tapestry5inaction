package com.tapestry5inaction.tlog.services;


import java.util.List;

public interface PluginPageManager {

    boolean isPluginPage(String pageName);

    List<String> getPluginPageNames();
}
