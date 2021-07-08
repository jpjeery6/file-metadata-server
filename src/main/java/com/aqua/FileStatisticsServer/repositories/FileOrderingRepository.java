package com.aqua.FileStatisticsServer.repositories;

import com.aqua.FileStatisticsServer.model.FileOrdering;

import org.springframework.data.repository.CrudRepository;

public interface FileOrderingRepository extends CrudRepository<FileOrdering, Long> {

    FileOrdering findById(long id);

}
