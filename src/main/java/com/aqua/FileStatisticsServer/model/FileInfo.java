package com.aqua.FileStatisticsServer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// @IdClass(FileInfoCompositeKey.class)
public class FileInfo implements Serializable {
    @Id
    String file;
    Long size;
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    Integer filenum;

    public FileInfo() {
    }

    public FileInfo(String file, Long size) {
        this.file = file;
        this.size = size;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getFilenum() {
        return filenum;
    }

    public void setFilenum(Integer filenum) {
        this.filenum = filenum;
    }

    @Override
    public String toString() {
        return "FileInfo [file=" + file + ", filenum=" + filenum + ", size=" + size + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FileInfo other = (FileInfo) obj;
        if (file == null) {
            if (other.file != null)
                return false;
        } else if (!file.equals(other.file))
            return false;
        return true;
    }

}
