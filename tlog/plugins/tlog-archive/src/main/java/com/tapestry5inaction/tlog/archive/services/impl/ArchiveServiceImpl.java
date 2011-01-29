package com.tapestry5inaction.tlog.archive.services.impl;

import com.tapestry5inaction.tlog.archive.services.ArchiveService;
import com.tapestry5inaction.tlog.entities.Archive;
import com.tapestry5inaction.tlog.entities.Month;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ArchiveServiceImpl implements ArchiveService {
    @Inject
    private Session session;

    public List<Archive> findArchives() {
        List<Archive> archives = new ArrayList<Archive>();

        List result = session.createQuery("select count(id), year(publishDate), month(publishDate) " +
                "from Article group by year(publishDate), month(publishDate) " +
                "order by year(publishDate) desc, month(publishDate) desc").setMaxResults(10).list();

        for (Object next : result) {
            Object[] array = (Object[]) next;

            final Number count = (Number) array[0];

            Month month = new Month((Integer) array[1], (Integer) array[2]);

            archives.add(new Archive(month, count));
        }

        return archives;
    }
}
