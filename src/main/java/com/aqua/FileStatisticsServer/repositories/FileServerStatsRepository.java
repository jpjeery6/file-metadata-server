package com.aqua.FileStatisticsServer.repositories;

import com.aqua.FileStatisticsServer.model.FileServerStats;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileServerStatsRepository extends JpaRepository<FileServerStats, Long> {

}
