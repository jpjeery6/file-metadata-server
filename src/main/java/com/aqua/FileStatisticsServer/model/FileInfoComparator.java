package com.aqua.FileStatisticsServer.model;

import java.util.Comparator;

public class FileInfoComparator implements Comparator<FileInfo> {

    @Override
    public int compare(FileInfo arg0, FileInfo arg1) {

        return arg1.getFilenum().compareTo(arg0.getFilenum());

    }

}
