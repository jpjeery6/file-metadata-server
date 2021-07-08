package com.aqua.FileStatisticsServer.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileOrdering {
    @Id
    Long client_id;
    Integer num;

    public FileOrdering() {
    }

    public FileOrdering(Long client_id, Integer num) {
        this.client_id = client_id;
        this.num = num;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}
