package com.aqua.FileStatisticsServer.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FileStats implements Serializable {

    @Id
    private Long client_id;
    int filesCount;
    @OneToMany(cascade = CascadeType.ALL)
    List<FileInfo> filesInfo;

    public FileStats() {
    }

    public FileStats(Long client_id, int filesCount, List<FileInfo> filesInfo) {
        this.client_id = client_id;
        this.filesCount = filesCount;
        this.filesInfo = filesInfo;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
    }

    public List<FileInfo> getFilesInfo() {
        return filesInfo;
    }

    public void setFilesInfo(List<FileInfo> filesInfo) {
        this.filesInfo = filesInfo;
    }

}
