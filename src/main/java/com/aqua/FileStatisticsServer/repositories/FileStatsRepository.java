package com.aqua.FileStatisticsServer.repositories;

import com.aqua.FileStatisticsServer.model.FileStats;

import org.springframework.data.repository.CrudRepository;

public interface FileStatsRepository extends CrudRepository<FileStats, Long> {

}
