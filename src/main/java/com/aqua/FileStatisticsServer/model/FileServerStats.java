package com.aqua.FileStatisticsServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileServerStats {
    @Id
    private long client_id;
    int filesCount;
    Long maxFileSize;
    String maxFile;
    Double avgFileSize;
    String fileExts;
    String mostFreqFileExt;
    int freq;
    @Column(length = 100000)
    String recentFiles;

    public FileServerStats() {
    }

    public FileServerStats(long client_id, int filesCount, Long maxFileSize, String maxFile, Double avgFileSize,
            String fileExts, String mostFreqFileExt, int freq, String recentFiles) {
        this.client_id = client_id;
        this.filesCount = filesCount;
        this.maxFileSize = maxFileSize;
        this.maxFile = maxFile;
        this.avgFileSize = avgFileSize;
        this.fileExts = fileExts;
        this.mostFreqFileExt = mostFreqFileExt;
        this.freq = freq;
        this.recentFiles = recentFiles;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
    }

    public Long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getMaxFile() {
        return maxFile;
    }

    public void setMaxFile(String maxFile) {
        this.maxFile = maxFile;
    }

    public Double getAvgFileSize() {
        return avgFileSize;
    }

    public void setAvgFileSize(Double avgFileSize) {
        this.avgFileSize = avgFileSize;
    }

    public String getFileExts() {
        return fileExts;
    }

    public void setFileExts(String fileExts) {
        this.fileExts = fileExts;
    }

    public String getMostFreqFileExt() {
        return mostFreqFileExt;
    }

    public void setMostFreqFileExt(String mostFreqFileExt) {
        this.mostFreqFileExt = mostFreqFileExt;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String getRecentFiles() {
        return recentFiles;
    }

    public void setRecentFiles(String recentFiles) {
        this.recentFiles = recentFiles;
    }

}
