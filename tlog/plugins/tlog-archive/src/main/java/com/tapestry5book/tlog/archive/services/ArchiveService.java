package com.tapestry5book.tlog.archive.services;


import com.tapestry5book.tlog.core.entities.Archive;

import java.util.List;

public interface ArchiveService {

    List<Archive> findArchives();
}
