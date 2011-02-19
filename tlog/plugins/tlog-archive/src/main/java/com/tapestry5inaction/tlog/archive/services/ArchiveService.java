package com.tapestry5inaction.tlog.archive.services;


import com.tapestry5inaction.tlog.core.entities.Archive;

import java.util.List;

public interface ArchiveService {

    List<Archive> findArchives();
}
