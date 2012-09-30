package com.tapestry5book.tlog.services;


import java.util.List;

public interface PluginPageManager {

    boolean isPluginPage(String pageName);

    List<String> getPluginPageNames();
}
