package org.groupware.common.file;

public enum S3Directory {

    ATTACHMENT_POST("attachment/post/"),
    ATTACHMENT_PROJECT("attachment/project"),
    IMAGE("profileImage/");

    private final String directory;

    S3Directory(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }
}
