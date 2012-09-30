package com.tapestry5book.pages.chapter06.blog;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.upload.services.UploadedFile;

import java.io.File;

public class Upload {

    @Property
    private UploadedFile file;

    public void onSuccess() {

        String tempDir = System.getProperty("java.io.tmpdir");

        File targetFile = new File(tempDir, file.getFileName());

        file.write(targetFile);
    }


}